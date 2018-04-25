/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Business.AccountFacade;
import Business.boardCrudBean;
import Business.pinCrudBean;
import Entities.Account;
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
    private AccountFacade account;
    
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
        
        int id = (int)request.getSession().getAttribute("id");
        Account currentUser = account.getAccountById(id);

        List<Board> userBoards = boardBean.getBoardsForUser(currentUser);
        List<Pin> boardPins;
        if(userBoards.size() == 0){
            boardPins = new ArrayList();   
        }else{
            boardPins = pinBean.getPinsForBoard(userBoards.get(0));
        }

        request.setAttribute("pinList", boardPins);           
        
        request.setAttribute("isAdmin", account.getAccountById(id).getAdmin());
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
        
        int id = (int)request.getSession().getAttribute("id");       
        
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
