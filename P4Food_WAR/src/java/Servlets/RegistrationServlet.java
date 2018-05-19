/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Business_Utility.Status;
import java.io.IOException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import remotesettings.setRemote;
import services.RegistrationBeanInterface;

/**
 *
 * @author ken
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/Registration"})
public class RegistrationServlet extends HttpServlet {

    private RegistrationBeanInterface createAccount;
    
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
        response.sendRedirect("signup.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * Attempt to create an account
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            ic = new InitialContext(setRemote.setProperties());   
            
            createAccount = (RegistrationBeanInterface) ic.lookup("java:global/statistics_EJB/Registration!services.RegistrationBeanInterface");
            Status valid;

            String email = request.getParameter("email");
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String country = request.getParameter("country");
            String gender = request.getParameter("gender");

            //Checks for validity of input.
            if(email.isEmpty()){valid= new Status(3, "Email adress is required");}
            else if(userName.isEmpty()){valid = new Status(4, "Username is required");}
            else if(password.isEmpty()){valid = new Status(5, "Password cannot be empty");}
            else if(fname.isEmpty()){valid = new Status(6, "First name is required");}
            else if(lname.isEmpty()){valid = new Status(7, "Last name is required");}
            else if(gender.isEmpty()){valid = new Status(8, "Gender is required");}
            else if(userName.length() > 45){valid = new Status(9, "Username cannot be longer than 45 characters");}
            else if(password.length() > 45){valid = new Status(11, "password cannot be longer than 45 characters");}
            else if(fname.length() > 45){valid = new Status(12, "fname cannot be longer than 45 characters");}
            else if(lname.length() > 45){valid = new Status(13, "lname cannot be longer than 45 characters");}
            else if(email.length() > 45){valid = new Status(10, "email cannot be longer than 45 characters");}
            else{
                //Check if email and username already exist
                valid = createAccount.validate(email, userName);
            }

            if(valid.getStatusCode() != 0){
                //Account with email or username already exists
                request.setAttribute("Status", valid);
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            }else{
                createAccount.createAccount(email, userName, password, fname, lname, country, gender);    

                response.sendRedirect("index.jsp");
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
