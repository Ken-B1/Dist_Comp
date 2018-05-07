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
    </head>

    <body style="background-color: #eeeeee">
        <div class="container">
            <div align="center" class="panel panel-default" style="margin-top: 4%;">
                <div class="panel-body" style="background-color: white; padding: 50px 20px 30px 20px; width: 40%; border-radius: 20px; border:solid; border-width: 1px; border-color: #bbbbbb">
                    <form action="settings" method="post">
                        <p class="font-weight-bold">Update your details</p>
                        <div class="row">
                            <div class="col-sm">
                                <div class="form-group" style="margin-top: 5%;">
                                    <label>Email:</label>  
                                </div>
                                <div class="form-group" style="margin-top: 5%;">
                                    <label>Username:</label>  
                                </div>
                                <div class="form-group" style="margin-top: 5%;">
                                    <label>First name:</label>  
                                </div>
                                <div class="form-group" style="margin-top: 5%;">
                                    <label>Last name:</label>  
                                </div>
                                <div class="form-group" style="margin-top: 5%;">
                                    <label>Country:</label>  
                                </div>
                                <div class="form-group" style="margin-top: 5%;">
                                    <label>Gender:</label>  
                                </div>
                            </div> 

                            <div class="col-sm">
                                <div class="form-group">
                                    <input type="text" class="form-control" name="email" value="${accountinfo.email}"/>
                                </div>
                                <div class="form-group">                    
                                    <input type="text" class="form-control" name="username" value="${accountinfo.username}"/>
                                </div>
                                <div class="form-group">                    
                                    <input type="text" class="form-control" name="fname" value="${accountinfo.fname}"/>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" name="lname" value="${accountinfo.lname}"/>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" name="country" value="${accountinfo.country}"/>
                                </div> 
                                <div class="form-group">                                    
                                    <input type="text" class="form-control" name="gender" value="${accountinfo.gender}"/>
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