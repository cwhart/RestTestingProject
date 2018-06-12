<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
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
    <title>Profile</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container">
    <h1>H.E.R.O.</h1>
    <hr/>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation" ><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/location/list?offset=0">Locations</a></li>
            <li role="presentation" ><a href="${pageContext.request.contextPath}/organization/list?offset=0">Organizations</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/power/list?offset=0">Powers</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/sighting/list?offset=0">Sightings</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/super/list?offset=0">Super People</a></li>
        </ul>
    </div>
    <h2>Home Page</h2>
</div>
<div class="container">
    <p>
        Name: <c:out value="${viewModel.name}"/>
    </p>
    <p>
        Description: <c:out value="${viewModel.description}"/>
    </p>
    <p>
        Street: <c:out value="${viewModel.street}"/>
    </p>
    <p>
        City: : <c:out value="${viewModel.city}"/>
    </p>
    <p>
        State: : <c:out value="${viewModel.state}"/>
    </p>
    <p>
        Zip: <c:out value="${viewModel.zip}"/>
    </p>
    <p>
        Latitude: : <c:out value="${viewModel.latitude}"/>
    </p>
    <p>
        Longitude: : <c:out value="${viewModel.longitude}"/>
    </p>
</div>


<button type="submit">Back</button>
</body>
</html>