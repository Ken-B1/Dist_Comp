<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>        
        <title>Home - Pin4FooD</title>
        <%@include file="headers/header.jsp" %>
    </head>

    <body>   

        <form action="WriteMessage" method="GET">
            <input type="submit" value="Click"></input>
        </form>           
    </body>
</html>