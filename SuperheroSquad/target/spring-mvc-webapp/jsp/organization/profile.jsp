<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: n0158583
  Date: 6/10/2018
  Time: 12:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>H.E.R.O.</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container">
    <h1>H.E.R.O.</h1>
    <hr/>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/location/list?offset=0">Locations</a></li>
            <li role="presentation" class="active" ><a href="${pageContext.request.contextPath}/organization/list?offset=0">Organizations</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/power/list?offset=0">Powers</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/sighting/list?offset=0">Sightings</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/super/list?offset=0">Super People</a></li>
        </ul>
    </div>
</div>

<h1>Organization Details</h1>
<br />
<hr/>

<div class="container">
    <p>
        Name: <c:out value="${viewModel.name}"/>
    </p>
    <p>
        Description: <c:out value="${viewModel.description}"/>
    </p>
    <p>
        <a href="/location/profile?id=${viewModel.locatonId}"><c:out value="${viewModel.location}"/></a>
    </p>
    <p>
        Phone: : <c:out value="${viewModel.phone}"/>
    </p>
    <p>
        Email: : <c:out value="${viewModel.email}"/>
    </p>
</div>
</body>
</html>