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
        <%--<li role="presentation" class="active" ><a href="${pageContext.request.contextPath}/reservationRoom/search?offset=0">Reservations</a></li>--%>
            <%--<li role="presentation"><a href="${pageContext.request.contextPath}/power/list?offset=0">Powers</a></li>--%>
            <%--<li role="presentation"><a href="${pageContext.request.contextPath}/sighting/list?offset=0">Sightings</a></li>--%>
            <%--<li role="presentation"><a href="${pageContext.request.contextPath}/super/list?offset=0">Super People</a></li>--%>
        </ul>
    </div>
    <sf:form action="/reservation/searchRooms" method="get" modelAttribute="commandModel"  >
<div class="row">
    <div class="col-md-12">
        <h2>Reservations</h2>
            <table id="reservations" class="table table-hover" >
                <tr>
                    Start Date:
                    <%--<input type="date" name="start">--%>
                    <sf:input type="date" path="startDate" ></sf:input>

                    End Date:
                    <%--<input type="date" name="end">--%>
                    <sf:input type="date" path="endDate"></sf:input>


                    Number in Party:
                    <sf:select type="select" path="numInParty" name="numPersons">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                    </sf:select>
                    <button type="submit">Search</button>
             </tr>
            </table>
        </sf:form>

    </div>
</div>
        <table id="rooms" class="table table-hover" >
            <tr>
                <th width="10%">Room Name</th>
                <th width="10%">Room Number</th>
                <th width="10%">Price Per Night</th>
                <th width="10%">Occupancy</th>
                <th width="10%">Amenities</th>
                <th width="60%"></th>
            </tr>
        <br/>
            <tr>
                <td colspan="5">${viewModel.message}</td>
            </tr>
            <c:forEach items="${viewModel.rooms}" var="room">
                <tr>
                    <td>${room.type}
                        <button><a href="${pageContext.request.contextPath}/reservation/searchResults?roomNumber=${room.roomNumber}
                            &startDate=${commandModel.startDate}&endDate=${commandModel.endDate}&numInParty=${commandModel.numInParty}">
                            Reserve Room</a></button>
                    </td>
                    <td>${room.roomNumber}</td>
                    <td>${room.rate}</td>
                    <td>${room.occupancy}</td>
                    <td>
                        <c:forEach items="${room.amenityList}" var="amenity">
                            ${amenity.type}
                            <br/>
                        </c:forEach>
                    </td>
                    <td>
                        <div style="text-align:right"><img src="/images/Bart.jpg" alt="Placeholder"></div>

                    </td>

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
</html>