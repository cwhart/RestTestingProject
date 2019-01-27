<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>

<%--
  Created by IntelliJ IDEA.
  User: n0268611
  Date: 6/10/2018
  Time: 12:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Promotions</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body >
<div class="container">

    <div><h1>Promotions</h1></div>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/reservation/displayRooms">Rooms</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/reservation/searchReservation">Search</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/promo/list">Promotions</a></li>

        </ul>
    </div>

    <div class="row">
        <div class="col-md-12">
            <h2>Promotions</h2>
            <table id="charges" class="table table-hover">
                <tr>
                    <th width="50%">Description</th>
                    <th width="10%">Code</th>
                    <th width="10%">Rate</th>
                    <th width="10%">Start Date</th>
                    <th width="10%">End Date</th>
                    <th width="10%"></th>

                </tr>
                <c:forEach var="promo" items="${viewModel}" >
                    <tr>
                        <td>${promo.description}</td>
                        <td>${promo.promoCode}</td>
                        <td>${promo.rate}</td>
                        <td>${promo.startDate}</td>
                        <td>${promo.endDate}</td>
                        <td><a href="${pageContext.request.contextPath}/promo/edit?id=${promo.id}">Edit</a></td>


                    </tr>
                </c:forEach>

            </table>
            <button><a href="${pageContext.request.contextPath}/promo/create">New Promotion</a></button>
        </div>


    </div>
</div>
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/delete.js"></script>

</body>
</html>