<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>        
        <title>Home - Pin4FooD</title>
        <%@include file="headers/header.jsp" %>
    </head>

    <body>   
        <c:forEach items="${messages}" var="currentMessage">
            <form action="FullMessage" method="GET">
                <input type="text" value="${currentMessage.getId()}" name="id" style="display:none;"></input>
                <input type="submit" value="${currentMessage.getSubject()}">${currentMessage.getId()}</input>
            </form>
        </c:forEach>
    </body>
</html>