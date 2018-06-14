<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: N0160024
  Date: 6/10/2018
  Time: 5:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Super Person List</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Hero Education and Relationship Organization (H.E.R.O.)</h1>
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

    <div class="row">
        <div class="col-md-6">
            <h2>Super People</h2>
            <table id="supers" class="table table-hover">
                <tr>
                    <th width="50%">Super Person Name</th>
                    <th width="25%"></th>
                    <th width="25%"></th>
                </tr>

    <c:forEach items="${viewModel.superPersons}" var="superPerson">
        <tr>
            <td><a href="${pageContext.request.contextPath}/super/profile?id=${superPerson.id}">${superPerson.name}</a></td>
            <td><a href="${pageContext.request.contextPath}/super/edit?id=${superPerson.id}">Edit</a></td>
            <td><a href="${pageContext.request.contextPath}/super/delete?id=${superPerson.id}">Delete</a></td>
        </tr>
    </c:forEach>

</table>

<c:forEach items="${viewModel.pageNumbers}" var="pageNumber">
    <a href="/super/list?offset=${ (pageNumber - 1) * 5}">${pageNumber}</a>
</c:forEach>

<br />

<a href="/super/create">Create</a>
</div>
    </div>
</div>
</body>
</html>