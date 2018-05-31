<%--
  Created by IntelliJ IDEA.
  User: N0148464
  Date: 5/10/2018
  Time: 11:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>DVD Library</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Edit DVD</h1>
    <hr/>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation">
                <a href="${pageContext.request.contextPath}/jsp/index.jsp">
                    Home
                </a>
            </li>
            <li role="presentation">
                <a href="${pageContext.request.contextPath}/displayDvdsPage">
                    DVDs
                </a>
            </li>
            <li role="presentation">
                <a href="${pageContext.request.contextPath}/displaySearchPage">
                    Search
                </a>
            </li>
        </ul>
    </div>
    <sf:form class="form-horizontal" role="form" modelAttribute="dvd"
             action="editDvd" method="POST">
        <div class="form-group">
            <label for="add-dvd-title" class="col-md-4 control-label">Title:</label>
            <div class="col-md-8">
                <sf:input type="text" class="form-control" id="add-dvd-title"
                          path="dvdTitle" placeholder="Title"/>
                <sf:errors path="dvdTitle" cssclass="error"></sf:errors>
            </div>
        </div>
        <div class="form-group">
            <label for="add-director" class="col-md-4 control-label">Director:</label>
            <div class="col-md-8">
                <sf:input type="text" class="form-control" id="add-director"
                          path="director.last_name" placeholder="Director"/>
                <sf:errors path="director" cssclass="error"></sf:errors>
            </div>
        </div>
        <div class="form-group">
            <label for="add-release-date" class="col-md-4 control-label">Release Date:</label>
            <div class="col-md-8">
                <sf:input type="text" class="form-control" id="add-release-date"
                          path="releaseDate" placeholder="Release Date"/>
                <sf:errors path="releaseDate" cssclass="error"></sf:errors>
            </div>
        </div>
        <div class="form-group">
            <label for="add-rating" class="col-md-4 control-label">Rating:</label>
            <div class="col-md-8">
                <select class="form-control" id="add-rating" name="rating">
                    <option value="G">G</option>
                    <option value="PG">PG</option>
                    <option value="PG-13">PG-13</option>
                    <option value="R">R</option>
                </select>
                <!-- <input type="text" class="form-control" id="add-rating"
                        name="rating" placeholder="Rating"/> -->
            </div>
        </div>
        <div class="form-group">
            <label for="add-notes" class="col-md-4 control-label">Notes:</label>
            <div class="col-md-8">
                <sf:input type="text" class="form-control" id="add-notes"
                          path="notes" placeholder="Notes"/>
                <sf:errors path="notes" cssclass="error"></sf:errors>
                <sf:hidden path="dvdId"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-offset-4 col-md-8">
                <input type="submit" class="btn btn-default" value="Update DVD"/>
            </div>
        </div>
    </sf:form>
</div>
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>