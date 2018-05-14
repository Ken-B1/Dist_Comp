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
    <body>
        <div class="container">
            <div class="row" style="margin-left: 5%">
                <div class="col-sm-3">
                    <div class="list-group">
                        <a href="#" class="list-group-item list-group-item-action active">
                            Pins
                        </a>
                        <c:forEach items="${resultingPins}" var="currentPin">
                            <a href="#" class="list-group-item list-group-item-action">${currentPin.getRecipeName()}</a>
                        </c:forEach>
                    </div>
                </div>

                <div class="col-sm-3">
                    <div class="list-group">
                        <a href="#" class="list-group-item list-group-item-action active">
                            Boards
                        </a>
                        <c:forEach items="${resultingBoards}" var="currentBoard">
                            <a href="#" class="list-group-item list-group-item-action">${currentBoard.getBoardname()}</a>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="list-group">
                        <a href="#" class="list-group-item list-group-item-action active">
                            Users
                        </a>
                         <c:forEach items="${resultingUsers}" var="currentUser">
                            <a href="#" class="list-group-item list-group-item-action">${currentUser.getUsername()}</a>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
