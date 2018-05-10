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
        <script type="text/javascript">
            // A $( document ).ready() block.
            $( document ).ready(function() {
                $.get("Notifications", function(data){
                    alert(data);
                });
            });
        </script>        
    </head>
    <body style="background-color: #eeeeee">
        <form action="FollowBoard" method="post">
            
            <input type="submit" name="BoardId" value="1">Click</input>
        </form>
        
        <form action="UnfollowBoard" method="post">
            
            <input type="submit" name="BoardId" value="1">Click</input>
        </form>
        
        <form action="FollowPerson" method="post">
            
            <input type="submit" name="PersonId" value="2">Click</input>
        </form>
        
        <form action="UnfollowPerson" method="post">
            
            <input type="submit" name="PersonId" value="2">Click</input>
        </form>
        
        <form action="BlockPerson" method="post">
            
            <input type="submit" name="PersonId" value="2">Click</input>
        </form>
        
        <form action="UnblockPerson" method="post">
            
            <input type="submit" name="PersonId" value="2">Click</input>
        </form>
    </body>
</html>
