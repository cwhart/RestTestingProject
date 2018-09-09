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
            <li role="presentation" ><a href="${pageContext.request.contextPath}/room/list?offset=0">Rooms</a></li>
            <li role="presentation" class="active" ><a href="${pageContext.request.contextPath}/reservationRoom/search?offset=0">Reservations</a></li>
            <%--<li role="presentation"><a href="${pageContext.request.contextPath}/power/list?offset=0">Powers</a></li>--%>
            <%--<li role="presentation"><a href="${pageContext.request.contextPath}/sighting/list?offset=0">Sightings</a></li>--%>
            <%--<li role="presentation"><a href="${pageContext.request.contextPath}/super/list?offset=0">Super People</a></li>--%>
        </ul>
    </div>
    <sf:form action="/reservationRoom/search" method="post" modelAttribute="commandModel" >
<div class="row">
    <div class="col-md-12">
        <h2>Reservations</h2>
            <table id="reservations" class="table table-hover">
                <tr>
                    Start Date:
                    <%--<input type="date" name="start">--%>
                    <sf:input type="date" path="startDate"></sf:input>

                    End Date:
                    <%--<input type="date" name="end">--%>
                    <sf:input type="date" path="endDate"></sf:input>


                    Number in Party:
                    <sf:select type="select" path="numPersons" name="numPersons">
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
        <%--<table>--%>
                <%--<tr>--%>
                    <%--<th width="50%">Start Date</th>--%>
                    <%--<th width="50%">End Date</th>--%>

                <%--</tr>--%>
                <%--<c:forEach items="${viewModel.reservations}" var="reservation">--%>
                    <%--<tr>--%>
                        <%--<td>${reservation.startDate}</td>--%>
                        <%--<td>${reservation.endDate}</td>--%>

                    <%--</tr>--%>
                <%--</c:forEach>--%>
        <%--</table>--%>
        </sf:form>
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