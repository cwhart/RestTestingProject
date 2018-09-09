<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
    <div style="text-align: center; " ><img src="/images/Blackpool.jpg" style="max-width: 100%; height: auto" alt="Blackpool Beach"></div>
        <div class="container">
            <h1>The Dragonfly Inn</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/room/list?offset=0">Rooms</a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/reservationRoom/search">Reservations</a></li>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <%--<li role="presentation">--%>
                        <%--<a href="/displayUserList">User Admin</a></li>--%>
                    </sec:authorize>
                    </li>
                </ul>
            </div>
            <h2>Welcome to the Dragonfly!</h2>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

