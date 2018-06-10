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
    <title>Create Sighting</title>
</head>
<body>
<sf:form action="/sighting/create" method="post" modelAttribute="commandModel">

    <label for="locationId">Location Name:</label>
    <sf:select path="locationId">
        <sf:option value="" label="No location" />
        <sf:options items="${viewModel.location}" itemValue="id" itemLabel="name" />
    </sf:select>
    <sf:errors path="locationId"></sf:errors>
    <br/>

    <label for="date">Sighting Date:</label>
    <sf:input path="date"></sf:input>
    <sf:errors path="date"></sf:errors>
    <br/>

    <label for="superId">Super People Present:</label>
    <sf:select multiple="true" path="superId" >
        <sf:option value="" label="No supers" />
        <sf:options items="${viewModel.superPeople}" itemValue="id" itemLabel="name" />
    </sf:select>
    <sf:errors path="superId"></sf:errors>
    <br/>

    <button type="submit">Save</button> <!--when clicked, the app will go to the url in the action above-->

</sf:form>

</body>
</html>