/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Entities.Notifications;
import java.io.IOException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import remotesettings.setRemote;
import services.StatisticsBeanInterface;

/**
 *
 * @author ken
 */
@WebServlet(name = "NotificationRedirectServlet", urlPatterns = {"/NotificationRedirect"})
public class NotificationRedirectServlet extends HttpServlet {
    StatisticsBeanInterface statsbean;

    /**
    * The context to be used to perform lookups of remote beans
    */
    private static InitialContext ic;
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
        response.sendRedirect("pinboard");
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
        try{
            ic = new InitialContext(setRemote.setProperties());
            statsbean = (StatisticsBeanInterface) ic.lookup("java:global/statistics_EJB/StatisticsBean!services.StatisticsBeanInterface");
            
            int id = Integer.parseInt(request.getParameter("id"));
            Notifications notif = statsbean.getNotification(id);
            statsbean.markAsRead(id);

            switch(notif.getType()){
                case 1:
                    // Redirect to new board
                    request.setAttribute("id", notif.getDescription());
                    request.getRequestDispatcher("createPin").forward(request, response);
                    break;
                case 2:
                    // Redirect to new pin
                    request.setAttribute("id", notif.getDescription());
                    request.getRequestDispatcher("fullRecipe").forward(request, response); 
                    break;
                case 3:
                    // Redirect to new follower
                    response.sendRedirect("profile?username=" + notif.getCreator().getUsername());
                    break;
                default:
                    response.sendRedirect("pinboard");
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
