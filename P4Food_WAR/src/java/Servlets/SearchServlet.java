/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Business.searchBean;
import Entities.Account;
import Entities.Board;
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
import services.searchBeanInterface;

/**
 *
 * @author ken
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/Search"})
public class SearchServlet extends HttpServlet {

    private searchBeanInterface search;

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
        request.getRequestDispatcher("pinboard").forward(request, response);
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
            search = (searchBeanInterface) ic.lookup("java:global/P4Food/statistics_EJB/searchBean!services.searchBeanInterface");     
            
            String searchString = request.getParameter("searchString");
            List<Pin> Pins = search.findPinsForSearch(searchString);
            List<Board> boards = search.findBoardsForSearch(searchString);
            List<Account> users = search.findUsersForSearch(searchString);
            request.setAttribute("resultingPins", Pins);
            request.setAttribute("resultingBoards", boards);
            request.setAttribute("resultingUsers", users);
            request.getRequestDispatcher("resultPage.jsp").forward(request, response);
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
