/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import remotesettings.setRemote;
import services.AdminInterface;

/**
 *
 * @author Ken
 */
@WebServlet(name = "AdmincategoriesServlet", urlPatterns = {"/Admincategories"})
public class AdmincategoriesServlet extends HttpServlet {
    
    private AdminInterface admin;
    
    /**
    * The context to be used to perform lookups of remote beans
    */
    private static InitialContext ic;    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * Returns a page with all categories
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
            
            admin = (AdminInterface) ic.lookup("java:global/statistics_EJB/Admin!services.AdminInterface");        
            List<String> categories = admin.getCategoryNames();
            request.setAttribute("categorylist", categories);
            request.getRequestDispatcher("admincategories.jsp").forward(request, response);
        }catch(NamingException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * Creates a new category
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
            
            admin = (AdminInterface) ic.lookup("java:global/statistics_EJB/Admin!services.AdminInterface"); 
            String name = request.getParameter("category");
            admin.addCategory(name);
            response.sendRedirect("Admincategories");
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
