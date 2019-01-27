<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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
    <title>Reservations</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Hotel Reservations</h1>
    <hr/>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/reservation/displayRooms">Rooms</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/reservation/searchReservation">Search</a></li>
        </ul>
    </div>
    <sf:form action="/reservation/searchReservation" method="get" modelAttribute="commandModel"  >
<div class="row">
    <div class="col-md-12">
        <h2>Search Reservations</h2>
            <table id="reservations" class="table table-hover" >
                <tr>
                    Reservation ID:
                    <%--<input type="date" name="start">--%>
                    <sf:input type="text" path="reservationNumber" ></sf:input>

                    <button type="submit">Search</button>
             </tr>
            </table>
        <p>
            <c:out value="${commandModel.message}"/>
        </p>
        </sf:form>

    </div>

    <c:if test="${not empty viewModel}">

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
    <button><a href="${pageContext.request.contextPath}/reservation/cancelReservation?reservationNumber=${viewModel.reservationId}">
        Cancel Reservation</a></button>
    <button><a href="${pageContext.request.contextPath}/reservation/edit?id=${viewModel.reservationId}">
        Edit Reservation</a></button>
    </c:if>

    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/delete.js"></script>

</body>
</html>