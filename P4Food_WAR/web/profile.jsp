<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Boards - Eat What You Wish</title>
        <%@include file="headers/header.jsp" %>               
    </head>

    <body onload="showBoards()">

        <div class="container" id="wrapper" >
            <!-- Image and text -->            
            <div class="container">
                <div class="row">
                    <div class="col">
                        <h1 class="display-4">${userName}</h1>
                        <p class="lead">Add Your board and Share your pins</p>
                    </div>
                    <div class="col-6 col-md-4">
                        <!-- TO DO Add User Image here -->
                        <i class="fas fa-user-circle fa-6x"></i>

                    </div>                            
                </div>
                <!-- TO DO Following and Followers to updated -->
                <c:if test="${!ownProfile}">
                    <c:if test="${!isFollowed}">
                        <a href="FollowPerson?PersonId=${userId}" class="btn btn-info">Follow</a>
                    </c:if>
                    <c:if test="${isFollowed}">
                        <a href="UnfollowPerson?PersonId=${userId}" class="btn btn-danger">Unfollow</a>
                    </c:if>    
                    <form action="FriendRequest" method="POST" style="display: inline">
                        <input type="text" name="id" value="${userId}" style="display:none;"></input>
                        <c:if test="${!isFriend}">
                            <input type="text" name="type" value="request" style="display:none;"></input>
                            <input type="submit" class="btn btn-success" value="Friend"></button>
                        </c:if>
                        <c:if test="${isFriend}">
                            <input type="text" name="type" value="remove" style="display:none;"></input>
                            <input type="submit" class="btn btn-danger" value="Unfriend"></button>
                        </c:if>                            
                    </form>       
                    <a href="WriteMessage?id=${userId}" class="btn btn-primary">Message</a>
                </c:if>                  
                    <c:if test="${isAdmin && isBlocked == 0}">
                        <form action="adminBlock" method="POST">
                            <input type="text" name="PersonId" value="${userId}" style="display:none;"></input>
                            <input type="submit" class="btn btn-danger" value="Block from site"></button>
                        </form>
                    </c:if>   
                    <c:if test="${isAdmin && isBlocked == 1}">
                        <form action="Adminunblock" method="POST">
                            <input type="text" name="PersonId" value="${userId}" style="display:none;"></input>
                            <input type="submit" class="btn btn-danger" value="Unblock from site"></button>
                        </form>
                    </c:if>       
                <c:if test="${ownProfile}">    
                    <div class="row">
                        <div class="col"></div>
                        <div class="col-6 col-md-4"><a href="GetFollowing"><span class="badge badge-primary">${followingNum}</span></a> Following</div>
                    </div>
                    <div class="row">
                        <div class="col"></div>
                        <div class="col-6 col-md-4"><a href="GetFollowers"><span class="badge badge-primary">${followerNum}</span></a> Followers</div>
                    </div>
                </c:if>    
            </div>



            <div class="container">
                <!-- Tab links -->
                <div class="tab">
                    <button class="tablinks" onclick="openTab(event, 'boards')">Boards</button>
                    <button class="tablinks" onclick="openTab(event, 'topics')">Topics</button>

                </div>
                <div class="dropdown-divider"></div> 
                <!-- Tab content -->
                <div id="boards" class="tabcontent">
                    <div class="row">
                        <c:if test="${ownProfile}">
                            <div class="col-sm-4">
                                <div class="card bg-light mb-3" style="height:15rem;">
                                    <div class="card-header">Create Board</div>
                                    <div class="card-body" align="center" style="padding: 3.5rem">
                                        <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal"><i class="fas fa-plus-circle fa-lg"></i></button>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        
                        <c:forEach items = "${boardList}" var="board" >
                            <div class="col-sm-4">
                                <div class="card bg-light mb-3">     
                                    <a href="createPin?id=${board.getId()}" class="">
                                        <div class="card-body" style="max-width: 18rem;height:11.5rem;">                            
                                        </div>
                                    </a>
                                    <div class="card-header">
                                        <span class="d-inline-block text-truncate" style="max-width: 200px;">
                                          ${board.getBoardname()}
                                        <c:if test="${board.getIsprivate() == 1}">
                                            <i class="fas fa-user-secret" style="color: #cccccc"></i>
                                        </c:if>                                          
                                        </span>
                                        <c:if test="${ownProfile}">
                                            <span class="float-right"><a href="boardSettings?boardId=${board.getId()}" alt="Edit this board"><i class="fas fa-pencil-alt fa-lg"></a></i>
                                            <span class="float-right"><a href="deleteBoard?boardId=${board.getId()}" alt="Edit this board" style="margin-left: 10px;"><i class="fas fa-trash-alt fa-lg" style="color: red"></a></i>
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
                                    <form action="createBoard" method="post">
                                        <div class="row">
                                            <div class="col">
                                                <!-- The Selection has to be looped from the exusting user selected categories 
                                                currently hard coded-->
                                                <select class="form-control" name="categorychosen">
                                                    <option value="NoCategory">Select Category</option> 
                                                    <c:forEach items = "${categoryList}" var="category" >
                                                        <option value="${category.getId()}">${category.getName()}</option>    
                                                    </c:forEach>
                                                </select>
                                                <div class="dropdown-divider"></div>
                                                <input type="text" class="form-control" placeholder="Name of the Board" name="boardname">
                                            </div>                                       
                                        </div>
                                        <div class="modal-footer">
                                            <button type="submit" class="btn btn-default">Create</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>  
                </div>

                <div id="topics" class="tabcontent">
                    <div class="row">
                        <c:forEach items = "${userCategories}" var="category" >
                            <div class="col-sm-3">
                                <div class="card bg-light mb-3" style="max-width: 18rem;height:15rem;">
                                    <div class="card-body" align="center" style="padding: 3.5rem">                            
                                    </div>
                                    <div class="card-header">${category.getName()}</div>
                                </div>
                            </div>
                        </c:forEach> 
                    </div>    
                </div>
            </div>
        </div>
        <script src="js/boards.js"></script>
    </body>
</html>