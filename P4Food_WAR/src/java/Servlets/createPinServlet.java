/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Business.AccountBean;
import Business.ImageBean;
import Business.boardCrudBean;
import Entities.Pin;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import remotesettings.setRemote;
import services.pinCrudInterface;

/**
 *
 * @author ken
 */
@WebServlet(name = "createPinServlet", urlPatterns = {"/createPin"})
@MultipartConfig
public class createPinServlet extends HttpServlet {
    private pinCrudInterface pinBean;
    
    /*Temporary*/
    @EJB
    private boardCrudBean boardBean;
    
    @EJB
    private ImageBean imgbean;
    
    /**
    * The context to be used to perform lookups of remote beans
    */
    private static InitialContext ic;
    
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
            
            pinBean = (pinCrudInterface) ic.lookup("java:global/P4Food/statistics_EJB/pinCrudBean!services.pinCrudInterface");        
            AccountBean currentUser = (AccountBean)request.getSession().getAttribute("user");
            int id = Integer.parseInt(request.getParameter("id"));

            List<Pin> boardPins;

            boardPins = pinBean.getPinsForBoard(boardBean.getBoard(id));

            request.setAttribute("pinList", boardPins);           

            request.setAttribute("isAdmin", currentUser.getAccount().getAdmin());
            request.setAttribute("boardId", id);
            request.getRequestDispatcher("pins.jsp").forward(request, response);
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

            pinBean = (pinCrudInterface) ic.lookup("java:global/P4Food/statistics_EJB/pinCrudBean!services.pinCrudInterface");  
            String recipeName = request.getParameter("recipeTitle");
            String recipe = request.getParameter("recipe");   
            int boardId = Integer.parseInt(request.getParameter("id"));
            final Part filePart = request.getPart("file");
            final String fileName = getFileName(filePart);
            String url = imgbean.storeImage(fileName, filePart);

            pinBean.createPin(recipeName, recipe, boardId, url);          


            response.sendRedirect("createPin?id=" + boardId);
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

    private String getFileName(final Part part) {
    for (String content : part.getHeader("content-disposition").split(";")) {
        if (content.trim().startsWith("filename")) {
            return content.substring(
                    content.indexOf('=') + 1).trim().replace("\"", "");
        }
    }
    return null;
}
}
