/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Entities.Categories;
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
import services.AccountBeanInterface;
import services.databaseConnectorInterface;

/**
 *
 * @author ken
 */
@WebServlet(name = "ChooseInitialCategoriesServlet", urlPatterns = {"/ChooseInitialCategories"})
public class ChooseInitialCategoriesServlet extends HttpServlet {
    private databaseConnectorInterface connector;
    
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
        // Redirect to pinboard, because this get should never be called right now
        response.sendRedirect("pinboard");
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
            connector = (databaseConnectorInterface)ic.lookup("java:global/statistics_EJB/databaseConnector!services.databaseConnectorInterface");
            String[] results = request.getParameterValues("CategorySelect");
            AccountBeanInterface currentUser = (AccountBeanInterface)request.getSession().getAttribute("user");        
            // Check if at least 3 categories have been selected.
            // If not, redirect back to categoryselect with an errormessage
            if(results.length < 3){
                System.out.println("length too short");
                request.setAttribute("NotEnoughCategories", true);
                request.getRequestDispatcher("pinboard").forward(request, response);
                return;
            }

            List<Categories> allCategories = connector.getAllCategories();
            for(String cat: results){
                for(Categories x: allCategories){
                    if(x.getId() == Integer.parseInt(cat)){
                        currentUser.addUserCategory(x);
                    }
                }
            }
            response.sendRedirect("pinboard");
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
