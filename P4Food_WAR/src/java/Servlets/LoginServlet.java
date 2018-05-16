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
import javax.naming.InitialContext;
import javax.naming.NamingException;
import remotesettings.setRemote;
import services.AccountBeanInterface;
import services.LoginBeanInterface;

/**
 *
 * @author ken
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    //Class that handles login requests
    
    private LoginBeanInterface loginBean;

    /**
    * The context to be used to perform lookups of remote beans
    */
    private static InitialContext ic;    
    /**
    * The context to be used to perform lookups of remote beans
    */
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
        request.getRequestDispatcher("index.jsp").forward(request, response);
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
            
            loginBean = (LoginBeanInterface) ic.lookup("java:global/statistics_EJB/LoginBean!services.LoginBeanInterface"); 
            AccountBeanInterface accountBean = (AccountBeanInterface) ic.lookup("java:global/statistics_EJB/AccountBean!services.AccountBeanInterface");
            // Log the user into his/her account and redirect depending on outcome
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            int accountId = loginBean.login(username, password);     
            accountBean.setAccount(accountId);
            System.out.println(accountId);
            
            if(accountId != -1) {
                request.getSession().setAttribute("user", accountBean);
                request.getSession().setAttribute("userid",username);
                response.sendRedirect("pinboard");
            }else{
                request.setAttribute("loginfail", "Incorrect username/password");
                request.getRequestDispatcher("index.jsp").forward(request, response);
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
