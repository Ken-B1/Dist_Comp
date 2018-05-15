<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>        
        <title>Home - Pin4FooD</title>
        <%@include file="headers/header.jsp" %>        
    </head>

    <body>   
        <div class="container">
            <!-- Image and text -->
            <% int pinCount = 0; %>
            <div class="row">
                <c:forEach items = "${pinlist}" var="pin" >
                    <% pinCount = pinCount+1; 
                        if(pinCount<=6){
                    %>
                    
                    <div class="col-sm-4">
                        <div class="card bg-light mb-3">     
                            <a href="fullRecipe?id=${pin.getId()}" class="">
                                <div class="card-img-top " style="max-width: 100%;height:11.5rem;">
                                    <img src="Image?filename=${pin.getLocation()}" style="width: 100%; height: 100%;"></img>
                                </div>
                            </a>
                            <div class="card-header">
                                <span class="d-inline-block text-truncate" style="max-width: 200px;">
                                    ${pin.getRecipeName()}
                                </span>                                
                            </div>
                        </div>
                    </div> 
                             <% } %>
                </c:forEach>   
            </div>
            
            <!-- <div class="irow">
                <div class="column">
                    <img src="images\users\username\African\BoardName\1.jpg" style="width:100%">
                    <img src="images\users\username\African\BoardName\2.jpg" style="width:100%">
                    <img src="images\users\username\African\BoardName\3.jpg" style="width:100%">
                    <img src="images\users\username\African\BoardName\4.jpg" style="width:100%">                    
                </div>  
                <div class="column">
                    <img src="images\users\username\Belgium\BoardName\1.jpg" style="width:100%">
                    <img src="images\users\username\Belgium\BoardName\2.jpg" style="width:100%">
                    <img src="images\users\username\Belgium\BoardName\3.jpg" style="width:100%">
                    <img src="images\users\username\Belgium\BoardName\4.jpg" style="width:100%">
                    <img src="images\users\username\Spanish\BoardName\2.jpg" style="width:100%">                    
                </div>
                <div class="column">
                    <img src="images\users\username\French\BoardName\1.jpg" style="width:100%">
                    <img src="images\users\username\French\BoardName\2.jpg" style="width:100%">
                    <img src="images\users\username\French\BoardName\3.jpg" style="width:100%">
                    <img src="images\users\username\French\BoardName\4.jpg" style="width:100%">
                    <img src="images\users\username\Spanish\BoardName\4.jpg" style="width:100%">
                </div>
                <div class="column">
                    <img src="images\users\username\Indian\BoardName\1.jpg" style="width:100%">
                    <img src="images\users\username\Indian\BoardName\2.jpg" style="width:100%">
                    <img src="images\users\username\Indian\BoardName\3.jpg" style="width:100%">
                    <img src="images\users\username\Indian\BoardName\4.jpg" style="width:100%">  
                    <img src="images\users\username\African\BoardName\2.jpg" style="width:100%">                  
                    <img src="images\users\username\Spanish\BoardName\1.jpg" style="width:100%">
                </div>
            </div> -->
        </div>
    </body>
</html>