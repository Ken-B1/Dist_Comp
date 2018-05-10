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
        <%@include file="headers/header.jsp" %>
    </head>
    <body style="background-color: #eeeeee">
        <div>
            <b>Pins:</b>
        <c:forEach items="${resultingPins}" var="currentPin">
            <p>${currentPin.getRecipeName()}</p>
        </c:forEach>
        </div>
        
        <div>
        <b>Boards</b>
        <c:forEach items="${resultingBoards}" var="currentBoard">
            <p>${currentBoard.getBoardname()}</p>
        </c:forEach>
        </div>
        
        <div>
        <b>Users:</b>
        <c:forEach items="${resultingUsers}" var="currentUser">
            <p>${currentUser.getUsername()}</p>
        </c:forEach>
        </div>
    </body>
</html>
