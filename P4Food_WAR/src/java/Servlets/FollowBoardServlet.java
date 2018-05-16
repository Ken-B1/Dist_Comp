/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Entities.Board;
import java.io.IOException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import remotesettings.setRemote;
import services.AccountBeanInterface;
import services.boardCrudBeanInterface;
import services.categoryBeanInterface;

/**
 *
 * @author ken
 */
@WebServlet(name = "FollowBoardServlet", urlPatterns = {"/FollowBoard"})
public class FollowBoardServlet extends HttpServlet {

    private boardCrudBeanInterface boardbean;
    
    /**
    * The context to be used to perform lookups of remote beans
    */
    private static InitialContext ic;
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
        request.getRequestDispatcher("pinboard").forward(request, response);
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
        try{
            ic = new InitialContext(setRemote.setProperties());
            boardbean = (boardCrudBeanInterface) ic.lookup("java:global/statistics_EJB/boardCrudBean!services.boardCrudBeanInterface");
            
            String id = request.getParameter("BoardId");          
            AccountBeanInterface currentUser = (AccountBeanInterface)request.getSession().getAttribute("user");
            int BoardId;
            if(id != null){
                // If id == null, something went wrong
                BoardId = Integer.parseInt(id);
                             
                Board requestedBoard = boardbean.getBoard(BoardId);
                if(requestedBoard != null){
                    // If requestedBoard is null, a nonexistend board is requested
                    currentUser.followBoard(requestedBoard);
                    //response.sendRedirect(request.getHeader("Referer"));
                }
            }
        }catch(NamingException e){
            System.out.println(e.getMessage());
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
