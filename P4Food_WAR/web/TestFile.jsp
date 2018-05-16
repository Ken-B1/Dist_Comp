<%-- 
    Document   : TestFile
    Author     : ken
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
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        
        <link rel="icon" href="images\icons\logo.png">      
    </head>
    <body style="background-color: #eeeeee">
        <form action="FriendRequest" method="post"id="js-upload-form">
            <input type="text" name="id" value="2" style="display:none"></input>
            <input type="text" name="type" value="request" style="display:none"></input>
            <div class="col">
                <button type="submit" class="btn btn-sm btn-primary" id="js-upload-submit">Upload Pins</button>                                                                                                                                                               
            </div>                                           
        </form>      
        
        
    </body>
</html>
