<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="navbar">
    <ul class="nav nav-tabs">
        <li role="presentation"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
        <li role="presentation"><a href="${pageContext.request.contextPath}/location/list?offset=0">Locations</a></li>
        <li role="presentation" ><a href="${pageContext.request.contextPath}/organization/list?offset=0">Organizations</a></li>
        <li role="presentation"><a href="${pageContext.request.contextPath}/power/list?offset=0">Powers</a></li>
        <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/sighting/list?offset=0">Sightings</a></li>
        <li role="presentation"><a href="${pageContext.request.contextPath}/super/list?offset=0">Super People</a></li>
    </ul>
</div>

<table>
    <c:forEach items="${viewModel.locations}" var="sighting">
        <tr>
            <td><a href="/sighting/show?id=${sighting.sightingId}">${sighting.location}
            </a></td>
            <td>${sighting.date}</td>
            <td><a href="/sighting/edit?id=${sighting.sightingId}">edit</a></td>
        </tr>
    </c:forEach>
</table>

<c:forEach items="${viewModel.pageNumbers}" var="pageNumber">
    <a href="/sighting/list?offset=${(pageNumber -1) *5}">${pageNumber}</a>
</c:forEach>

<br />
<a href="/sighting/create">Create New Sighting</a>

</body>
</html>