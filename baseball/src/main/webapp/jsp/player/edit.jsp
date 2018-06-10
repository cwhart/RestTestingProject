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
    <title>Title</title>
</head>
<body>
<sf:form action="/player/edit" method="post" modelAttribute="commandModel">
    <sf:hidden path="id"></sf:hidden>

    <label for="first">First Name:</label>
    <sf:input path="first"></sf:input>

    <sf:errors path="first"></sf:errors>
    <br/>

    <label for="last">Last Name:</label>
    <sf:input path="last"></sf:input>
    <sf:errors path="last"></sf:errors>
    <br/>

    <label for="teamId">Team:</label>
    <sf:select path="teamId">
        <sf:option value="" label="No team" />
        <sf:options items="${viewModel.teams}" itemValue="id" itemLabel="name" />
    </sf:select>
    <sf:errors path="teamId"></sf:errors>
    <br/>

    <br/>

    <label for="positionIds">Positions:</label>
    <sf:select path="positionIds" multiple="true">
        <sf:options items="${viewModel.positions}" itemValue="id" itemLabel="name" />
    </sf:select>
    <sf:errors path="positionIds"></sf:errors>
    <br/>

    <button type="submit">Save</button> <!--when clicked, the app will go to the url in the action above-->

</sf:form>

</body>
</html>
