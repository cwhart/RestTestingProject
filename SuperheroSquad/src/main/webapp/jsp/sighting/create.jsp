<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%--
  Created by IntelliJ IDEA.
  User: n0268611
  Date: 6/10/2018
  Time: 12:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Sighting</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="navbar">
    <ul class="nav nav-tabs">
        <li role="presentation"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
        <li role="presentation"><a href="${pageContext.request.contextPath}/location/list?offset=0">Locations</a></li>
        <li role="presentation" ><a href="${pageContext.request.contextPath}/organization/list?offset=0">Organizations</a></li>
        <li role="presentation"><a href="${pageContext.request.contextPath}/power/list?offset=0">Powers</a></li>
        <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/sighting/list?offset=0">Sightings</a></li>
        <li role="presentation"><a href="${pageContext.request.contextPath}/super/list?offset=0">Super People</a></li>
    </ul>
</div>
<h1 class="text-center">Create Sighting</h1>
<hr/>

<sf:form action="/sighting/create" method="post" modelAttribute="commandModel">
    <label for="locationId">Location:</label>
    <sf:select path="locationId">
        <sf:option value="" label="No Location"/>
        <sf:options items="${viewModel.location}" itemValue="id" itemLabel="name"/>
    </sf:select>
    <sf:errors path="locationId"></sf:errors>
    <br/>

    <label for="date">Date:</label>
    <sf:input type="text" path="date"></sf:input>
    <sf:errors path="date"></sf:errors>
    <br/>

    <label for="superId">Super People:</label>
    <sf:select path="superId" multiple="true">
        <sf:options items="${viewModel.superPeople}" itemValue="id" itemLabel="name" />
    </sf:select>
    <sf:errors path="superId"></sf:errors>
    <br/>

    <div class="form-group">
        <div class="col-md-offset-2 col-md-8">
            <button type="submit">Create</button>
            <%--<button><a href="${pageContext.request.contextPath}/sighting/list">Back</a></button>--%>
        </div>
    </div>
</sf:form>
</body>
</html>