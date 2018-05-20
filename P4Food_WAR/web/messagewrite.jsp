<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>        
        <title>Home - Pin4FooD</title>
        <%@include file="headers/header.jsp" %>
    </head>

    <body>   
        <div class="container">
            <div class="w-50" style="margin-left: 25%">
                <div class="card">
                    <form action="WriteMessage?recid=${receiverId}" method="Post">
                        <div class="card-header">
                            <b>Receiver:</b> ${receiverName}
                        </div>
                        <div class="card-header">
                            <p style="color: red">${error}</p>
                            <b>Subject: </b> <input class="form-control" type="text" name="subject">
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">Message:</h5>
                            <textarea class="form-control" aria-label="With textarea" name="content"></textarea>
                            <div class="dropdown-divider"></div>
                            <input type="submit" class="btn btn-primary" value="Send"></input>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>