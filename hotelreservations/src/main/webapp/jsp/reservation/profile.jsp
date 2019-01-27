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
    Reservation ID: <c:out value="${viewModel.reservationId}"/>
</p>
<p>
    Bill ID: <c:out value="${viewModel.billId}"/>
</p>
<p>
    First Name: <c:out value="${viewModel.firstName}"/>
</p>
<p>
    Last Name: <c:out value="${viewModel.lastName}"/>
</p>
<p>
    Start Date: <c:out value="${viewModel.startDate}"/>
</p>
<p>
    End Date: <c:out value="${viewModel.endDate}"/>
</p>
<p>
    Room Number: <c:out value="${viewModel.roomNumber}"/>
</p>
<p>
    Email: <c:out value="${viewModel.email}"/>
</p>
<p>
    Phone: <c:out value="${viewModel.phone}"/>
</p>
<p>
    Promotion Code: <c:out value="${viewModel.promoCode}"/>
</p>
<div class="col-md-2">Additional Guests:</div>
<div class="col-md-10">
    <table>
        <c:forEach items="${viewModel.guests}" var="guest">
            <tr>
                <td>
                    ${guest}
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<button><a href="${pageContext.request.contextPath}/addon/listAddOns?billId=${viewModel.billId}">Select Add-On</a></button>



</body>
</html>