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
                <p><b>Followers: </b></p>
            </div>
            <c:forEach items = "${followers}" var="follower" >
            <div class="row">
                    <div class="col-4">
                        <a href="profile?username=${follower.getAccount1().getUsername()}">${follower.getAccount1().getUsername()}</a>
                    </div>
                    <div class="col-2">
                        <c:if test="${follower.getIsBlocked() == 0}">
                        <form action="BlockPerson" method="post">
                            <input type="text" value="${follower.getAccount1().getId()}" name="PersonId" style="display:none;"></input>
                            <input type="text" value="accept" name="type" style="display:none;"></input>
                            <input class="btn btn-outline-danger btn-sm" type="submit" value="block"></input>
                        </form>
                        </c:if>
                        <c:if test="${follower.getIsBlocked() == 1}">
                        <form action="UnblockPerson" method="post">
                            <input type="text" value="${follower.getAccount1().getId()}" name="PersonId" style="display:none;"></input>
                            <input type="text" value="accept" name="type" style="display:none;"></input>
                            <input class="btn btn-danger btn-sm" type="submit" value="unblock"></input>
                        </form>
                        </c:if>                        
                    </div>
            </div>     
            <hr style="width: 80%; margin-left:0px;"/>
            </c:forEach>          
        </div>
    </body>
</html>