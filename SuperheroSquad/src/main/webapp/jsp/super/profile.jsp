<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Super Person Details</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>H.E.R.O.</h1>
    <hr/>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/location/list?offset=0">Locations</a></li>
            <li role="presentation" ><a href="${pageContext.request.contextPath}/organization/list?offset=0">Organizations</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/power/list?offset=0">Powers</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/sighting/list?offset=0">Sightings</a></li>
            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/super/list?offset=0">Super People</a></li>
        </ul>
    </div>
    <h2>Home Page</h2>
</div>

<p>
    Name: <c:out value="${viewModel.name}"/>
</p>

<p>
    Description: <c:out value="${viewModel.description}"/>
</p>

<p>
<div class="col-md-12">
    <div class="col-md-2">Organizations:</div>
    <div class="col-md-10">
        <table>
            <c:forEach items="${viewModel.organizationList}" var="org">
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/organization/profile?id=${org.id}">${org.name}</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</p>

<p>
<div class="col-md-12">
    <div class="col-md-2">Locations Seen:</div>
    <div class="col-md-10">
        <table>
            <c:forEach items="${viewModel.sightingList}" var="sightings">
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/location/profile?id=${sightings.id}">${sightings.name}</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</p>

<p>
<div class="col-md-12">
    <div class="col-md-2">Powers:</div>
    <div class="col-md-10">
        <table>
            <c:forEach items="${viewModel.powerList}" var="power">
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/power/profile?id=${power.id}">${power.name}</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</p>

<button type="submit">Back</button>

</body>
</html>