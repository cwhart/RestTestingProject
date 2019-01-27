<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>

<%--
  Created by IntelliJ IDEA.
  User: n0268611
  Date: 6/10/2018
  Time: 12:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your Bill</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body >
<div class="container">

    <div><h1>Your Bill</h1></div>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/reservation/displayRooms">Rooms</a></li>
        </ul>
    </div>

    <div class="row">
        <div class="col-md-6">
            <h2>Charges</h2>
            <table id="charges" class="table table-hover">
                <tr>
                    <th width="50%">Description</th>
                    <th width="25%">Base Price</th>
                    <th width="25%">Discounted Price</th>
                    <th width="25%">Tax</th>
                    <th width="25%">Transaction Date</th>
                </tr>
                <c:forEach var="roomCharge" items="${roomViewModels}" >
                    <tr>
                        <td>${roomCharge.description}</td>
                        <td>${roomCharge.basePrice}</td>
                        <td>${roomCharge.discountedPrice}</td>
                        <td>${roomCharge.taxAmount}</td>
                        <td>${roomCharge.transactionDate}</td>

                    </tr>
                </c:forEach>
                <c:forEach var="addOnCharge" items="${addOnViewModels}" >
                    <tr>
                        <td>${addOnCharge.description}</td>
                        <td>${addOnCharge.basePrice}</td>
                        <td>${addOnCharge.discountedPrice}</td>
                        <td>${addOnCharge.taxAmount}</td>
                        <td>${addOnCharge.transactionDate}</td>

                    </tr>
                </c:forEach>
            </table>
            <table id="total" class="table table-hover">
                <tr>
                    <%--<th width="75%">Total</th>--%>
                    <%--<th width="25%">Price</th>--%>
                    <td>Your Total Charges:</td>
                    <td>${viewModel.total}</td>
                </tr>
            </table>


        </div>
    </div>
</div>
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/delete.js"></script>

</body>
</html>