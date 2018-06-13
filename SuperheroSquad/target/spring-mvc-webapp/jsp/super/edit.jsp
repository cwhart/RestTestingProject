<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: N0160024
  Date: 6/10/2018
  Time: 5:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Super Person</title>
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
<sf:form action="/super/edit" method="post" modelAttribute="commandModel">
<sf:hidden path="id"></sf:hidden>

<div class="form-group">
    <label class="col-md-2" for="name">Name:</label>
    <div class="col-md-10">
<sf:input path="name"></sf:input>
<sf:errors path="name"></sf:errors>
    </div>
</div>

<br/>

<div class="form-group">
    <label class="col-md-2" for="description">Description:</label>
    <div class="col-md-10">
<sf:input path="description"></sf:input>
<sf:errors path="description"></sf:errors>
    </div>
</div>

<br/>

<div class="form-group">
    <label class="col-md-2" for="organizationId">Organizations:</label>
    <div class="col-md-10">
<sf:select path="organizationId">
    <sf:option value="" label="Choose an Organization"/>
    <sf:options items="${viewModel.organizations}" itemValue="id" itemLabel="name"/>
</sf:select>
<sf:errors path="organizationId"></sf:errors>
    </div>
</div>

<br/>

<div class="form-group">
    <label class="col-md-2" for="powerId">Powers:</label>
    <div class="col-md-10">
<sf:select path="powerId">
    <sf:option value="" label="Choose a Power"/>
    <sf:options items="${viewModel.powers}" itemValue="id" itemLabel="name"/>
</sf:select>
<sf:errors path="powerId"></sf:errors>
    </div>
</div>

<br/>

<button type="submit">Save</button>
<button type="submit">Cancel</button>
</sf:form>
</body>

</html>