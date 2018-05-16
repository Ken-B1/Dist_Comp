/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Business.friendsBean;
import Entities.Account;
import java.io.IOException;
import java.util.Collection;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import remotesettings.setRemote;
import services.AccountBeanInterface;

/**
 *
 * @author ken
 */
@WebServlet(name = "FriendRequestServlet", urlPatterns = {"/FriendRequest"})
public class FriendRequestServlet extends HttpServlet {
    @EJB
    private friendsBean friends;
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

            Collection<Account> thisfriends = friends.getFriends(currentUser.getAccount().getId());
            Collection<Account> thisrequests = friends.getFriendRequests(currentUser.getAccount().getId());

            request.setAttribute("friends", thisfriends);
            request.setAttribute("requests", thisrequests);

            request.getRequestDispatcher("friends.jsp").forward(request, response);
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
            AccountBeanInterface currentUser = (AccountBeanInterface)request.getSession().getAttribute("user");
            int currentUserId = currentUser.getAccount().getId();
            String id = request.getParameter("id");
            String type = request.getParameter("type");

            if(id == null || type == null){
                //Parameters not correctly set
                request.getRequestDispatcher("pinboard").forward(request, response);
                return;
            }

            int idInt = Integer.parseInt(id);

            if(type.equals("request")){
                // New friend request
                friends.sendFriendRequest(currentUserId, idInt);
            }else if(type.equals("accept")){
                // Friend request accept
                friends.acceptFriendRequest(idInt, currentUserId);
            }else if(type.equals("deny")){
                // Friend request deny
                friends.removeFriendRequest(idInt, currentUserId);
            }else if(type.equals("remove")){
                // Remove friend
                friends.sendunFriendRequest(currentUserId, idInt);
            }else{
                // Unknown parameter
                request.getRequestDispatcher("pinboard").forward(request, response);
            }
            response.sendRedirect("FriendRequest");
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
