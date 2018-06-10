<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%--
  Created by IntelliJ IDEA.
  User: n0148464
  Date: 6/8/2018
  Time: 9:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Sighting</title>
</head>
<body>
<sf:form action="/sighting/edit" method="post" modelAttribute="commandModel">
    <sf:hidden path="sightingId"></sf:hidden>

    <label for="locationId">Location Name:</label>
    <sf:select path="locationId">
        <sf:option value="" label="No location" />
        <sf:options items="${viewModel.location}" itemValue="id" itemLabel="name" />
    </sf:select>
    <sf:errors path="locationId"></sf:errors>
    <br/>

    <label for="date">Date:</label>
    <sf:input path="date"></sf:input>
    <sf:errors path="date"></sf:errors>
    <br/>

    <label for="superIds">Super People Present:</label>
    <sf:select path="superIds" multiple="true">
        <sf:options items="${viewModel.superPeople}" itemValue="id" itemLabel="name" />
    </sf:select>
    <sf:errors path="superIds"></sf:errors>
    <br/>

    <button type="submit">Save</button> <!--when clicked, the app will go to the url in the action above-->

</sf:form>

</body>
</html>