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
                <h3>Edit your Pin</h3>
                <div class="panel-body" style="background-color: white; padding: 50px 20px 40px 20px; width: 50%; border-radius: 20px; border:solid; border-width: 1px; border-color: #bbbbbb">
                    <form action="pinsSettings?pinId=${pin.getId()}" method="POST">
                        <div class="row">
                            <div class="col">
                                <!-- The Selection has to be looped from the exusting user selected categories 
                                currently hard coded-->
                                <!-- <label>Category</label>
                                <select class="form-control" name="categorychosen"> 
                                    <//c:forEach items = "${categoryList}" var="category" >
                                        <option value="${category.getId()}" <c:if test="${board.getCategory().getId() == category.getId()}">selected="selected"</c:if>>${category.getName()}</option>    
                                    <// /c:forEach>
                                </select> -->
                                <div class="dropdown-divider"></div>
                                <label>Recipe Name</label>
                                <input type="text" class="form-control" name="recipeTitle" value="${pin.getRecipeName()}">
                                <label>Description</label>
                                <textarea class="form-control" id="exampleTextarea" rows="3" name="recipe">${pin.getRecipe()}</textarea>
                                <div class="dropdown-divider"></div>                                
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