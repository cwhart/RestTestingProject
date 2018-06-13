<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%--
  Created by IntelliJ IDEA.
  User: n0268611
  Date: 6/9/2018
  Time: 9:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Location</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>H.E.R.O.</h1>
    <hr/>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/location/list?offset=0">Locations</a></li>
            <li role="presentation" ><a href="${pageContext.request.contextPath}/organization/list?offset=0">Organizations</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/power/list?offset=0">Powers</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/sighting/list?offset=0">Sightings</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/super/list?offset=0">Super People</a></li>
        </ul>
    </div>

</div>
<h1 class="text-center">Edit Location</h1>
<hr/>

<sf:form action="/location/edit" method="post" modelAttribute="commandModel">
    <sf:hidden path="id"></sf:hidden>

    <div class="form-group">
        <label class="col-md-2" for="name">Name:</label>
        <div class="col-md-10">
            <sf:input path="name"></sf:input>
            <sf:errors path="name"></sf:errors>
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-2" for="description">Description:</label>
        <div class="col-md-10">
            <sf:input path="description"></sf:input>
            <sf:errors path="description"></sf:errors>
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-2" for="street">Street:</label>
        <div class="col-md-10">
            <sf:input path="street"></sf:input>
            <sf:errors path="street"></sf:errors>
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-2" for="city">City:</label>
        <div class="col-md-10">
            <sf:input path="city"></sf:input>
            <sf:errors path="city"></sf:errors>
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-2" for="state">State:</label>
        <div class="col-md-10">
            <sf:input path="state"></sf:input>
            <sf:errors path="state"></sf:errors>
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-2" for="zip">Zip:</label>
        <div class="col-md-10">
            <sf:input path="zip"></sf:input>
            <sf:errors path="zip"></sf:errors>
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-2" for="latitude">Latitude:</label>
        <div class="col-md-10">
            <sf:input path="latitude"></sf:input>
            <sf:errors path="latitude"></sf:errors>
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-2" for="longitude">Longitude:</label>
        <div class="col-md-10">
            <sf:input path="longitude"></sf:input>
            <sf:errors path="longitude"></sf:errors>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-offset-2 col-md-8">
            <button type="submit">Save</button>
            <%--<button><a href="${pageContext.request.contextPath}/location/list">Back</a></button>--%>
        </div>
    </div>

</sf:form>

</body>
</html>