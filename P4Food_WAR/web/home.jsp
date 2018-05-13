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
            <div class="row">
                <c:forEach items = "${pinlist}" var="pin" >
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
                </c:forEach>   
            </div>
        </div>

        <c:if test="${!hasCategories}">
            <script type="text/javascript">
                window.onload = function () {
                    $('#myModal').modal('show');
                };
            </script>
        </c:if>
        <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><i class="fas fa-times-circle fa-lg"></i></button>
                    </div>

                    <form action="ChooseInitialCategories" method="post">
                        <div class="modal-body">
                            <div class="row">
                                <div class="col">
                                    <c:set var="errorMessage" value="${NotEnoughCategories ? '' : 'Select at least 3 categories'}" />
                                    <h5>Select atleast three categories</h5>  
                                    <c:forEach items = "${Categories}" var="category" >  
                                        <div class="custom-control custom-checkbox">
                                            <input type="checkbox" class="custom-control-input" name="CategorySelect" id="${category.getName()}" value="${category.getId()}">
                                            <label class="custom-control-label" for="${category.getName()}">${category.getName()}</label>
                                        </div>
                                    </c:forEach>
                                    <div class="dropdown-divider"></div>                                    
                                </div>                                       
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-default">Add</button>
                            </div>
                        </div>
                    </form>
                </div>

            </div>

            <!-- Display all the boards that are created under different category-->

        </div>                   
    </body>
</html>