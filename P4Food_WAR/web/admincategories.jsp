<%-- 
    Document   : pinboard
    Created on : 18-mrt-2018, 11:29:31
    Author     : ken
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/fa-svg-with-js.css">
        <script src="js/bootstrap.bundle.min.js"></script>
        <script defer src="js/fontawesome-all.js"></script>

        <link rel="icon" href="images\icons\logo.png">
        <%@include file="headers/header.jsp" %>
    </head>

    <body>
    <form action="Admincategories" method="POST">
        <p style="color: red;">${error}</p>
        <div class="form-group">
            <label>Category name:</label>
            <input type="text" name="category" />
        </div>
        <input type="submit" value="Create new category" class="btn btn-default" style="background-color: #ffffff;border: solid;  border-width: 1px; border-color: #aaaaaa">
        
    </form>
    <c:forEach items="${categorylist}" var="category" varStatus="loop">
        <p><c:out value="${category}"></c:out></p>
    </c:forEach>
    </body>
</html>