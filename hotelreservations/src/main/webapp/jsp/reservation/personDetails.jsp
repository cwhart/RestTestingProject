<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: N0160024
  Date: 6/10/2018
  Time: 5:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Enter Reservation Holder Details</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container">
    <h1>The Dragonfly Inn</h1>
    <hr/>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/reservation/displayRooms">Rooms</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/reservation/searchReservation">Search</a></li>
        </ul>
    </div>
    <h2>Reservation Holder</h2>
</div>

<sf:form action="/reservation/personDetails" method="post" modelAttribute="commandModel">
    ${viewModel.message}

    <div class="form-group">
        <label class="col-md-2" for="${commandModel.roomsViewModel.commandModel.startDate}">Start Date:</label>
        <div class="col-md-10">
            <sf:input type="date" path="roomsViewModel.commandModel.startDate"></sf:input>
            <sf:errors path="roomsViewModel.commandModel.startDate"></sf:errors>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2" for="${commandModel.roomsViewModel.commandModel.endDate}">End Date:</label>
        <div class="col-md-10">
            <sf:input type="date" path="roomsViewModel.commandModel.endDate"></sf:input>
            <sf:errors path="roomsViewModel.commandModel.endDate"></sf:errors>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2" for="${commandModel.roomsViewModel.commandModel.roomNum}">Room Number:</label>
        <div class="col-md-10">
            <sf:input path="roomsViewModel.commandModel.roomNum"></sf:input>
            <sf:errors path="roomsViewModel.commandModel.roomNum"></sf:errors>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2" for="${commandModel.promoCode}">Promotion Code:</label>
        <div class="col-md-10">
            <sf:input type="text" path="promoCode"></sf:input>
            <sf:errors path="promoCode"></sf:errors>
        </div>
    </div>

<c:forEach var="thisPerson" items="${commandModel.personDetailsViewModels}" varStatus="status" >
    <form>
    <div class="form-group">
        <label class="col-md-2" for="${thisPerson.commandModel.firstName}">First Name:</label>
        <div class="col-md-10">
            <input name="personDetailsViewModels[${status.index}].commandModel.firstName" value="${thisPerson.commandModel.firstName}" required/>
        </div>
    </div>

    <br/>

<div class="form-group">
    <label class="col-md-2" for="${thisPerson.commandModel.lastName}">Last Name:</label>
    <div class="col-md-10">
        <input name="personDetailsViewModels[${status.index}].commandModel.lastName" value="${thisPerson.commandModel.lastName}" required/>
    </div>
</div>

    <br/>

    <div class="form-group">
        <label class="col-md-2" for="${thisPerson.commandModel.dateOfBirth}">Date of Birth:</label>
        <div class="col-md-10">
            <input type="date" name="personDetailsViewModels[${status.index}].commandModel.dateOfBirth" value="${thisPerson.commandModel.dateOfBirth}"/>
        </div>
    </div>

    <br/>

    <div class="form-group">
        <label class="col-md-2" for="${thisPerson.commandModel.phone}">Phone:</label>
        <div class="col-md-10">
            <input name="personDetailsViewModels[${status.index}].commandModel.phone" value="${thisPerson.commandModel.phone}"/>
        </div>
    </div>

    <br/>

    <div class="form-group">
        <label class="col-md-2" for="${thisPerson.commandModel.email}">Email:</label>
        <div class="col-md-10">
            <input name="personDetailsViewModels[${status.index}].commandModel.email" value="${thisPerson.commandModel.email}"/>
        </div>
    </div>


    </c:forEach>

    <button type="submit">Create</button>
    <button type="reset">Cancel</button>
    </form>

</sf:form>

</body>
</html>