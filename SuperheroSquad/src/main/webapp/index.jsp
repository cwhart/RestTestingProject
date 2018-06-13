<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>H.E.R.O.</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>H.E.R.O.</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/location/list?offset=0">Locations</a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/organization/list?offset=0">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/power/list?offset=0">Powers</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sighting/list?offset=0">Sightings</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/super/list?offset=0">Super People</a></li>
                </ul>    
            </div>

        </div>

        <div class="container">
            <div class="row">
                <div class="col-xs-4 col-xs-offset-1 col1">
                    <h1>About Us</h1>
                    <td >
                        Superhero Squad allows you to see the latest superhero
                        and supervillain sightings. We allow our users to update
                        superheros & villain, add sightings and locations of
                        spotted superheros and villains. We also allow our users
                        to see all superhero and supervillain organizations.
                    </td>
                </div>
            </div>
        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

