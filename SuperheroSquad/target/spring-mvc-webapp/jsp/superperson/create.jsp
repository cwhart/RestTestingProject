<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%--
  Created by IntelliJ IDEA.
  User: N0160024
  Date: 6/10/2018
  Time: 5:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Super Person</title>
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

<sf:form action="/super/create" method="post" modelAttribute="commandModel">

    <label for="name">Name:</label>
    <sf:input path="name"></sf:input>
    <sf:errors path="name"></sf:errors>

    <br/>

    <label for="description">Description:</label>
    <sf:input path="description"></sf:input>
    <sf:errors path="description"></sf:errors>

    <br/>

    <label for="organizationId">Organizations:</label>
    <sf:select path="organizationId" multiple="true">
        <sf:option value="" label="Choose an Organization"/>
        <sf:options items="${viewModel.organizations}" itemValue="id" itemLabel="name"/>
    </sf:select>
    <sf:errors path="organizationId"></sf:errors>

    <br/>

    <label for="powerId">Powers:</label>
    <sf:select path="powerId" multiple="true">
        <sf:option value="" label="Choose a Power"/>
        <sf:options items="${viewModel.powers}" itemValue="id" itemLabel="name"/>
    </sf:select>
    <sf:errors path="powerId"></sf:errors>

    <button type="submit">Create</button>
    <button type="reset">Cancel</button>
</sf:form>

</body>
</html>