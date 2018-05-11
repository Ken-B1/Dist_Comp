<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>        
        <title>Home - Pin4FooD</title>
        <%@include file="headers/header.jsp" %>        
    </head>

    <body>   
        <form action="boardSettings?boardId=${board.getId()}" method="POST">
            <div class="row">
                <div class="col">
                    <!-- The Selection has to be looped from the exusting user selected categories 
                    currently hard coded-->
                    <select class="form-control" name="categorychosen">
                        <c:forEach items = "${categoryList}" var="category" >
                            <option value="${category.getId()}" <c:if test="${board.getCategory().getId() == category.getId()}">selected="selected"</c:if>>${category.getName()}</option>    
                        </c:forEach>
                    </select>
                    <div class="dropdown-divider"></div>
                    <input type="text" class="form-control" placeholder="${board.getBoardname()}" name="boardname">
                    <input type="checkbox" name="isPrivate">Private</input>
                </div>                                       
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-default">Create</button>
            </div>
        </form>
    </body>
</html>