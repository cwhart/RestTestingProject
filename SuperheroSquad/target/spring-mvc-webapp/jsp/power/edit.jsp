<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: n0268611
  Date: 6/10/2018
  Time: 7:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Power</title>
</head>
<body>
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
    <title>Edit Power</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container">
    <h1>H.E.R.O.</h1>
    <hr/>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/location/list?offset=0">Locations</a></li>
            <li role="presentation" ><a href="${pageContext.request.contextPath}/organization/list?offset=0">Organizations</a></li>
            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/power/list?offset=0">Powers</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/sighting/list?offset=0">Sightings</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/super/list?offset=0">Super People</a></li>
        </ul>
    </div>
    <h2>Home Page</h2>
</div>
<sf:form action="/power/edit" method="post" modelAttribute="commandModel">
    <sf:hidden path="id"></sf:hidden>

<div class="form-group">
    <label class="col-md-2" for="name">Power Name:</label>
    <div class="col-md-10">
    <sf:input path="name"></sf:input>
    <sf:errors path="name"></sf:errors>
    </div>
</div>
    <br/>

    <br/>
<div class="form-group">
    <label class="col-md-2" for="name">Super People:</label>
    <div class="col-md-10">
    <sf:select path="superId" multiple="true">
        <sf:options items="${viewModel.superPeople}" itemValue="id" itemLabel="name" />
    </sf:select>
    <sf:errors path="superId"></sf:errors>
    </div>
</div>
    <br/>

    <button type="submit">Save</button>
</sf:form>
</body>
</html>