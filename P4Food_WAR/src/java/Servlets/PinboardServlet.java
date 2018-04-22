/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Business.AccountFacade;
import Business.boardCrudBean;
import Entities.Account;
import Entities.Board;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ken
 */
@WebServlet(name = "PinboardServlet", urlPatterns = {"/pinboard"})
public class PinboardServlet extends HttpServlet {
    @EJB
    private AccountFacade account;
    
    @EJB 
    private boardCrudBean boardBean;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = (int)request.getSession().getAttribute("id");
        
        //Logic for CRUD boards, move to appropriate servlet when created
        Account currentUser = account.getAccountById(id);
        List<Board> userBoards = boardBean.getBoardsForUser(currentUser);
        if(!userBoards.isEmpty()){
            boardBean.deleteBoard(userBoards.get(0).getId());
        }
        userBoards = boardBean.getBoardsForUser(currentUser);
        
        request.setAttribute("boardList", userBoards);
        
        
        //Correct execution proceeds
        request.setAttribute("isAdmin", account.getAccountById(id).getAdmin());
        request.getRequestDispatcher("home.jsp").forward(request, response);
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                //Logic for CRUD boards, move to appropriate servlet when created
        String boardName = request.getParameter("boardname");
        
        int id = (int)request.getSession().getAttribute("id");
        Account currentUser = account.getAccountById(id);
        boardBean.createBoard(boardName,currentUser);
        response.sendRedirect("pinboard");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
