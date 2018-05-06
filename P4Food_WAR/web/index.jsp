<%-- 
    Document   : index
    Created on : 18-mrt-2018, 11:29:13
    Author     : ken
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Login</title>
        <%@include file="headers/header.jsp" %>
        <script src="http://code.jquery.com/jquery.js"></script>
    </head>
    <body style="background-color: #eeeeee">
        <div class="container">
            <div align="center" class="panel panel-default" style="margin-top: 10%;">
                <div class="panel-body" style="background-color: white; padding: 50px 20px 40px 20px; width: 40%; border-radius: 20px; border:solid; border-width: 1px; border-color: #bbbbbb" >

                    <form action="login" method="post">
                        <p class="font-weight-bold">Welcome to Pinterest 4 Food</p>
                        <div class="alert alert-danger" id="signInError" role="alert">${loginfail}</div>
                        <script>
                            if($("#signInError").text()){
                                $("#signInError").show();                                
                            }else{
                                $("#signInError").hide();
                            }                           
                        </script>
                                            
                        <div class="form-group">
                          
                          <input type="text" class="form-control" id="exampleDropdownFormEmail2" placeholder="Username" name="username">
                        </div>
                        <div class="form-group">
                         
                          <input type="password" class="form-control" id="exampleDropdownFormPassword2" placeholder="Password" name="password">
                        </div>
                        <div class="form-check">
                          <input type="checkbox" class="form-check-input" id="dropdownCheck2">
                          <label class="form-check-label" for="dropdownCheck2">
                            Remember me
                          </label>
                        </div>
                        <div class="dropdown-divider"></div>
                        <button type="submit" class="btn btn-primary">Sign In</button>
                        
                      </form>
                    <p style="padding-top: 20px">No account yet? <a href="Registration">Sign up</a></p>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="btn btn-lg btn-block omb_btn-facebook">
                        <i class="fab fa-google-plus-g"></i>
                        <span class="hidden-xs">Login with Google</span>
                    </a>
                </div>
            </div>
        </div>
    </body>
</html>
