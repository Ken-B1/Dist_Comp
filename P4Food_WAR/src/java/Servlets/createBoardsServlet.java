/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Business.AccountBean;
import Business.boardCrudBean;
import Business.databaseConnector;
import Entities.Board;
import Entities.Categories;
import java.io.IOException;
import java.util.Collection;
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
        AccountBean currentUser = (AccountBean)request.getSession().getAttribute("user");

        List<Board> userBoards = boardBean.getBoardsForUser(currentUser.getAccount());
        Collection<Categories> userCategories = currentUser.getUserCategories();
        request.setAttribute("boardList", userBoards);
        request.setAttribute("userCategories", userCategories);
        request.setAttribute("followerNum", currentUser.getNumFollowers());
        request.setAttribute("followingNum", currentUser.getNumFollowing());
        
        
        List<Categories> allCategories = connector.getAllCategories();
        request.setAttribute("categoryList", allCategories);
        request.setAttribute("isAdmin", currentUser.getAccount().getAdmin());
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
        
        AccountBean currentUser = (AccountBean)request.getSession().getAttribute("user");
        
        String categoryString = request.getParameter("categorychosen");
        
        if(categoryString.equals("NoCategory")){
            boardBean.createBoard(boardName, currentUser.getAccount()); 
        }else{
            int category = Integer.parseInt(request.getParameter("categorychosen")); 
            boardBean.createBoard(boardName,category, currentUser.getAccount());          
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
