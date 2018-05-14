<%--
  Created by IntelliJ IDEA.
  User: N0148464
  Date: 5/10/2018
  Time: 11:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>DVD Library</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container" >
    <h1>DVD Details</h1>
    <hr/>

    <p>
        Title: <c:out value="${dvd.dvdTitle}"/>
    </p>
    <p>
        Release Year: <c:out value="${dvd.releaseYear}"/>
    </p>
    <p>
        Director: <c:out value="${dvd.director}"/>
    </p>
    <p>
        Rating: <c:out value="${dvd.rating}"/>
    </p>
    <p>
        Notes: <c:out value="${dvd.notes}"/>
    </p>
</div>
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>