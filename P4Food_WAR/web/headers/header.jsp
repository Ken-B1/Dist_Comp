<%-- 
    Document   : header
    Author     : keerthana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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


        <% String userprf = (String) session.getAttribute("userid");
            if (userprf == null) {
                System.out.println("This is not a user");
                userprf = "";
            } else {
                System.out.println("The user is " + userprf);
            };
        %>            

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="index.jsp">
                <img src="images\icons\logo.png" width="30" height="30" class="d-inline-block align-top" alt="">
                Pin4FooD
            </a>   
            <div class="collapse navbar-collapse" id="navbarSupportedContent"> 
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="pinboard"><i class="fas fa-home fa-sm"></i> Home</a>
                    </li>                                      
                    <li>
                        <form class="form-inline my-2 my-lg-0">
                            <input class="form-control mr-sm-2" type="search" placeholder="Search">
                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                        </form>                      
                    </li>
                </ul> 

                <% if (userprf == "") {%>
                <ul class="nav navbar-nav navbar-right" id="loginmenu">
                    <a class="btn btn-outline-success my-2 my-sm-0" href="index.jsp">Login</a>
                </ul>
                <%} else {%>
                <ul class="nav navbar-nav navbar-right" id="usernmenu">
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="content:;"> 
                            <!-- <i class="fas fa-user-circle fa-1x"></i> -->
                            <img class="user" src="images\icons\logo.png" />
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">                     
                            <a class="dropdown-item" href="createBoard">My Profile</a>
                            <a class="dropdown-item" href="MessageOverview">Messages</a>
                            <a class="dropdown-item" href="settings">Settings</a>
                            <a class="dropdown-item" href="statistics">Statistics</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="logout">Logout</a>

                            <c:if test="${isAdmin}">
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="admin">I am Admin</a>
                            </c:if>
                        </div>
                    </li>
                </ul>  
                <% }
                %>




            </div> 
        </div>
    </nav>
</head>
<body>
    <div class="dropdown-divider"></div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
</body>
</html>
