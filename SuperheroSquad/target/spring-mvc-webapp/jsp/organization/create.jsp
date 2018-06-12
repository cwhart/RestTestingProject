<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: n0158583
  Date: 6/10/2018
  Time: 12:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>H.E.R.O.</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<sec:authorize access="isAuthenticated()">
    <p>
        This is only visible to users who are logged in.
    </p>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <p>
        This is only visible to users who also have the ADMIN role.
    </p>
</sec:authorize>
<div class="container">
    <h1>H.E.R.O.</h1>
    <hr/>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/location/list?offset=0">Locations</a></li>
            <li role="presentation"  class="active"><a href="${pageContext.request.contextPath}/organization/list?offset=0">Organizations</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/power/list?offset=0">Powers</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/sighting/list?offset=0">Sightings</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/super/list?offset=0">Super People</a></li>
        </ul>
    </div>
    <h2>Home Page</h2>
</div>
<h1>Create Organization</h1>
<sf:form action="/organization/create" method="post" modelAttribute="commandModel">
    <label for="name">Organization Name:</label>
    <sf:input path="name"></sf:input>
    <sf:errors path="name"></sf:errors>
    <br/>
    <label for="description">Description:</label>
    <sf:input path="description"></sf:input>
    <sf:errors path="description"></sf:errors>
    <br/>
    <label for="locationId">Location:</label>
    <sf:select path="locationId">
        <sf:option value="" label="No Location"/>
        <sf:options items="${viewModel.locations}" itemValue="id" itemLabel="name"/>
    </sf:select>
    <sf:errors path="locationId"></sf:errors>
    <br/>
    <label for="phone">Phone:</label>
    <sf:input path="phone"></sf:input>
    <sf:errors path="phone"></sf:errors>
    <br/>
    <label for="email">Email:</label>
    <sf:input path="email"></sf:input>
    <sf:errors path="email"></sf:errors>
    <br/>

    <button type="submit">Create</button>

    <%--<a href="/jsp/organization/list.jsp"><button type="submit">Create</button>--%>
</sf:form>
</body>
</html>