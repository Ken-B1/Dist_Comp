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
                        <h4><b>Messages</b></h4>  
                    </div>
                    <c:forEach items="${messages}" var="currentMessage">
                        <form action="FullMessage" method="GET">
                            <div class="card-body">
                                <h5 class="card-title"><c:if test="${currentMessage.getIsRead() == 0}"><b></c:if>${currentMessage.getSubject()}<c:if test="${currentMessage.getIsRead() == 0}"></b></c:if></h5>
                                <input type="text" value="${currentMessage.getId()}" name="id" style="display:none;">
                                <div class="dropdown-divider"></div>
                                <input type="submit" class="btn btn-primary" value="Read message">
                            </div>
                        </form>
                    </c:forEach>

                </div>
            </div>
        </div>

    </body>
</html>