/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Entities.Account;
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
import services.messageCrudInterface;

/**
 *
 * @author ken
 */
@WebServlet(name = "WriteMessage", urlPatterns = {"/WriteMessage"})
public class WriteMessageServlet extends HttpServlet {

    private messageCrudInterface messageHandler;
    
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
            
            AccountBeanInterface currentUser = (AccountBeanInterface)request.getSession().getAttribute("user");
            String idString = request.getParameter("id");
            int receiverId;
            
            if(idString == null || "".equals(idString)){
                // Something went wrong
                request.getRequestDispatcher("pinboard").forward(request, response);
                System.out.println("id = null or id = empty");
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
        try{
            ic = new InitialContext(setRemote.setProperties());   
            
            messageHandler = (messageCrudInterface) ic.lookup("java:global/statistics_EJB/messageCrud!services.messageCrudInterface");
            
            AccountBeanInterface currentUser = (AccountBeanInterface)request.getSession().getAttribute("user");
            int id = Integer.parseInt(request.getParameter("recid"));
            String subject = request.getParameter("subject");
            String message = request.getParameter("content");
            if("".equals(subject) || subject == null){
                // Subject has to be entered
                request.setAttribute("error", "Please enter a subject");
                Account receiver = currentUser.getAccountForId(id);
                request.setAttribute("receiverName", receiver.getUsername());
                request.setAttribute("receiverId", receiver.getId());
                request.getRequestDispatcher("messagewrite.jsp").forward(request, response);
                return;
            }
            
            messageHandler.createMessage(currentUser.getAccount().getId(), id, subject, message);
            
            request.getRequestDispatcher("MessageOverview").forward(request, response);
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
