<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>        
        <title>Home - Pin4FooD</title>
        <%@include file="headers/header.jsp" %>        
    </head>

    <body>   
        <div class="container">
            <div class="panel panel-primary" style="margin-left: 30%;margin-top: 5%" >
                <h3>Edit your board</h3>
                <div class="panel-body" style="background-color: white; padding: 50px 20px 40px 20px; width: 50%; border-radius: 20px; border:solid; border-width: 1px; border-color: #bbbbbb">
                    <form action="boardSettings?boardId=${board.getId()}" method="POST">
                        <div class="row">
                            <div class="col">
                                <!-- The Selection has to be looped from the exusting user selected categories 
                                currently hard coded-->
                                <label>Category</label>
                                <select class="form-control" name="categorychosen">
                                    <c:forEach items = "${categoryList}" var="category" >
                                        <option value="${category.getId()}" <c:if test="${board.getCategory().getId() == category.getId()}">selected="selected"</c:if>>${category.getName()}</option>    
                                    </c:forEach>
                                </select>
                                <div class="dropdown-divider"></div>
                                <label>Board Name</label>
                                <input type="text" class="form-control" name="boardname" value="${board.getBoardname()}">
                                <div class="dropdown-divider"></div>
                                <input type="checkbox" name="isPrivate">Private</input>
                            </div>                                       
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Save</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>