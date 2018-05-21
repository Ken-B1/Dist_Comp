<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>        
        <title>Home - Pin4FooD</title>
        <%@include file="headers/header.jsp" %>     
    </head>

    <body>
        <div class="container">
            <div class="row">           
                <p><b>FriendRequests:</b></p>
            </div>
            <c:forEach items = "${requests}" var="friend" >
            <div class="row">
                    <div class="col-4">
                        <a href="profile?username=${friend.getUsername()}">${friend.getUsername()}</a>
                    </div>
                    <div class="col-2">
                        <form action="FriendRequest" method="post">
                            <input type="text" value="${friend.getId()}" name="id" style="display:none;"></input>
                            <input type="text" value="accept" name="type" style="display:none;"></input>
                            <input class="btn btn-outline-success btn-sm" type="submit" value="accept"></input>
                        </form>
                        <form action="FriendRequest" method="post">
                            <input type="text" value="${friend.getId()}" name="id" style="display:none;"></input>
                            <input type="text" value="deny" name="type" style="display:none;"></input>
                            <input class="btn btn-outline-danger btn-sm" type="submit" value="deny"></input>
                        </form>
                    </div>
            </div>     
            <hr style="width: 80%; margin-left:0px;"/>
            </c:forEach>   
            
            <hr style="background-color: black;margin-left: 0px;">
            <div class="row">
                <p><b>Friends:</b></p>
            </div>
            <c:forEach items = "${friends}" var="friend" >
                <div class="row">
                    <div class="col-4">                    
                    <a href="profile?username=${friend.getUsername()}">${friend.getUsername()}</a>
                    </div>
                    <div class="col-2">
                    <form action="FriendRequest" method="post">
                        <input type="text" value="${friend.getId()}" name="id" style="display:none;"></input>
                        <input type="text" value="remove" name="type" style="display:none;"></input>
                        <input class="btn btn-outline-danger btn-sm" type="submit" value="unfriend"></input>
                    </form>
                    </div>
                </div>  
            <hr style="width: 80%; margin-left:0px;"/>                        
            </c:forEach>          
        </div>
    </body>
</html>