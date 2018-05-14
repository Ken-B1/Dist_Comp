<%-- 
    Document   : pinboard
    Created on : 18-mrt-2018, 11:29:31
    Author     : ken
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <%@include file="headers/header.jsp" %>
    </head>

    <body>
        <div class="container">
            <div class="row justify-content-md-center" style="margin-top: 10%">
                <a class="btn btn-primary btn-lg" href="Adminregistration">Add New Administrator</a>

            </div>
            <div class="dropdown-divider"></div>
            <div class="row justify-content-md-center">

                <a class="btn btn-dark btn-lg" href="Admincategories">Add New Category</a>
            </div>            
        </div>
    </body>
</html>
