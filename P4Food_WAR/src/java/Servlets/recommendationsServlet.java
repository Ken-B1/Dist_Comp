/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Business.AccountBean;
import Entities.Categories;
import Entities.Pin;
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
import services.boardCrudBeanInterface;
import services.databaseConnectorInterface;

/**
 *
 * @author ken
 */
@WebServlet(name = "recommendationsServlet", urlPatterns = {"/recommendations"})
public class recommendationsServlet extends HttpServlet {
    
    private boardCrudBeanInterface boardBean;
    
    private databaseConnectorInterface connector;

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
            boardBean = (boardCrudBeanInterface)ic.lookup("java:global/statistics_EJB/boardCrudBean!services.boardCrudBeanInterface");
            connector = (databaseConnectorInterface)ic.lookup("java:global/statistics_EJB/databaseConnector!services.databaseConnectorInterface");
            
            AccountBeanInterface currentUser = (AccountBeanInterface)request.getSession().getAttribute("user");
            List<Categories> allCategories = connector.getAllCategories();
            List<Pin> currentUserPin = currentUser.getRecommendations();
            currentUser.getRecommendations();
            request.setAttribute("pinlist", currentUserPin); 
            request.getRequestDispatcher("recommendations.jsp").forward(request, response);
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
