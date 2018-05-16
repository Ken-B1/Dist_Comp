/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

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
import services.AdminInterface;

/**
 *
 * @author ken
 */
@WebServlet(name = "AdminRegistrationServlet", urlPatterns = {"/Adminregistration"})
public class AdminRegistrationServlet extends HttpServlet {
    private AdminInterface admin;
    
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
        response.sendRedirect("adminsignup.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     * Attempt to create an account and changes this account to an admin account
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            ic = new InitialContext(setRemote.setProperties());   
            
            admin = (AdminInterface) ic.lookup("java:global/statistics_EJB/Admin!services.AdminInterface");         
            String email = request.getParameter("email");
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String country = request.getParameter("country");
            String gender = request.getParameter("gender");

            Status result = admin.createAdminAccount(email, userName, password, fname, lname, country, gender);    

            if(result.getStatusCode() != 0){
                request.setAttribute("Status", result);
                request.getRequestDispatcher("Adminregistration").forward(request, response);
            }else{
                response.sendRedirect("admin");
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
