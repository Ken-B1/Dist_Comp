<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Boards - Pin4FooD</title>
        <%@include file="headers/header.jsp" %>               
        <script src="js/boards.js"></script>
    </head>

    <body>

        <div class="container" id="wrapper" >
            <!-- Image and text -->            
            <div class="container">
                <div class="row">
                    <div class="col">
                        <h1 class="display-4">Pin4FooD</h1>
                        <p class="lead">Add Your board and Share your pins</p>
                    </div>
                    <div class="col-6 col-md-4">
                        <!-- TO DO Add User Image here -->
                        <i class="fas fa-user-circle fa-6x"></i>

                    </div>                            
                </div>
                <!-- TO DO Following and Followers to updated -->
                <div class="row">
                    <div class="col"></div>
                    <div class="col-6 col-md-4"><a href="#"><span class="badge badge-primary">0</span></a> Following</div>
                </div>
                <div class="row">
                    <div class="col"></div>
                    <div class="col-6 col-md-4"><a href="#"><span class="badge badge-primary">0</span></a> Followers</div>
                </div>
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
                        <div class="col-sm">
                            <div class="card bg-light mb-3" style="max-width: 18rem;height:15rem;">
                                <div class="card-header">Create Board</div>
                                <div class="card-body" align="center" style="padding: 3.5rem">
                                    <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal"><i class="fas fa-plus-circle fa-lg"></i></button>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm">
                            <a href="#" class="">
                                <div class="card bg-light mb-3" style="max-width: 18rem;height:15rem;">
                                    <div class="card-body" align="center" style="padding: 3.5rem">                            
                                    </div>
                                    <div class="card-header">Board Title</div>
                                </div>
                            </a>
                        </div>
                        <div class="col-sm">
                            <a href="#" class="">
                                <div class="card bg-light mb-3" style="max-width: 18rem;height:15rem;">                        
                                    <div class="card-body" align="center" style="padding: 3.5rem">                            
                                    </div>
                                    <div class="card-header">Board Title</div>
                                </div>
                            </a>
                        </div>
                        <div class="col-sm">
                            <a href="#" class="">
                                <div class="card bg-light mb-3" style="max-width: 18rem;height:15rem;">                        
                                    <div class="card-body" align="center" style="padding: 3.5rem">                            
                                    </div>
                                    <div class="card-header">Board Title</div>
                                </div>
                            </a>
                        </div>
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
                    <h3>Topics</h3>
                    <p>Topics are listed here</p> 
                </div>
            </div>

            <div class="row">
                <c:forEach items = "${boardList}" var="board" >
                    <div class="col">
                        <svg width="260" height="260">
                        <rect width="250" height="250" style="fill:grey;stroke:black;stroke-width:2;fill-opacity:0.1;stroke-opacity:0.1" /> >
                        <text fill="#000000" stroke="black" font-size="30" font-family="Verdana" x="65" y="120">${board.getBoardname()}</text>
                        </svg>
                    </div>
                </c:forEach> 
            </div>  
        </div>
        <script src="js/boards.js"></script>
    </body>
</html>