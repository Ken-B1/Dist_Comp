<%-- 
    Document   : settings
    Created on : 18-mrt-2018, 11:29:39
    Author     : ken
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <%@include file="headers/header.jsp" %>
        <link rel="stylesheet" href="countrySelect/css/countrySelect.css">
        <script src="js/boards.js"></script>        
    </head>

    <body style="background-color: #eeeeee">
        <div class="container">
            <div align="center" class="panel panel-default" style="margin-top: 4%;">
                <div class="panel-body" style="background-color: white; padding: 50px 20px 30px 20px; width: 40%; border-radius: 20px; border:solid; border-width: 1px; border-color: #bbbbbb">
                    <form action="settings" method="post">
                        <p class="font-weight-bold">Update your details</p>
 <div class="row">
                            <div class="col-sm">
                                <p>Credentials</p>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="email" placeholder="Email" name="email" value="${accountinfo.email}" required>
                                    <p style="color:red;" id="emailError"></p>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="username" placeholder="Username" name="username" value="${accountinfo.username}" required>
                                    <p style="color:red;" id="unameError"></p>
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control" id="password" placeholder="Password" name="password" required>
                                     <p style="color:red;" id="pwdError"></p>
                                </div>
                            </div>

                            <div class="col-sm">
                                <p>Personal Details</p>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="fname" placeholder="First Name" name="fname" value="${accountinfo.fname}" required>
                                    <p style="color:red;" id="fnameError"></p>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="lname" placeholder="Last Name" name="lname" value="${accountinfo.lname}" required>
                                    <p style="color:red;" id="lnameError"></p>
                                </div>
                                <div class="form-group">                                    
                                    <input class="form-control" type="text" id="country" value="${accountinfo.country}" name="country" >

                                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
                                    <script src="countrySelect/js/countrySelect.min.js"></script>
                                    <script>
                                        $("#country").countrySelect();
                                    </script> 
                                </div>
                                <div class="form-group">
                                    <select class="custom-select" name="gender" value="${accountinfo.gender}">
                                        <option selected>Select Gender</option>
                                        <option value="M">Male</option>
                                        <option value="F">Female</option>
                                        <option value="U">Other</option>
                                    </select>  
                                   <p style="color:red;" id="gError"></p>
                                </div>
                            </div>
                        </div>
             <input type="submit" class="btn btn-success" value="Update">                
                </form>
              </div>
            </div>
        </div>
    </div>
</body>


</html>