/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Business.boardCrudBean;
import Business.databaseConnector;
import Entities.Account;
import Entities.Board;
import Entities.Categories;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import remotesettings.setRemote;
import services.AccountBeanInterface;

/**
 *
 * @author ken
 */
@WebServlet(name = "profileServlet", urlPatterns = {"/profile"})
public class profileServlet extends HttpServlet {
    @EJB 
    private boardCrudBean boardBean;
    
    @EJB
    private databaseConnector connector;
    
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
            AccountBeanInterface currentUser = (AccountBeanInterface)request.getSession().getAttribute("user");
            String requestedUser = request.getParameter("username");
            List<Board> userBoards;
            if(requestedUser == null || currentUser.getAccount().getUsername().equals(requestedUser)){
                // The requested profile is the current user's profile
                request.setAttribute("ownProfile", true);
                userBoards = boardBean.getBoardsForUser(currentUser.getAccount());
            }else{
                // The requested profile is another user's profile
                request.setAttribute("ownProfile", false);
                Account acc = currentUser.getAccount(requestedUser);
                userBoards = boardBean.getBoardsForUser(acc);
                request.setAttribute("userId", acc.getId());
            }


            Collection<Categories> userCategories = currentUser.getUserCategories();
            request.setAttribute("boardList", userBoards);
            request.setAttribute("userCategories", userCategories);
            //request.setAttribute("followerNum", currentUser.getNumFollowers());
            request.setAttribute("followerNum", 0);
            //request.setAttribute("followingNum", currentUser.getNumFollowing());
            request.setAttribute("followingNum", 0);


            List<Categories> allCategories = connector.getAllCategories();
            request.setAttribute("categoryList", allCategories);
            request.setAttribute("isAdmin", currentUser.getAccount().getAdmin());
            request.getRequestDispatcher("profile.jsp").forward(request, response);
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
