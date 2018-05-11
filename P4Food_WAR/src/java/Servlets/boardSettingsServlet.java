/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Business.boardCrudBean;
import Business.databaseConnector;
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
@WebServlet(name = "boardSettingsServlet", urlPatterns = {"/boardSettings"})
public class boardSettingsServlet extends HttpServlet {
    @EJB
    boardCrudBean boardCRUD;
    
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
        String Stringid = request.getParameter("boardId");
        if(Stringid == null){
            // Wrong call
            request.getRequestDispatcher("pinboard").forward(request, response);
        }else{
            int boardid = Integer.parseInt(Stringid);
            Board currentBoard = boardCRUD.getBoard(boardid);
            request.setAttribute("board", currentBoard);
            List<Categories> allCategories = connector.getAllCategories();
            request.setAttribute("categoryList", allCategories);
            request.getRequestDispatcher("boardSettings.jsp").forward(request, response);
        }
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
        String Stringid = request.getParameter("boardId");
        
        if(Stringid == null){
            // Wrong id
            request.getRequestDispatcher("pinboard").forward(request, response);
        }else{
            int boardid = Integer.parseInt(Stringid);
            String newName = request.getParameter("boardname");
            String categoryString = request.getParameter("categorychosen");
            boardCRUD.updateBoard(boardid, newName, Integer.parseInt(categoryString));
            response.sendRedirect("profile");
        }
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
