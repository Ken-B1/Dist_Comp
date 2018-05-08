<%-- 
    Document   : signup
    Created on : 18-mrt-2018, 11:29:23
    Author     : ken
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Admin Registration</title>
        <%@include file="headers/header.jsp" %>
        <link rel="stylesheet" href="countrySelect/css/countrySelect.css">
    </head>
    <body style="background-color: #eeeeee">
        <div class="container">
            <div align="center" class="panel panel-default" style="margin-top: 5%;">
                <div class="panel-body" style="background-color: white; padding: 50px 20px 40px 20px; width: 60%; border-radius: 20px; border:solid; border-width: 1px; border-color: #bbbbbb">
                    <form action="Adminregistration" method="post">
                        <p class="font-weight-bold">Register an Administrator</p>
                        <div class="row">
                            <div class="col-sm">
                                <p>Admin Credentials</p>
                                <div class="form-group">
                                    <input type="text" c id="email" placeholder="Email" name="emal">                            
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="username" placeholder="Username" name="username">

                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control" id="password" placeholder="Password" name="password">

                                </div>
                            </div>

                            <div class="col-sm">
                                <p>Personal Details</p>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="fname" placeholder="First Name" name="fname">

                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="lname" placeholder="Last Name" name="lname">

                                </div>
                                <div class="form-group">                                    
                                    <input class="form-control" type="text" id="country" name="country">

                                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
                                    <script src="countrySelect/js/countrySelect.min.js"></script>
                                    <script>
                                        $("#country").countrySelect();
                                    </script> 
                                </div>
                                <div class="form-group">
                                    <select class="custom-select" name="gender">
                                        <option selected>Select Gender</option>
                                        <option value="M">Male</option>
                                        <option value="F">Female</option>
                                        <option value="U">Other</option>
                                    </select>  

                                </div>
                            </div>
                        </div>
                        <div class="dropdown-divider"></div>
                        <button type="submit" class="btn btn-primary">Register</button>
                    </form>
                    
                </div>
            </div>
        </div>
    </body>
</html>
