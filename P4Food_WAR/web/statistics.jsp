<%-- 
    Document   : statistics
    Created on : 18-mrt-2018, 11:29:46
    Author     : ken
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <%@include file="headers/header.jsp" %>
    </head>

    <body>
    
        <div class="container">
            <div class="dropdown-divider"></div>
            <div class="row">
                <div class="col"></div>
                <div class="col-6">
                    <table class="table">
                        <thead>
                          <tr>
                            <th scope="col">ID</th>
                            <th scope="col">USER ID</th>
                            <th scope="col">Time</th>
                          </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${logins}" var="entry">
                                <tr>
                                    <th scope="row">${entry.id}</th>
                                    <td>${entry.userid}</td>
                                    <td>${entry.timestamp}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
             <div class="col"></div>
            </div>
        </div>
        
    </body>
</html>

