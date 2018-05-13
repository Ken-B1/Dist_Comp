<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>        
        <title>Home - Pin4FooD</title>
        <%@include file="headers/header.jsp" %>        
    </head>

    <body>
        <b>Friends:</b>
        <c:forEach items = "${friends}" var="friend" >
            <a href="profile?username=${friend.getUsername()}">${friend.getUsername()}</a>
            <form action="FriendRequest" method="post">
                <input type="text" value="${friend.getId()}" name="id" style="display:none;"></input>
                <input type="text" value="remove" name="type" style="display:none;"></input>
                <input type="submit" value="unfriend"></input>
            </form>
        </c:forEach>
            
        <b>FriendRequests:</b>
        
        <c:forEach items = "${requests}" var="friend" >
            <a href="profile?username=${friend.getUsername()}">${friend.getUsername()}</a>
            <form action="FriendRequest" method="post">
                <input type="text" value="${friend.getId()}" name="id" style="display:none;"></input>
                <input type="text" value="accept" name="type" style="display:none;"></input>
                <input type="submit" value="accept"></input>
            </form>
            <form action="FriendRequest" method="post">
                <input type="text" value="${friend.getId()}" name="id" style="display:none;"></input>
                <input type="text" value="deny" name="type" style="display:none;"></input>
                <input type="submit" value="deny"></input>
            </form>
        </c:forEach>
            <hr>
            <p>This is an example friendrequest where currentuser sends request to user 2. This functionality should be moved to the profile page for the requested user:</p>
        <form action="FriendRequest" method="post">
                <input type="text" value="2" name="id" style="display:none;"></input>
                <input type="text" value="request" name="type" style="display:none;"></input>
                <input type="submit" value="requestTest"></input>
        </form>     
    </body>
</html>