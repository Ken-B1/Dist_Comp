<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/fa-svg-with-js.css">
        <script src="js/bootstrap.bundle.min.js"></script>
        <script defer src="js/fontawesome-all.js"></script>

        <link rel="icon" href="images\icons\logo.png">
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
                        <a class="nav-link" href="pins.jsp"><i class="fas fa-thumbtack fa-sm"></i> Pins</a>
                    </li>
                </ul>
                <c:if test="${isAdmin}"><a class="nav-item active" href="admin">Admin</a></c:if>
                <a class="nav-item active" href="settings"><i class="fas fa-user-circle fa-2x"></i></a>
                <a class="nav-item active" href="statistics">Statistics</a>
                <button class="btn btn-outline-success my-2 my-sm-0" >Login</button>
            </div> 
        </nav>
        <div class="container">
           <!-- Image and text -->
            

            <div class="jumbotron">
                <div class="container">
                    <h1 class="display-4">Pin 4 Food</h1>
                    <p class="lead">Share your pins on food.</p>
                </div>
            </div>

            <div class="row" id="wrapper">

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
                                   $("#wrapper").append("<div class='col-md-4'>"
                                                    +"<div class='card mb-4 box-shadow'>"
                                                    +"  <img class='card-img-top' src="+imgAdd+" alt='Card image cap'>"
                                                    +"    <div class='card-body'>"
                                                    +"    <p class='card-text'>This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>"
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

            
            
        </div>

    </div>
    <div>
        <p>Board CRUD:</p>
            <form action="pinboard" method="post">
                <div class="form-group">
                    <label>Board name</label>
                    <input type="text" name="boardname"/>
                </div>   
                <div class="form-group">
                    <label>Category: </label>
                    <select name="category"/>
                        <c:forEach items = "${categoryList}" var="category" >
                            <option value="${emp}">${emp}</option>    
                        </c:forEach>
                    </select>
                </div>   
                <input type="submit" value="Create">
            </form>      
        <hr/>
        <p>Pin CRUD:</p>
    </div>


        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
    </body>
</html>