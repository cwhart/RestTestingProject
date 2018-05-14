<%--
  Created by IntelliJ IDEA.
  User: N0148464
  Date: 5/8/2018
  Time: 10:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UFT-8">
    <title>Results</title>
</head>
<body>
    <h1>Result:</h1>
    <p >
        <table>
        <c:forEach var="year" items="${year}">

            <c:out value="year: ${year}  " />
            <br/>

        </c:forEach>

        </table>
    </p>

</body>
</html>
