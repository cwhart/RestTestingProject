<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
    <h1>Hero Education and Relationship Organization (H.E.R.O.)</h1>
    <hr/>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation" ><a href="${pageContext.request.contextPath}/">Home</a></li>
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
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <td><a href="${pageContext.request.contextPath}/power/edit?id=${power.id}">Edit</a></td>
                    <td><a href="${pageContext.request.contextPath}/power/delete?id=${power.id}" class="delete">
                        Delete</a></td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </table>

        <c:forEach items="${viewModel.pageNumbers}" var="pageNumber">
            <a href="${pageContext.request.contextPath}/power/list?offset=${ (pageNumber - 1) * 5}">${pageNumber}</a>
        </c:forEach>

        <br />
        <sec:authorize access="hasRole('ROLE_ADMIN')">

        <a href="${pageContext.request.contextPath}/power/create">Create New Power</a>
        </sec:authorize>

    </div>
</div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/delete.js"></script>



</body>
</html>