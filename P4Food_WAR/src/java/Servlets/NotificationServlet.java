/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Business.AccountBean;
import Entities.Notifications;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ken
 */
@WebServlet(name = "NotificationServlet", urlPatterns = {"/Notifications"})
public class NotificationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
        }
    }

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
        response.setContentType("text/html;charset=UTF-8");
        AccountBean currentUser = (AccountBean)request.getSession().getAttribute("user");
        List<Notifications> notifications = new ArrayList(currentUser.getAccount().getNotificationsCollection1());
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            for(Notifications u: notifications){
                if(u.getIsread() == 1){
                    continue;
                }
                out.write("<a href=\"");
                out.write("NotificationRedirect?id=" + u.getIdnotifications());
                out.write("\" class=\"list-group-item list-group-item-action flex-column align-items-start\"><div class=\"d-flex w-100 justify-content-between\"><h5 class=\"mb-1\">");
                switch(u.getType()){
                    case 1:
                        out.write(u.getCreator().getUsername() + " created a new board.");
                        break;
                    case 2:
                        // Redirect to new pin    
                        out.write(u.getCreator().getUsername() + " created a new pin.");
                        break;
                    case 3:
                        // Redirect to new follower
                        out.write(u.getCreator().getUsername() + " started following you.");
                        break;
                    default:
                }                
                out.write("</h5></div></a>");
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
        processRequest(request, response);
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
