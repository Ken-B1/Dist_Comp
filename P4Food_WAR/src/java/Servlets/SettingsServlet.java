/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Entities.Account;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Business.AccountFacade;
import Business_Utility.RegistrationStatus;

/**
 *
 * @author ken
 */
@WebServlet(name = "SettingsServlet", urlPatterns = {"/settings"})
public class SettingsServlet extends HttpServlet {

    @EJB
    private AccountFacade accountbean;
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
        Account account = accountbean.getAccountById(id);
        request.setAttribute("accountinfo", account);
        request.getRequestDispatcher("settings.jsp").forward(request, response);
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
        Account account = new Account();
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        
        account.setEmail(request.getParameter("email"));
        account.setUsername(request.getParameter("username"));
        account.setFname(request.getParameter("fname"));
        account.setLname(request.getParameter("lname"));
        account.setCountry(request.getParameter("country"));
        account.setGender(request.getParameter("gender"));
        
        try {
            RegistrationStatus result = accountbean.updateAccount(account, (int)request.getSession().getAttribute("id"));
            if(result.getStatusCode() == 0){
                response.sendRedirect("settings");
            }else{
                int id = (int)request.getSession().getAttribute("id");
                request.setAttribute("result", result);
                request.setAttribute("accountinfo", accountbean.getAccountById(id));
                request.getRequestDispatcher("settings.jsp").forward(request, response);
            }
        } catch(javax.persistence.NoResultException e) {
            // TODO: Show a corresponding error
            // Error happens when nonexistent user is somehow requested
            System.out.println(e.getMessage());
        }
        response.sendRedirect("settings");
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
