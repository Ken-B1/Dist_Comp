<%-- 
    Document   : header
    Author     : keerthana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/fa-svg-with-js.css">
        <link rel="stylesheet" href="css/header.css">
        <script src="js/bootstrap.bundle.min.js"></script>
        <script defer src="js/fontawesome-all.js"></script>

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="icon" href="images\icons\logo.png">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="http://code.jquery.com/jquery.js"></script>
        
        <% String userprf = (String) session.getAttribute("userid");
            if (userprf == null) {
                System.out.println("This is not a user");
                userprf = "";
            } else {
                System.out.println("The user is " + userprf);
            };
        %>            

        <% if (userprf != "") {%> 
        <c:if test="${Loggedin}">
        <script type="text/javascript">
            // A $( document ).ready() block.
            $( document ).ready(function() {
                $.get("Messages", function(data){
                    $( ".messages" ).append(data);
                });
            });
        </script>
        
        </c:if>
        <%}%>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="login">
                <img src="images\icons\logo.png" width="30" height="25" class="d-inline-block align-top" alt="">
                Eat What You Wish
            </a>   
            <div class="collapse navbar-collapse" id="navbarSupportedContent"> 
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="pinboard"><i class="fas fa-home fa-sm"></i> Home</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="explore"><i class="fab fa-wpexplorer fa-sm"></i> Explore</a>
                    </li>     
                    <li class="nav-item active">
                        <a class="nav-link" href="recommendations"><i class="fas fa-box-open fa-sm"></i></i> Recommendations</a>
                    </li>    
                    <li>
                        <form class="form-inline my-2 my-lg-0" action="Search" method="post">
                            <input class="form-control mr-sm-2" type="search" placeholder="Search" name="searchString">
                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                        </form>                      
                    </li>
                </ul> 

                <% if (userprf == "") {%>
                <c:if test="${!Loggedin}">
                <ul class="nav navbar-nav navbar-right" id="loginmenu">
                    <a class="btn btn-outline-success my-2 my-sm-0" href="login">Login</a>
                </ul>
                </c:if>
                <%} else {%>
                <c:if test="${Loggedin}">
                <ul class="nav navbar-nav navbar-right" id="usernmenu">
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="content:;"> 
                            <i class="far fa-comment-alt fa-2x"></i>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown" style="min-width:25rem;border:0px;">                     

                            <div class="list-group messages" style="height: 300px;overflow:auto">
                                <a href="#" class="list-group-item list-group-item-action flex-column align-items-start active">
                                    <div class="d-flex w-100 justify-content-between">
                                        <h5 class="mb-1">Messages</h5>                                       
                                    </div>                                    
                                </a>
                            </div>
                        </div>
                    </li>

                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="content:;"> 
                            <span class="fa-stack" id="notifIcon" style="margin-top:10%">
                                <i class="far fa-bell fa-2x"></i>
                                <i class="fa fa-flag fa-stack-1x" id="notIcon" style="color:red; top: -25px;left: -25px; display:none"></i>
                            </span>                            
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown" style="min-width:25rem;border:0px;">                     

                            <div class="list-group notifications" style="height: 300px;overflow:auto">
                                <a href="#" class="list-group-item list-group-item-action flex-column align-items-start active">
                                    <div class="d-flex w-100 justify-content-between">
                                        <h5 class="mb-1">Notifications</h5>                                       
                                    </div>                                    
                                </a>
                            </div>
                        </div>
                    </li>


                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="content:;"> 
                            <i class="fas fa-user-circle fa-2x"></i>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">                     
                            <a class="dropdown-item" href="profile">My Profile</a>
                            <a class="dropdown-item" href="FriendRequest">Friends</a>
                            <a class="dropdown-item" href="MessageOverview">Messages</a>
                            <a class="dropdown-item" href="settings">Settings</a>
                            <a class="dropdown-item" href="statistics">Statistics</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="logout">Logout</a>

                            <c:if test="${isAdmin}">
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="admin">I am Admin</a>
                                <a class="dropdown-item" href="AdminStatistics">AdminStatistics</a>
                            </c:if>
                        </div>
                    </li>
                </ul>  
                </c:if>
                <% }
                %>




            </div> 
        </div>
    </nav>
</head>
<body>
    <div class="dropdown-divider"></div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
        <script type="text/javascript">
            // A $( document ).ready() block.
            $( document ).ready(function() {
                $.get("Notifications", function(data){
                    $( ".notifications" ).append(data);
                    $("#notIcon").toggle();

                });
            });
        </script>
</body>
</html>
