<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>        
        <title>Home - Pin4FooD</title>
        <%@include file="headers/header.jsp" %>        
    </head>

    <body>   
        <p>@Keerthana: to get a different amount of topics, change the parameters in 'cat.getRisingCategories(10);' in the exploreservlet</p>
        <b>Top topics:</b>
        <div class="tab-content">
            <div class="rows">
                <c:forEach items = "${top}" var="toptopic" >
                   <div class="col-sm-2">
                        <div class="card bg-light mb-3">     
                            <div class="card-body" style="max-width: 18rem;height:11.5rem;">                            
                            </div>
                            <div class="card-header">
                                <span class="d-inline-block text-truncate" style="max-width: 200px;">
                                  ${toptopic.getName()}
                                </span>
                            </div>
                        </div>
                    </div> 
                </c:forEach>
            </div>       
        </div>
        
        <b>Trending topics:</b>
        <div class="tab-content">
            <div class="rows">
                <c:forEach items = "${trending}" var="trendingtopic" >
                    <div class="col-sm-2">
                        <div class="card bg-light mb-3">     
                            <div class="card-body" style="max-width: 18rem;height:11.5rem;">                            
                            </div>
                            <div class="card-header">
                                <span class="d-inline-block text-truncate" style="max-width: 200px;">
                                  ${trendingtopic.getName()}
                                </span>
                            </div>
                        </div>
                    </div> 
                </c:forEach>
            </div>
        </div>
    </body>
</html>