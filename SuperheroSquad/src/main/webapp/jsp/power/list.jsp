<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: n0268611
  Date: 6/8/2018
  Time: 9:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Powers</title>
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

<div class="row">
    <div class="col-md-6">
        <h2>Powers</h2>
        <table id="powers" class="table table-hover">
            <tr>
                <th width="50%">Power Name</th>
                <th width="25%"></th>
                <th width="25%"></th>
            </tr>
            <c:forEach var="power" items="${viewModel.powers}" >
                <tr>
                    <td><a href="${pageContext.request.contextPath}/power/profile?id=${power.id}">${power.name}</a></td>
                    <td><a href="${pageContext.request.contextPath}/power/edit?id=${power.id}">Edit</a></td>
                    <td><a href="${pageContext.request.contextPath}/power/delete?id=${power.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>

        <c:forEach items="${viewModel.pageNumbers}" var="pageNumber">
            <a href="${pageContext.request.contextPath}/power/list?offset=${ (pageNumber - 1) * 5}">${pageNumber}</a>
        </c:forEach>

        <br />
        <a href="${pageContext.request.contextPath}/power/create">Create New Power</a>

    </div>
</div>
</div>

<%--<table>
    <c:forEach items="${viewModel.powers}" var="power">
        <tr>
            <td><a href="/power/show?id=${power.id}">${power.name}</a></td>
            <td><a href="/power/edit?id=${power.id}">Edit</a> </td>
        </tr>
    </c:forEach>
</table>

<c:forEach items="${viewModel.pageNumbers}" var="pageNumber">
    <a href="/power/list?offset=${(pageNumber-1) *5}">${pageNumber}</a>  <!--saying we have 5 per page-->
</c:forEach>

<br/>
<a href="power/create">Create New Player</a>--%>

</body>
</html>