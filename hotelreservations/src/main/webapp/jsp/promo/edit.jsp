<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: N0160024
  Date: 6/10/2018
  Time: 5:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Promotion</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Promotion Details</h1>
    <hr/>
    <div class="navbar">

            <ul class="nav nav-tabs">
                <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/reservation/displayRooms">Rooms</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/reservation/searchReservation">Search</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/promo/list">Promotions</a></li>
            </ul>

    </div>
</div>
<sf:form action="/promo/edit" method="post" modelAttribute="commandModel">
    <sf:hidden path="id"></sf:hidden>
    <%--<sf:hidden path="rateId"></sf:hidden>--%>
    <%--<sf:hidden path="promoTypeId"></sf:hidden>--%>


    <%--${viewModel.message}--%>

<p>
    Promo ID: <c:out value="${commandModel.id}"/>
</p>

    <div class="form-group">
        <label class="col-md-2" for="promoTypeId">Promotion:</label>
        <div class="col-md-10">
            <sf:select path="promoTypeId" multiple="false">
                <sf:option value="${commandModel.promoTypeId}" label="${commandModel.promoCodeDescription}" />
                <sf:options items="${viewModel.promoTypes}" itemValue="id" itemLabel="name"  />
            </sf:select>
            <sf:errors path="promoTypeId"></sf:errors>
        </div>
    </div>
     <br/>
    <p>
    <div class="form-group">
        <label class="col-md-2" for="rateId">Rates:</label>
        <div class="col-md-10">
            <sf:select path="rateId" multiple="false">
                <sf:option value="${commandModel.rateId}" label="${commandModel.rate}" />
                <sf:options items="${viewModel.promoRates}" itemValue="id" itemLabel="name"  />
            </sf:select>
            <sf:errors path="rateId"></sf:errors>
        </div>
    </div>
    </p>
    <br/>
    <p>
    Promotion Start Date:
    <sf:input type="date" path="startDate" ></sf:input>
    <br/>
    </p>
    <p>
    Promotion End Date:
    <sf:input type="date" path="endDate"></sf:input>
    <br/>
    </p>
    <button type="submit">Save</button>


</sf:form>


</body>
</html>