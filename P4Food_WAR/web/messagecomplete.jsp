<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>        
        <title>Messages - Pin4FooD</title>
        <%@include file="headers/header.jsp" %>
    </head>

    <body>   
        <div class="container">
            <div class="w-50" style="margin-left: 25%">
                <div class="card">
                    <div class="card-header">
                        <b>Sender:</b> ${message.getSender().getUsername()}
                    </div>
                    <div class="card-header">
                        <b>Receiver:</b> ${message.getReceiver().getUsername()}
                    </div>
                    <div class="card-header">
                        <b>Subject: </b> ${message.getSubject()}
                    </div>

                    <div class="card-body">
                        <b>Message:</b> <h5 class="card-title">${message.getContent()}</h5>      

                    </div>

                    <div class="card-body">
                        <a href="WriteMessage?id=${message.getSender().getId()}"><button class="btn btn-primary" value="Reply">Reply</button></a>                   
                    </div>

                </div>
            </div>
        </div>


    </body>
</html>