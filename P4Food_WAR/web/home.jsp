<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/fa-svg-with-js.css">
        <script src="js/bootstrap.bundle.min.js"></script>
        <script defer src="js/fontawesome-all.js"></script>
        
        <title>Home - Pin4FooD</title>
        <link rel="icon" href="images\icons\logo.png">

        <style>
            .rgrid {
                display: flex;
                flex-wrap: wrap;
                padding: 0 4px;
                }
            .cgrid {
            flex: 25%;
            max-width: 25%;
            padding: 0 4px;
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
                <a class="nav-item active" href="settings"><i class="fas fa-user-circle fa-2x"></i></a>
                <a class="nav-item active" href="statistics">Statistics</a>
                                <!-- TO DO Notification pop up icon-->
                <button class="btn btn-links" style="font-size:36px;" data-toggle="dropdown"  aria-haspopup="true" aria-expanded="false" >
                        <i class="far fa-bell"></i>
                </button>
                <span class="badge badge-notify">0</span>
                
                <!-- TO DO Notification toggle-->
                <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
                    <button class="dropdown-item" type="button">Action</button>
                    <button class="dropdown-item" type="button">Another action</button>
                    <button class="dropdown-item" type="button">Something else here</button>
                </div>
                <a href="logout"><button class="btn btn-outline-success my-2 my-sm-0" >Login</button></a>
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
                                    <p class="lead">Welcome to Pin4Food, Share your pins on food.</p>
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
            </div>

            <div class="row rgrid" id="wrapper">

                    <script>
                            var HttpClient = function() {
                                this.get = function(aUrl, aCallback) {
                                    var anHttpRequest = new XMLHttpRequest();
                                    anHttpRequest.onreadystatechange = function() { 
                                        if (anHttpRequest.readyState == 4 && anHttpRequest.status == 200)
                                            aCallback(anHttpRequest.responseText);
                                    }
            
                                    anHttpRequest.open( "GET", aUrl, true );            
                                    anHttpRequest.send( null );
                                }
                            }
                            var url = "http://localhost:8080/PinterestUI/images/users/"+"username/"+"African/"+"BoardName"
                            var client = new HttpClient();
                            client.get(url, function(response) {
                                //console.log(response);
                                var el = document.createElement( 'html' );
                                el.innerHTML = response
                                console.log(el.getElementsByTagName('a'));
                                var imageItems = el.getElementsByTagName('a');
                                var arrayLength = imageItems.length;
                                for(var i=1;i<arrayLength;i++){
                                    var imgAdd = url+"/"+(imageItems[i].innerHTML).trim();
                                   console.log(url+"/"+(imageItems[i].innerHTML).trim());
                                 //  $("#wrapper").append("<img class='card-img-top' src="+imgAdd+" alt='Card image cap'>");
                                   $("#wrapper").append("<div class='col-md-4 cgrid'>"
                                                    +"<div class='card mb-4 box-shadow'>"
                                                    +"  <img class='card-img-top' src="+imgAdd+" alt='Card image cap'>"
                                                    +"    <div class='card-body'>"
                                                    +"    <p class='card-text'>Description of the images goes here. </p>"
                                                    +"      <div class='d-flex justify-content-between align-items-center'>"
                                                    +"      <div class='btn-group'>" 
                                                            +"<button type='button' class='btn btn-sm btn-outline-secondary'>View</button>" 
                                                            +"<button type='button' class='btn btn-sm btn-outline-secondary'>Edit</button>" 
                                                            +"</div>" 
                                                            +"<small class='text-muted'>9 mins</small>" 
                                                            +"</div>"
                                                            +"</div>" 
                                                            +"</div>"
                                                            +"</div>"
                                    );
                                                                                                        
                                }
                            });
                        </script>
                
            </div>
           <!-- Add a 'if' condition such that it should display the below script tag only new users  -->

            <script type="text/javascript">
                window.onload = function(){
                    $('#myModal').modal('show');
                };
            </script>
            
            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog">
                
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><i class="fas fa-times-circle fa-lg"></i></button>
                    </div>
                    <div class="modal-body">
                        <form action="# createboard.jsp">
                            <div class="row">
                                <div class="col">
                                <h5>Select atleast three categories</h5>  
                                    <c:forEach items = "${Categories}" var="category" >  
                                        <div class="custom-control custom-checkbox">
                                            <input type="checkbox" class="custom-control-input" id="${category.getName()}" value="${category.getId()}">
                                            <label class="custom-control-label" for="${category.getName()}">${category.getName()}</label>
                                        </div>
                                    </c:forEach>
                                    <div class="dropdown-divider"></div>                                    
                                </div>                                       
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                    <button type="submit" class="btn btn-default">Add</button>
                    </div>
                </div>
                
                </div>

                <!-- Display all the boards that are created under different category-->

            </div>
            
            
        </div>

    </div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
    </body>
</html>