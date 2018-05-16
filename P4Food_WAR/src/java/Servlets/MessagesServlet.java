/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Entities.Messages;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;
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
@WebServlet(name = "MessagesServlet", urlPatterns = {"/Messages"})
public class MessagesServlet extends HttpServlet {
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
        try{
            ic = new InitialContext(setRemote.setProperties());
            response.setContentType("text/html;charset=UTF-8");
            AccountBeanInterface currentUser = (AccountBeanInterface)request.getSession().getAttribute("user");
            Collection<Messages> messages = currentUser.getMessages();

            // Manually write a json file with all objects
            // This is a quick 'hack' instead of using a proper json library because we won't need this often and 
            // finding a json library will probably take longer
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                String totalString = "";
                for(Messages x: messages){
                    Date currentDate = new Date(System.currentTimeMillis());
                    long daysAgo = currentDate.getTime() - x.getTimestamp().getTime();
                    int numDays = (int)TimeUnit.DAYS.convert(daysAgo, TimeUnit.MILLISECONDS);
                    String toAdd = "";
                    toAdd += "<a href=\"FullMessage?id=";
                    toAdd += x.getId();
                    toAdd += "\" class=\"list-group-item list-group-item-action flex-column align-items-start\"><div class=\"d-flex w-100 justify-content-between\"><h5 class=\"mb-1\">";
                    toAdd += x.getSubject();
                    toAdd += "</h5><small class=\"text-muted\">";
                    toAdd += numDays;
                    if(numDays == 1){
                        toAdd += " day ago</small></div></a>";
                    }else{
                        toAdd += " days ago</small></div></a>";
                    }
                    totalString = toAdd + totalString;
                }

                out.println(totalString);
            }
        }catch(NamingException e){
            System.out.println(e.getMessage());
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
        processRequest(request, response);
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
