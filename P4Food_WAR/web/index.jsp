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
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </head>
    <body style="background-color: #eeeeee">
        <div class="container">
        <div align="center" class="panel panel-default" style="margin-top: 10%;">
            <div class="panel-body" style="background-color: white; padding: 50px 20px 40px 20px; width: 40%; border-radius: 20px; border:solid; border-width: 1px; border-color: #bbbbbb">
                <form action="login" method="post">
                    <div class="form-group">
                        <label>Username:</label>
                        <input type="text" name="username" />
                    </div>
                    <div class="form-group">
                        <label>Password:</label>
                        <input type="password" name="password" />
                    </div>
                    <div>
                        <p style="color: red">${loginfail}</p>
                    </div>
                    
                    <input type="submit" value="Submit" class="btn btn-default" style="background-color: #ffffff;border: solid;  border-width: 1px; border-color: #aaaaaa">
                </form>
                <p style="padding-top: 20px">No account yet? <a href="Registration">Sign up</a></p>
            </div>
        </div>
        </div>
    </body>
</html>
