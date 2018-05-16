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
                        <form action="WriteMessage" method="GET">
                            <textarea class="form-control" aria-label="With textarea" value="${message.getSender().getId()}" name="id" style="display:none;"></textarea>
                            <input type="submit" class="btn btn-primary" value="Reply"></input>                   
                        </form> 
                    </div>

                </div>
            </div>
        </div>


    </body>
</html>