/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Entities.Board;
import Entities.Categories;
import Entities.Pin;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import remotesettings.setRemote;
import services.ImageBeanInterface;
import services.boardCrudBeanInterface;
import services.databaseConnectorInterface;
import services.pinCrudInterface;

/**
 *
 * @author ken
 */
@WebServlet(name = "PinSettingsServlet", urlPatterns = {"/pinsSettings"})
public class PinSettingsServlet extends HttpServlet {

    private pinCrudInterface pinCRUD;
    private databaseConnectorInterface connector;
    private static InitialContext ic;
     
    private ImageBeanInterface imgbean;

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

        try {
            ic = new InitialContext(setRemote.setProperties());
            connector = (databaseConnectorInterface) ic.lookup("java:global/statistics_EJB/databaseConnector!services.databaseConnectorInterface");
            pinCRUD = (pinCrudInterface) ic.lookup("java:global/statistics_EJB/pinCrudBean!services.pinCrudInterface");

            String Stringid = request.getParameter("pinId");
            if (Stringid == null) {
                // Wrong call
                request.getRequestDispatcher("pinboard").forward(request, response);
            } else {
                int pinId = Integer.parseInt(Stringid);
                Pin currentPin = pinCRUD.getPin(pinId);
                request.setAttribute("pin", currentPin);
                List<Categories> allCategories = connector.getAllCategories();
                request.setAttribute("categoryList", allCategories);
                request.getRequestDispatcher("pinsSettings.jsp").forward(request, response);
            }
        } catch (NamingException e) {
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
        try{
            ic = new InitialContext(setRemote.setProperties());   

            pinCRUD = (pinCrudInterface) ic.lookup("java:global/statistics_EJB/pinCrudBean!services.pinCrudInterface"); 
            imgbean = (ImageBeanInterface) ic.lookup("java:global/statistics_EJB/ImageBean!services.ImageBeanInterface");
        
            
            String recipeName = request.getParameter("recipeTitle");
            String recipe = request.getParameter("recipe");   
            int pinId = Integer.parseInt(request.getParameter("pinId"));
            
            pinCRUD.updatePin(pinId,recipeName,recipe,pinId);          


            response.sendRedirect("fullRecipe?id=" + pinId);
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
