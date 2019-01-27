<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: N0160024
  Date: 6/10/2018
  Time: 5:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reservation Details</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Your Reservation</h1>
    <hr/>
    <div class="navbar">

            <ul class="nav nav-tabs">
                <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/reservation/displayRooms">Rooms</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/reservation/searchReservation">Search</a></li>
            </ul>

    </div>
    <h2>Home Page</h2>
</div>
<p>
    Your reservation has been cancelled.
</p>



</body>
</html>