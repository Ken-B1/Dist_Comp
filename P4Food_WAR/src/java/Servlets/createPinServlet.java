/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Business.AccountBean;
import Business.boardCrudBean;
import Business.pinCrudBean;
import Entities.Board;
import Entities.Pin;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "createPinServlet", urlPatterns = {"/createPin"})
public class createPinServlet extends HttpServlet {
    @EJB 
    private pinCrudBean pinBean;
    
    /*Temporary*/
    @EJB
    private boardCrudBean boardBean;
    
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
        List<Pin> boardPins;
        
        if(userBoards.isEmpty()){
            boardPins = new ArrayList();   
        }else{
            boardPins = pinBean.getPinsForBoard(userBoards.get(0));
        }

        request.setAttribute("pinList", boardPins);           
        
        request.setAttribute("isAdmin", currentUser.getAccount().getAdmin());
        request.getRequestDispatcher("pins.jsp").forward(request, response);
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
        String recipeName = request.getParameter("recipeTitle");
        String recipe = request.getParameter("recipe");   
        
        pinBean.createPin(recipeName, recipe, 1);          
            
        response.sendRedirect("createPin");
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
