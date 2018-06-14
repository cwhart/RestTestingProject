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
    <title>Sighting Details</title>
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
        <li role="presentation" ><a href="${pageContext.request.contextPath}/organization/list?offset=0">Organizations</a></li>
        <li role="presentation"><a href="${pageContext.request.contextPath}/power/list?offset=0">Powers</a></li>
        <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/sighting/list?offset=0">Sightings</a></li>
        <li role="presentation"><a href="${pageContext.request.contextPath}/super/list?offset=0">Super People</a></li>
    </ul>
</div>
<br/>
<div class="container">

    <h1 class="text-center">Sighting Details</h1>
    <hr/>
    <div class="col-md-12">
        <div class="col-md-2">Location:</div>
        <div class="col-md-10"><c:out value="${viewModel.location}"/></div>
    </div>

    <div class="col-md-12">
        <div class="col-md-2">Date:</div>
        <div class="col-md-10"><c:out value="${viewModel.date}"/></div>
    </div>

    <div class="col-md-12">
        <div class="col-md-2">Super People:</div>
        <div class="col-md-10">
            <table>
                <c:forEach items="${viewModel.superList}" var="supers">
                    <tr>
                        <td>
                            <a href="${pageContext.request.contextPath}/super/profile?id=${supers.id}">${supers.superPersonName}</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>


</div>
</div>
</body>
</html>