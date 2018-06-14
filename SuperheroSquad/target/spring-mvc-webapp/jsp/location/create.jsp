<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: n0239947
  Date: 6/9/2018
  Time: 9:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Location Create</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <h1>H.E.R.O.</h1>
    <hr/>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/location/list?offset=0">Locations</a></li>
            <li role="presentation" ><a href="${pageContext.request.contextPath}/organization/list?offset=0">Organizations</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/power/list?offset=0">Powers</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/sighting/list?offset=0">Sightings</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/super/list?offset=0">Super People</a></li>
        </ul>
    </div>

</div>
<sf:form action="/location/create" method="post" modelAttribute="commandModel" >

<div class="form-group">
    <label class="col-md-2" for="name">Name:</label>
    <div class="col-md-10">
    <sf:input path="name"></sf:input>
    <sf:errors path="name"></sf:errors>
    </div>
</div>


    <div class="form-group">
        <label class="col-md-2" for="name">Description:</label>
        <div class="col-md-10">
    <sf:input path="description"></sf:input>
    <sf:errors path="description"></sf:errors>
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-2" for="name">Street:</label>
        <div class="col-md-10">
      <sf:input path="street"></sf:input>
    <sf:errors path="street"></sf:errors>
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-2" for="name">City:</label>
        <div class="col-md-10">
       <sf:input path="city"></sf:input>
    <sf:errors path="city"></sf:errors>
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-2" for="name">State:</label>
        <div class="col-md-10">
       <sf:input path="state"></sf:input>
    <sf:errors path="state"></sf:errors>
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-2" for="name">Zip:</label>
        <div class="col-md-10">
      <sf:input path="zip"></sf:input>
    <sf:errors path="zip"></sf:errors>
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-2" for="name">Latitude:</label>
        <div class="col-md-10">
       <sf:input path="latitude"></sf:input>
    <sf:errors path="latitude"></sf:errors>
        </div>
    </div>


    <div class="form-group">
        <label class="col-md-2" for="name">Longitude:</label>
        <div class="col-md-10">
      <sf:input path="longitude"></sf:input>
    <sf:errors path="longitude"></sf:errors>
        </div>
    </div>
    </div>

    <button type="submit">Create</button>
    <button type="cancel">Cancel</button>

</sf:form>
</body>
</html>