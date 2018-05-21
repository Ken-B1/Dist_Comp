<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Pins - Pin4FooD</title>
        <%@include file="headers/header.jsp" %>       
    </head>

    <body>        
        <div class="container" id="wrapper">
            <!-- Image and text -->
            <div class="container">
                <div class="row">
                    <div class="col">
                        <h1 class="display-4">${boardName}</h1>
                    </div>
                    <div class="col-6 col-md-4">
                        <!-- TO DO Add User Image here -->
                        <i class="fas fa-user-circle fa-6x"></i>

                    </div>                            
                </div>
                
                <div class="row">
                    <div class="col"></div>
                    <div class="col-6 col-md-4"><a href="#"><span class="badge badge-primary">${followingNum}</span></a> Following</div>
                </div>
                <div class="row">
                    <div class="col"></div>
                    <div class="col-6 col-md-4"><a href="#"><span class="badge badge-primary">${followerNum}</span></a> Followers</div>
                </div>
                <c:if test="${!isOwner}">
                <div class="row">
                    <div class="col"></div>
                    <div class="col-6 col-md-4">
                        <c:if test="${!isFollowing}">
                            <form action="FollowBoard" method="POST">
                                <input type="text" value="${boardId}" style="display:none" name="BoardId"></input>
                                <input class="btn btn-info" type="submit" value="Follow"></input>
                            </form>
                        </c:if>
                        <c:if test="${isFollowing}">
                            <form action="UnfollowBoard" method="POST">
                                <input type="text" value="${boardId}" style="display:none" name="BoardId"></input>
                                <input class="btn btn-danger" type="submit" value="Unfollow"></input>
                            </form>                            
                        </c:if>
                    </div>
                </div>
                </c:if>
            </div>

            <div class="container">
                <!-- Tab links -->
                <div class="tab">
                    <button class="tablinks active">Pins</button>
                </div>

            </div>
            <div class="dropdown-divider"></div>

            <div class="row">
                <c:if test="${isOwner}">
                    <div class="col-sm-4">
                        <div class="card bg-light mb-3" style="height:15rem;">
                            <div class="card-header">Create Pin</div>
                            <div class="card-body" align="center" style="padding: 3.5rem">
                                <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal"><i class="fas fa-plus-circle fa-lg"></i></button>
                            </div>
                        </div>
                    </div>
                </c:if>
                
                <c:forEach items = "${pinList}" var="pin" >
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
                                    <c:if test="${isOwner}">
                                        <span class="float-right"><a href="pinsSettings?pinId=${pin.getId()}" alt="Edit this board"><i class="fas fa-pencil-alt fa-lg"></a></i>
                                    </c:if>
                                </span>                                
                            </div>
                        </div>
                    </div> 
                </c:forEach>   
            </div>


            <!-- Modal -->
            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><i class="fas fa-times-circle fa-lg"></i></button>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <form action="createPin" method="post" enctype="multipart/form-data" id="js-upload-form">
                                    <input type="text" name="id" value="${boardId}" style="display:none"></input>
                                    <div class="col">
                                        <!-- Standard Form -->
                                        <h5>Select images from your computer</h5>                                                
                                        <div class="form-inline">
                                            <div class="form-group">
                                                <input type="file" name="file" id="js-upload-files" multiple>
                                            </div>
                                        </div> 
                                        <div class="dropdown-divider"></div>
                                        <label for="exampleTextarea"><i class="fas fa-tag fa-sm"></i> Add Name</label>
                                        <input type="text" class="form-control" placeholder="Add Your Recipe Name" name="recipeTitle">
                                        <div class="dropdown-divider"></div>
                                        <label for="exampleTextarea"><i class="fas fa-pen-square fa-sm"></i> Description</label>
                                        <textarea class="form-control" id="exampleTextarea" rows="3" name="recipe"></textarea>
                                        <div class="dropdown-divider"></div> 
                                        <button type="submit" class="btn btn-sm btn-primary" id="js-upload-submit">Upload Pins</button>                                                                                                                                                               
                                    </div>                                           
                                </form>                                        
                            </div>
                        </div>                            
                    </div>             
                </div>
            </div>   
        </div><!-- Container ends here-->  
    </body>
</html>