<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%--
  Created by IntelliJ IDEA.
  User: n0268611
  Date: 6/8/2018
  Time: 9:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Sighting</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>H.E.R.O.</h1>
    <hr/>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
            <li role="presentation" ><a href="${pageContext.request.contextPath}/location/list?offset=0">Locations</a></li>
            <li role="presentation" ><a href="${pageContext.request.contextPath}/organization/list?offset=0">Organizations</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/power/list?offset=0">Powers</a></li>
            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/sighting/list?offset=0">Sightings</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/super/list?offset=0">Super People</a></li>
        </ul>
    </div>

</div>

<h1 class="text-center">Edit Sighting</h1>
<hr/>

<div class="row" style="width:80%; margin: auto">
    <sf:form action="/sighting/edit" method="post" modelAttribute="commandModel">
        <sf:hidden path="sightingId"></sf:hidden>

        <div class="form-group col-md-12">
            <label class="col-md-2 control-label" for="locationId">Location Name:</label>
            <div class="col-md-10">
                <sf:select path="locationId">
                    <sf:option value="" label="No location"/>
                    <sf:options items="${viewModel.location}" itemValue="id" itemLabel="name"/>
                </sf:select>
                <sf:errors path="locationId"></sf:errors>
            </div>
        </div>

        <div class="form-group col-md-12">
            <label class="col-md-2 control-label" for="date">Date:</label>
            <div class="col-md-10">
                <sf:input path="date"></sf:input>
                <sf:errors path="date"></sf:errors>
            </div>
        </div>

        <div class="form-group col-md-12">
            <label class="col-md-2 control-label" for="superIds">Super People Present:</label>
            <div class="col-md-10">
                <sf:select path="superIds" multiple="true">
                    <sf:options items="${viewModel.superPeople}" itemValue="id" itemLabel="name"/>
                </sf:select>
                <sf:errors path="superIds"></sf:errors>
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-offset-2 col-md-8">
                <button type="submit">Save</button>

            </div>
        </div>

    </sf:form>
</div>
</body>
</html>