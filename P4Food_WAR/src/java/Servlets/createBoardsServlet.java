/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Business.AccountFacade;
import Business.boardCrudBean;
import Business.databaseConnector;
import Entities.Account;
import Entities.Board;
import Entities.Categories;
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
@WebServlet(name = "createBoardsServlet", urlPatterns = {"/createBoard"})
public class createBoardsServlet extends HttpServlet {
    @EJB
    private AccountFacade account;
    
    @EJB 
    private boardCrudBean boardBean;
    
    @EJB
    private databaseConnector connector;
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
        int id = (int)request.getSession().getAttribute("id");
        Account currentUser = account.getAccountById(id);

        List<Board> userBoards = boardBean.getBoardsForUser(currentUser);

        request.setAttribute("boardList", userBoards);    
        
        
        List<Categories> allCategories = connector.getAllCategories();
        request.setAttribute("categoryList", allCategories);
        request.setAttribute("isAdmin", account.getAccountById(id).getAdmin());
        request.getRequestDispatcher("boards.jsp").forward(request, response);
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
        String boardName = request.getParameter("boardname");
        int id = (int)request.getSession().getAttribute("id");
        Account currentUser = account.getAccountById(id);        
        
        String categoryString = request.getParameter("categorychosen");
        if(categoryString.equals("NoCategory")){
            boardBean.createBoard(boardName, currentUser); 
        }else{
            int category = Integer.parseInt(request.getParameter("categorychosen")); 
            boardBean.createBoard(boardName,category, currentUser);          
        }
        
        response.sendRedirect("createBoard");
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
