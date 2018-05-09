<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>        
        <title>Home - Pin4FooD</title>
        <%@include file="headers/header.jsp" %>
    </head>

    <body>   
        <p><b>Sender:</b> ${message.getSender().getUsername()}</p>
        <p><b>Receiver:</b> ${message.getReceiver().getUsername()}</p>
        <p><b>Subject: </b> ${message.getSubject()}</p>
        <p><b>Message:</b> ${message.getContent()}</p>
        <form action="WriteMessage" method="GET">
            <input type="submit" value="Click"></input>
        </form>           
    </body>
</html>