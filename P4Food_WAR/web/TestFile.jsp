<%-- 
    Document   : TestFile
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
    </head>
    <body style="background-color: #eeeeee">
        <form action="FollowBoard" method="post">
            
            <input type="submit" name="BoardId" value="1">Click</input>
        </form>
        
        <form action="UnfollowBoard" method="post">
            
            <input type="submit" name="BoardId" value="1">Click</input>
        </form>
        
        <form action="FollowPerson" method="post">
            
            <input type="submit" name="PersonId" value="2">Click</input>
        </form>
        
        <form action="UnfollowPerson" method="post">
            
            <input type="submit" name="PersonId" value="2">Click</input>
        </form>
    </body>
</html>
