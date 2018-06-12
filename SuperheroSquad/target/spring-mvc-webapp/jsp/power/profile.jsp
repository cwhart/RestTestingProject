<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: n0268611
  Date: 6/10/2018
  Time: 7:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Power Details</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container">
    <h1>H.E.R.O.</h1>
    <hr/>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation" ><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/location/list?offset=0">Locations</a></li>
            <li role="presentation" ><a href="${pageContext.request.contextPath}/organization/list?offset=0">Organizations</a></li>
            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/power/list?offset=0">Powers</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/sighting/list?offset=0">Sightings</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/super/list?offset=0">Super People</a></li>
        </ul>
    </div>
    <h2>Home Page</h2>
</div>
<p>
    Name: <c:out value="${power.name}"/>
</p>
<p>
    Super People:
<table>
    <c:forEach items="${viewModel.powers}" var="power">
        <tr>
            <td>${power.superPeople}</td>
        </tr>
    </c:forEach>
</table>
</p>

</body>
</html>