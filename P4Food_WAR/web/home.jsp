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
            <div class="irow"> 
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