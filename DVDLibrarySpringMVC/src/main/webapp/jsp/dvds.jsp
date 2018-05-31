<%--
  Created by IntelliJ IDEA.
  User: N0148464
  Date: 5/10/2018
  Time: 9:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>DVD Library</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container ">

    <!-- Main Page Content Start -->

    <div class="row">
        <div >
            <h1>DVD Library</h1>
            <hr/>
            <div class="form-control">

                <a href="/jsp/addDvd.jsp"><button type="button" id="createButton">Create DVD</button></a>


                <button type="button" id="searchButton">Search</button></a>

                <select class="control-label" id="search-category" name="searchCategory">
                    <option value="title">Title</option>
                    <option value="releaseDate">Release Date</option>
                    <option value="last_name">Director Name</option>
                    <option value="rating">Rating</option>
                </select>

                <input type="text" id="searchCriteria" placeholder="Search Term">
            </div>

            <div class="col-md-10">
                <h2>My DVDs</h2>
                <table id="dvdTable" class="table table-hover" method="POST"
                       action="${pageContext.request.contextPath}/displayDvdsPage">
                    <tr>
                        <th width="20%">Title</th>
                        <th width="20%">Release Date</th>
                        <th width="20%">Director</th>
                        <th width="20%">Rating</th>
                        <th width="10%"></th>
                        <th width="10%"></th>
                    </tr>
                    <c:forEach var="currentDvd" items="${dvdList}">
                        <tr>
                            <td>
                                <a href="displayDvdDetails?dvdId=${currentDvd.dvdId}">
                                    <c:out value="${currentDvd.dvdTitle}"/>
                                </a>
                            </td>
                            <td>
                                <c:out value="${currentDvd.releaseDate}"/>
                            </td>
                            <td>
                                <c:out value="${currentDvd.director.last_name}"/>
                            </td>
                            <td>
                                <c:out value="${currentDvd.rating}"/>
                            </td>
                            <td>
                                <a href="displayEditDvdForm?dvdId=${currentDvd.dvdId}">
                                    Edit
                                </a>
                            </td>
                            <td>
                                <a href="deleteDvd?dvdId=${currentDvd.dvdId} " >
                                    Delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <!--
            Add a col to hold the summary table - have it take up half the row
        -->
         <!-- End col div -->
        <!--
            Add col to hold the new contact form - have it take up the other
            half of the row
        -->
         <!-- End col div -->

    </div> <!-- End row div -->

    <!-- Main Page Content Stop -->
</div>
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>


</body>
</html>
