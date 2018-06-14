<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%--
  Created by IntelliJ IDEA.
  User: n0148464
  Date: 6/8/2018
  Time: 9:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Superhero Sightings</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Hero Education and Relationship Organization (H.E.R.O.)</h1>
    <hr/>
<div class="navbar">
    <ul class="nav nav-tabs">
        <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
        <li role="presentation"><a href="${pageContext.request.contextPath}/location/list?offset=0">Locations</a></li>
        <li role="presentation" ><a href="${pageContext.request.contextPath}/organization/list?offset=0">Organizations</a></li>
        <li role="presentation"><a href="${pageContext.request.contextPath}/power/list?offset=0">Powers</a></li>
        <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/sighting/list?offset=0">Sightings</a></li>
        <li role="presentation"><a href="${pageContext.request.contextPath}/super/list?offset=0">Super People</a></li>
    </ul>
</div>
<div class="row">
    <div class="col-md-6">
        <h2>Sightings</h2>
        <table id="sightings" class="table table-hover">


    <c:forEach items="${viewModel.locations}" var="sighting">
        <tr>
            <td><a href="/sighting/profile?id=${sighting.sightingId}">${sighting.location}
            </a></td>
            <td>${sighting.date}</td>
            <sec:authorize access="hasRole('ROLE_ADMIN')">

            <td><a href="${pageContext.request.contextPath}/sighting/edit?id=${sighting.sightingId}">Edit</a></td>
            <td><a href="${pageContext.request.contextPath}/sighting/delete?id=${sighting.sightingId}">Delete</a></td>
            </sec:authorize>
        </tr>
    </c:forEach>
</table>

<c:forEach items="${viewModel.pageNumbers}" var="pageNumber">
    <a href="/sighting/list?offset=${(pageNumber -1) *5}">${pageNumber}</a>
</c:forEach>

        <sec:authorize access="hasRole('ROLE_USER')">

        <br />
<a href="/sighting/create">Create New Sighting</a>
        </sec:authorize>
    </div>
</div>
</div>
</body>
</html>