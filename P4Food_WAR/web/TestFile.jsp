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
        <form action="testServlet" method="post" enctype="multipart/form-data" id="js-upload-form">
            <input type="text" name="id" value="${boardId}" style="display:none"></input>
            <div class="col">
                <!-- Standard Form -->
                <h5>Select images from your computer</h5>                                                
                <div class="form-inline">
                    <div class="form-group">
                        <input type="file" name="file" id="js-upload-files" multiple>
                    </div>
                </div> 
                <button type="submit" class="btn btn-sm btn-primary" id="js-upload-submit">Upload Pins</button>                                                                                                                                                               
            </div>                                           
        </form>        
    </body>
</html>
