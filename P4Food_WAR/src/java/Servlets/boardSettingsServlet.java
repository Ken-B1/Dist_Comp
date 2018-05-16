/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Entities.Board;
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
import services.boardCrudBeanInterface;
import services.databaseConnectorInterface;

/**
 *
 * @author ken
 */
@WebServlet(name = "boardSettingsServlet", urlPatterns = {"/boardSettings"})
public class boardSettingsServlet extends HttpServlet {

    private boardCrudBeanInterface boardCRUD;
    
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
        try{
            ic = new InitialContext(setRemote.setProperties());            
            connector = (databaseConnectorInterface)ic.lookup("java:global/statistics_EJB/databaseConnector!services.databaseConnectorInterface");
            boardCRUD = (boardCrudBeanInterface) ic.lookup("java:global/statistics_EJB/boardCrudBean!services.boardCrudBeanInterface");
            
            String Stringid = request.getParameter("boardId");
            if(Stringid == null){
                // Wrong call
                request.getRequestDispatcher("pinboard").forward(request, response);
            }else{
                int boardid = Integer.parseInt(Stringid);
                Board currentBoard = boardCRUD.getBoard(boardid);
                request.setAttribute("board", currentBoard);
                List<Categories> allCategories = connector.getAllCategories();
                request.setAttribute("categoryList", allCategories);
                request.getRequestDispatcher("boardSettings.jsp").forward(request, response);
            }
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
        String Stringid = request.getParameter("boardId");
        try{
            ic = new InitialContext(setRemote.setProperties());  
            boardCRUD = (boardCrudBeanInterface) ic.lookup("java:global/statistics_EJB/boardCrudBean!services.boardCrudBeanInterface");
            
            if(Stringid == null){
                // Wrong id
                request.getRequestDispatcher("pinboard").forward(request, response);
            }else{
                int boardid = Integer.parseInt(Stringid);
                String newName = request.getParameter("boardname");
                String categoryString = request.getParameter("categorychosen");
                System.out.println(request.getParameter("isPrivate"));
                boolean isPrivate = "on".equals(request.getParameter("isPrivate"));
                boardCRUD.updateBoard(boardid, newName, Integer.parseInt(categoryString));
                boardCRUD.makePrivate(boardid, isPrivate);
                response.sendRedirect("profile");
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
