<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/fa-svg-with-js.css">
        <link rel="stylesheet" href="css/imageStyle.css">
        <script src="js/bootstrap.bundle.min.js"></script>
        <script defer src="js/fontawesome-all.js"></script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <title>Pins - Pin4FooD</title>
        <link rel="icon" href="images\icons\logo.png">

        <style>
                .badge-notify{
                background:red;
                position:relative;
                top: -15px;
                left: -25px;
                }
                .btn-links{
                font-weight: 400;
                color: #007ff;
                background-color:transparent;
            }
        </style>

    </head>

    <body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">
                <img src="images\icons\logo.png" width="30" height="30" class="d-inline-block align-top" alt="">
                Pin4FooD
            </a>   
            <div class="collapse navbar-collapse" id="navbarSupportedContent"> 
               <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="pinboard"><i class="fas fa-home fa-sm"></i> Home</a>
                    </li>
                    <li class="nav-item active">
                            <a class="nav-link" href="createBoard"><i class="fab fa-flipboard fa-sm"></i> Boards</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="createPin"><i class="fas fa-thumbtack fa-sm"></i> Pins</a>
                    </li>
                    <li>
                        <form class="form-inline my-2 my-lg-0">
                            <input class="form-control mr-sm-2" type="search" placeholder="Search">
                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                        </form>                      
                    </li>
                </ul>
                <c:if test="${isAdmin}"><a class="nav-item active" href="admin">Admin</a></c:if>
                <a class="nav-item" href="settings"><i class="fas fa-user-circle fa-2x"></i></a>
                <a class="nav-item active" href="statistics">Statistics</a>
                <!-- TO DO Notification pop up icon-->
                <button class="btn btn-links" style="font-size:36px;" data-toggle="dropdown"  aria-haspopup="true" aria-expanded="false" >
                        <i class="far fa-bell"></i>
                </button>
                <span class="badge badge-notify">0</span>
                <button class="btn btn-outline-success my-2 my-sm-0" >Login</button>
            </div> 
        </nav>
        <div class="dropdown-divider"></div>
        <div class="container">
           <!-- Image and text -->
            

            <div class="jumbotron">
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
                            <div class="col-6 col-md-4"><span class="badge badge-primary">0</span> Following</div>
                        </div>
                        <div class="row">
                                <div class="col"></div>
                                <div class="col-6 col-md-4"><span class="badge badge-primary">0</span> Followers</div>
                            </div>
                </div>

                <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal"><i class="fas fa-plus-circle fa-lg"></i> Create Pin</button>

                    <!-- Modal -->
                    <div class="modal fade" id="myModal" role="dialog">
                        <div class="modal-dialog">
                        
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><i class="fas fa-times-circle fa-lg"></i></button>
                            </div>
                            <div class="modal-body">
                                <form action="createPin" method="post" >
                                    <div class="row">
                                        <form action="createPin" method="post" enctype="multipart/form-data" id="js-upload-form">
                                            <div class="col">
                                                <!-- Standard Form -->
                                                <h5>Select images from your computer</h5>                                                
                                                    <div class="form-inline">
                                                        <div class="form-group">
                                                            <input type="file" name="files[]" id="js-upload-files" multiple>
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
                                </form>
                            </div>                            
                        </div>
                        
                        </div>
                    </div>
                </div>

            <c:forEach items = "${pinList}" var="pin" >
                <p>${pin.getRecipeName()}<br>
                   ${pin.getRecipe()}
                
                </p> 
                <hr>
            </c:forEach>                 
            </div><!-- Container ends here-->  

            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
    </body>
</html>