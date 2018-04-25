<%-- 
    Document   : settings
    Created on : 18-mrt-2018, 11:29:39
    Author     : ken
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                        <a class="nav-link" href="createPin"><i class="fas fa-thumbtack fa-sm"></i> Pins</a>
                    </li>
                </ul>
                <c:if test="${isAdmin}"><a class="nav-item active" href="admin">Admin</a></c:if>
                <a class="nav-item active" href="settings"><i class="fas fa-user-circle fa-2x"></i></a>
                <a class="nav-item active" href="statistics">Statistics</a>
                <button class="btn btn-outline-success my-2 my-sm-0" >Login</button>
            </div> 
        </nav>
        <div style="margin-top: 5%; margin-left: 30%">
            <form action="settings" method="post">
                <div class="form-group">
                    <label>Email:</label>
                    <input type="text" name="email" value="${accountinfo.email}"/>
                </div>   
                <div class="form-group">
                    <label>Username: </label>
                    <input type="text" name="username" value="${accountinfo.username}"/>
                </div>   
                <div class="form-group">
                    <label>First name: </label>
                    <input type="text" name="fname" value="${accountinfo.fname}"/>
                </div>   
                <div class="form-group">
                    <label>Last name:</label>
                    <input type="text" name="lname" value="${accountinfo.lname}"/>
                </div>   
                <div class="form-group">
                    <label>Country:</label>
                    <input type="text" name="country" value="${accountinfo.country}"/>
                </div>   
                <div class="form-group">
                    <label>Gender:</label>
                    <input type="text" name="gender" value="${accountinfo.gender}"/>
                </div>   
                <input type="submit" value="Update">
            </form>
        </div>
    </body>
</html>