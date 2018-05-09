<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>        
        <title>Home - Pin4FooD</title>
        <%@include file="headers/header.jsp" %>
    </head>

    <body>   
        <form action="WriteMessage" method="Post">
            <p><b>Sender:</b> ${message.getSender().getUsername()}</p>
            <p><b>Receiver:</b> <input type="text" name="receiver"> </p>
            <p><b>Subject: </b> <input type="text" name="subject"> </p>
            <p><b>Message:</b> <input type="text" name="content"> </input></p>
            <input type="submit" value="Send"></input>
            
        </form>
    </body>
</html>