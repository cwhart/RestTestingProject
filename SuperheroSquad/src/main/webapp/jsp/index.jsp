<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>Welcome to SuperPerson Sightings!!!</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Bangers|Oswald:300" rel="stylesheet">

</head>
<body>
<div class="logout pull-right">
    <%--<c:if test="${pageContext.request.userPrincipal.name != null}">--%>
        <div>You are logged in as: <b>${pageContext.request.userPrincipal.name}</b>
            | <button><a href="<c:url value="/login" />" > Login</a></button>
            <button><a href="<c:url value="/j_spring_security_logout" />" > Logout</a></button>
        </div>
    <%--</c:if>--%>
</div>
<<br>
<br>
<div class="container">
    <div><h1 class="text-center">Hero Education and Relationship Organization (H.E.R.O)</h1></div>

    <hr/>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/location/list?offset=0">Locations</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/organization/list?offset=0">Organizations</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/power/list?offset=0">Powers</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/sighting/list?offset=0">Sightings</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/super/list?offset=0">Super People</a></li>
            <li>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li role="presentation">
                <a href="/displayUserList">User Admin</a></li>
            </sec:authorize>
            </li>
        </ul>
    </div>


    <div class="row">
        <div class="column col-md-4"><article><h2><b>About Us</b></h2>
            <p class="text-justify">
                Superhero Squad allows you to see the latest superhero and supervillain sightings.
                We allow our users to add sightings and locations of
                spotted superheroes and villains. We also allow our users to see all superhero and
                supervillain organizations.
            </p>
            <p class="text-justify">Be sure to visit regularly or sign up for our alert APP coming soon to your favorite APP store.</p>
        </article></div>

        <div class="column col-md-8">
            <div class="col-md-8">
                <h2>Sightings</h2>
                <table id="sightings" class="table table-hover">
                    <tr>
                        <th width="40%">Sighting Location</th>
                        <th width="30%">Date</th>
                    </tr>

                    <c:forEach items="${viewModel.locations}" var="sighting">
                        <tbody>
                        <tr>
                            <td>
                                <a href="${pageContext.request.contextPath}/location/profile?id=${sighting.locationId}">${sighting.location}</a>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/sighting/profile?id=${sighting.sightingId}">${sighting.date}</a>
                            </td>
                        </tr>
                        </tbody>
                    </c:forEach>
                </table>

                <div class="col-md-12 text-center">
                    <ul class="pagination">
                        <c:if test="${viewModel.selectedPage>1}">
                            <li class="page-item"><a href="${pageContext.request.contextPath}/sighting/list?offset=${(((viewModel.selectedPage)-2)*10)}" class="page-link">Previous</a></li>
                        </c:if>
                        <c:forEach items="${viewModel.pageNumbers}" var="pageNumber">
                            <li class="page-item"><a href="${pageContext.request.contextPath}/sighting/list?offset=${(pageNumber -1) * 10}" class="page-link">${pageNumber}</a></li>
                        </c:forEach>
                    </ul>
                </div>

            </div>
        </div>
    </div>

</div>
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>

