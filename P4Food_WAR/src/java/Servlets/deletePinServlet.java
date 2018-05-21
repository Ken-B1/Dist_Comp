/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Entities.Board;
import Entities.Pin;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import remotesettings.setRemote;
import services.AccountBeanInterface;
import services.ImageBeanInterface;
import services.boardCrudBeanInterface;
import services.pinCrudInterface;

/**
 *
 * @author ken
 */
@WebServlet(name = "deletePinServlet", urlPatterns = {"/deletePin"})
public class deletePinServlet extends HttpServlet {
    private pinCrudInterface pinBean;
    
    private boardCrudBeanInterface boardBean;
    
    private ImageBeanInterface imgbean;
    
    
    /**
    * The context to be used to perform lookups of remote beans
    */
    private static InitialContext ic;
    
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
        try{
            ic = new InitialContext(setRemote.setProperties());
            pinBean = (pinCrudInterface) ic.lookup("java:global/statistics_EJB/pinCrudBean!services.pinCrudInterface");        
            AccountBeanInterface currentUser = (AccountBeanInterface)request.getSession().getAttribute("user");
            int id = Integer.parseInt(request.getParameter("id"));
            Board currentBoard = boardBean.getBoard(id);
            List<Pin> boardPins;

            boardPins = pinBean.getPinsForBoard(currentBoard);

            request.setAttribute("pinList", boardPins);           
            request.setAttribute("isOwner", currentBoard.getOwner().getId().equals(currentUser.getAccount().getId()));
            request.setAttribute("isAdmin", currentUser.getAccount().getAdmin());
            request.setAttribute("boardId", id);
            request.setAttribute("boardName", currentBoard.getBoardname());
            request.setAttribute("isFollowing", currentUser.followsBoard(id));
            request.getRequestDispatcher("pins.jsp").forward(request, response);
        }catch(NamingException e){
            System.out.println(e.getMessage());
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
