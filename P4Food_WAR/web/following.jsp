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
                <p><b>Following:  </b></p>
            </div>
            <c:forEach items = "${following}" var="follower" >
            <div class="row">
                    <div class="col-4">
                        <a href="profile?username=${follower.getAccount().getUsername()}">${follower.getAccount().getUsername()}</a>
                    </div>
                    <div class="col-2">
                        <form action="UnfollowPerson" method="get">
                            <input type="text" value="${follower.getAccount().getId()}" name="PersonId" style="display:none;"></input>
                            <input type="text" value="accept" name="type" style="display:none;"></input>
                            <input class="btn btn-outline-danger btn-sm" type="submit" value="unfollow"></input>
                        </form>
                    </div>
            </div>     
            <hr style="width: 80%; margin-left:0px;"/>
            </c:forEach>          
        </div>
    </body>
</html>