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
        <title>Login</title>
        <%@include file="headers/header.jsp" %>
    </head>
    <body style="background-color: #eeeeee">
        <div class="container">
        <div align="center" class="panel panel-default" style="margin-top: 10%;">
            <div class="panel-body" style="background-color: white; padding: 50px 20px 40px 20px; width: 40%; border-radius: 20px; border:solid; border-width: 1px; border-color: #bbbbbb">
                <form action="Adminregistration" method="post">
                    <div class="form-group">
                        <label>Email:</label>
                        <input type="text" name="email" />
                    </div>
                    <div class="form-group">
                        <label>Username:</label>
                        <input type="text" name="username" />
                    </div>
                    <div class="form-group">
                        <label>Password:</label>
                        <input type="password" name="password" />
                    </div>
                    <div class="form-group">
                        <label>First name:</label>
                        <input type="text" name="fname" />
                    </div>
                    <div class="form-group">
                        <label>Last name:</label>
                        <input type="text" name="lname" />
                    </div>
                    <div class="form-group">
                        <label>Country:</label>
                        <input type="text" name="country" />
                    </div>
                    <div class="form-group">
                        <label>Gender:</label>
                        <input type="text" name="gender" />
                    </div>
                    <button type="submit" class="btn btn-default" style="background-color: #ffffff;border: solid;  border-width: 1px; border-color: #aaaaaa">Submit</button>
                </form>
                <p style="padding-top: 20px"><a href="index.jsp">Sign in</a></p>
            </div>
        </div>
        </div>
    </body>
</html>
