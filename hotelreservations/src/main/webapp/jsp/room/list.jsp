<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>

<%--
  Created by IntelliJ IDEA.
  User: n0239947
  Date: 6/9/2018
  Time: 9:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Room List</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Hotel Reservations</h1>
    <hr/>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/room/list?offset=0">Rooms</a></li>
            <li role="presentation" ><a href="${pageContext.request.contextPath}/reservationRoom/search?offset=0">Reservations</a></li>
            <%--<li role="presentation"><a href="${pageContext.request.contextPath}/power/list?offset=0">Powers</a></li>--%>
            <%--<li role="presentation"><a href="${pageContext.request.contextPath}/sighting/list?offset=0">Sightings</a></li>--%>
            <%--<li role="presentation"><a href="${pageContext.request.contextPath}/super/list?offset=0">Super People</a></li>--%>
        </ul>
    </div>

<div class="row">
    <div class="col-md-12">
        <h2>Rooms</h2>
            <table id="rooms" class="table table-hover">
                <tr>
                    <th width="10%">Room Name</th>
                    <th width="10%">Room Number</th>
                    <th width="10%">Occupancy</th>
                    <th width="10%">Amenities</th>
                    <th width="60%"></th>
                </tr>
                <c:forEach items="${listViewModel.rooms}" var="room">
                    <tr>
                        <td>${room.type}</td>
                        <td>${room.roomnumber}</td>
                        <td>${room.occupancy}</td>
                        <td>
                                <c:forEach items="${room.amenities}" var="amenity">
                                            ${amenity.type}
                                    <br/>
                                </c:forEach>
                        </td>
                        <td>
                            <div style="text-align:right"><img src="/images/Bart.jpg"  alt="Placeholder"></div>

                        </td>
                        <%--<sec:authorize access="hasRole('ROLE_ADMIN')">--%>
                        <%--<td><a href="${pageContext.request.contextPath}/room/edit?id=${room.id}">Edit</a></td>--%>
                        <%--<td><a href="${pageContext.request.contextPath}/room/delete?id=${room.id}" class="delete">Delete</a></td>--%>
                        <%--</sec:authorize>--%>
                    </tr>
                </c:forEach>
            </table>
<%--<c:forEach items="${viewModel.pageNumbers}" var="pageNumber">--%>
    <%--<a href="/room/list?offset=${(pageNumber -1)* 5}">${pageNumber}</a>--%>
<%--</c:forEach>--%>
<%--<br />--%>
        <%--<sec:authorize access="hasRole('ROLE_USER')">--%>

<%--<a href="/room/search">Create</a>--%>
        <%--</sec:authorize>--%>
<!-- <a href="/room/cancel">Cancel</a> -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/delete.js"></script>

</body>
</div>
</html>