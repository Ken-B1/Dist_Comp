/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Entities.Account;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Business_Utility.Status;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import remotesettings.setRemote;
import services.AccountBeanInterface;

/**
 *
 * @author ken
 */
@WebServlet(name = "SettingsServlet", urlPatterns = {"/settings"})
public class SettingsServlet extends HttpServlet {
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
        try{
            ic = new InitialContext(setRemote.setProperties());
            Account account = ((AccountBeanInterface)request.getSession().getAttribute("user")).getAccount();
            request.setAttribute("accountinfo", account);
            request.getRequestDispatcher("settings.jsp").forward(request, response);
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
            Account account = new Account();
        
            account.setEmail(request.getParameter("email"));
            account.setUsername(request.getParameter("username"));
            account.setFname(request.getParameter("fname"));
            account.setLname(request.getParameter("lname"));
            account.setCountry(request.getParameter("country"));
            account.setGender(request.getParameter("gender"));

            AccountBeanInterface currentUser = (AccountBeanInterface)request.getSession().getAttribute("user");

            Status result = currentUser.updateAccount(account);
            if(result.getStatusCode() == 0){
                response.sendRedirect("settings");
            }else{
                request.setAttribute("result", result);
                request.setAttribute("accountinfo", currentUser);
                request.getRequestDispatcher("settings.jsp").forward(request, response);
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
