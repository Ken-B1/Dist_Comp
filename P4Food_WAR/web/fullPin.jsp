<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>        
        <title>Home - Pin4FooD</title>
        <%@include file="headers/header.jsp" %>        
    </head>

    <body>   
        <div class="container">
            <figure class="figure">
                               
                <img src="Image?filename=${pin.getLocation()}" class="figure-img img-fluid rounded" alt="A generic square placeholder image with rounded corners in a figure.">
                <figcaption class="figure-caption"></figcaption>
            </figure>
            <div class="card mb-3">
                
                <div class="card-body">
                    <h5 class="card-title">${pin.getRecipeName()}</h5>
                    <p class="card-text">${pin.getRecipe()}</p>
                    <p class="card-text"><small class="text-muted">You ${pin.getRecipeName()} Pin is successfully uploaded.</small></p>
                </div>
            </div>            
        </div>
    </body>
</html>