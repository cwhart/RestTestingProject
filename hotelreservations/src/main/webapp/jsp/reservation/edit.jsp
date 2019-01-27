<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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
<sf:form action="/reservation/edit" method="post" modelAttribute="viewModel">
    <sf:hidden path="commandModel.reservationId"></sf:hidden>
    ${viewModel.message}
<%--<p>--%>
    <%--Reservation ID: <c:out value="${commandModel.reservationId}"/>--%>
<%--</p>--%>
<p>
    Bill ID: <c:out value="${commandModel.billId}"/>
</p>
    <div class="form-group">
        <label class="col-md-2" for="commandModel.reservationHolder">Reservation Holder:</label>
        <div class="col-md-10">
            <sf:select path="commandModel.reservationHolder" multiple="false">
                <sf:options items="${viewModel.commandModel.guests}"  />
            </sf:select>
            <sf:errors path="commandModel.reservationHolder"></sf:errors>
        </div>
    </div>
<p>
    Start Date: <c:out value="${viewModel.commandModel.startDate}"/>
</p>
<p>
    End Date: <c:out value="${viewModel.commandModel.endDate}"/>
</p>
<p>
    Room Number: <c:out value="${viewModel.commandModel.roomNumber}"/>
</p>
<p>
    Email: <c:out value="${viewModel.commandModel.email}"/>
</p>
<p>
    Phone: <c:out value="${viewModel.commandModel.phone}"/>
</p>
<p>
    <%--Promotion Code: <c:out value="${commandModel.promoCode}"/>--%>
    <div class="form-group">
        <label class="col-md-2" for="${viewModel.commandModel.promoCode}">Promo Code:</label>
        <div class="col-md-10">
            <sf:input path="commandModel.promoCode"></sf:input>
            <sf:errors path="commandModel.promoCode"></sf:errors>
        </div>
    </div>
</p>
<div class="col-md-2">Guests:</div>
<div class="col-md-10">
    <table>
        <c:forEach items="${viewModel.commandModel.guests}" var="guest">
            <tr>
                <td>
                    ${guest}
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<button><a href="${pageContext.request.contextPath}/addon/listAddOns?billId=${viewModel.commandModel.billId}">Select Add-On</a></button>
    <button type="submit">Save</button>
    <button><a href="${pageContext.request.contextPath}/reservation/profile?id=${viewModel.commandModel.reservationId}">Cancel</a></button>

</sf:form>


</body>
</html>