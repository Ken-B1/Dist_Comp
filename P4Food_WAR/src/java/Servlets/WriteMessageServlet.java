/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Business.AccountBean;
import Business.messageCrud;
import Entities.Account;
import java.io.IOException;
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
@WebServlet(name = "WriteMessage", urlPatterns = {"/WriteMessage"})
public class WriteMessageServlet extends HttpServlet {
    @EJB
    private messageCrud messageHandler;
    
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
        String idString = request.getParameter("id");
        int receiverId;
        if(idString == null){
            // Something went wrong
            request.getRequestDispatcher("pinboard").forward(request, response);
            System.out.println("id = null");
        }else{
            receiverId = Integer.parseInt(idString);
            Account receiver = currentUser.getAccountForId(receiverId);
            if(receiver == null){
                // Nonexistent user is requested
                request.getRequestDispatcher("pinboard").forward(request, response);     
                System.out.println("receiver = null");
            }else{
                request.setAttribute("receiverName", receiver.getUsername());
                request.setAttribute("receiverId", receiver.getId());
                request.getRequestDispatcher("messagewrite.jsp").forward(request, response);
            }
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
            AccountBean currentUser = (AccountBean)request.getSession().getAttribute("user");
            System.out.println("Called");
            int id = Integer.parseInt(request.getParameter("recid"));
            String subject = request.getParameter("subject");
            String message = request.getParameter("content");

            messageHandler.createMessage(currentUser.getAccount().getId(), id, subject, message);
            
            request.getRequestDispatcher("MessageOverview").forward(request, response);
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
