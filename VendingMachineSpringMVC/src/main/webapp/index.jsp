<%--
  Created by IntelliJ IDEA.
  User: N0148464
  Date: 5/11/2018
  Time: 10:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Vending Machine</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<!--<div class="container ">

    <!-- Main Page Content Start -->

<ul class="list-group" id="errorMessages"></ul>
<h1>Vending Machine</h1>
<hr/>



<!--<div method="GET" action="${pageContext.request.contextPath}/displayVendingMachine">-->

<div class="col-md-9" >

    <c:forEach var="currentItem" items="${itemList}">
        <div class="col-md-4" >
            <a href="selectItem?itemId=${currentItem.itemID}">
            <div class="well" id=${currentItem.itemID} >
                <p>Item #: <c:out value="${currentItem.itemID}"/></p>
               <p> <c:out value="${currentItem.itemName}"/></p>
                <p>Price: $<c:out value="${currentItem.itemPrice}"/></p>
                <p>Quantity:  <c:out value="${currentItem.itemQuantity}"/></p>


            </div>
            </a>
        </div>
    </c:forEach>

    </div>


<div class="col-md-3">

    <form> <!-- modelAttribute="item" action="/selectItem" method="GET"> -->
        <div class="form-group">
            <label for="total-money-in">Total $ In:</label>
            <input type="text" class="form-control" id="total-money-in" value="${totalMoneyIn}" readonly>
        </div>

        <div>
            <div class="form-group text-center">
               <!-- <button type="button" class="btn btn-outline-dark " id="add-dollar">Add Dollar</button>-->

                <a href="${pageContext.request.contextPath}/addDollar" type="button"
                   class="btn-sm btn-primary" name="adddollar-button"
                   id="addDollar-button">Add Dollar
                </a>

                <a href="${pageContext.request.contextPath}/addQuarter" type="button"
                   class="btn-sm btn-primary" name="addquarter-button"
                   id="addquarter-button">Add Quarter
                </a>

              <!--  <button type="button" class="btn btn-outline-dark " id="add-quarter">Add Quarter</button>  -->
            </div>
        </div>
        <div>
            <div class="form-group text-center">
         <a href="${pageContext.request.contextPath}/addDime" type="button"
                   class="btn-sm btn-primary" name="addDime-button"
                   id="addDime-button">Add Dime
                </a>
                <a href="${pageContext.request.contextPath}/addNickel" type="button"
                   class="btn-sm btn-primary" name="addNickel-button"
                   id="addNickel-button">Add Nickel
                </a>
             <!--   <button type="button" class="btn btn-outline-dark " id="add-dime">Add Dime</button>
                <button type="button" class="btn btn-outline-dark " id="add-nickel">Add Nickel</button>  -->
            </div>
        </div>
    </form>
    <hr/>

    <form>

        <div class="form-group">
            <label for="messages">Messages:</label>
            <input type="text" class="form-control" id="messages" value = "${messages}" readonly>
        </div>

        <div class="form-group">
            <label for="item">Item:</label>
            <input type="text" class="form-control" id="item" value="${item}" readonly />


        </div>

        <div>
            <div class="form-group">

                <a href="${pageContext.request.contextPath}/makePurchase" type="button"
                   class="btn-sm btn-primary" name="makePurchase-button"
                   id="makePurchase-button">Make Purchase
                </a>


            </div>
        </div>
    </form>

    <hr/>

    <form>

        <div class="form-group">
            <label for="change">Change:</label>
            <input type="text" class="form-control " style="font-size:13px" id="change" value="${change}" readonly>
        </div>

        <div>
            <div class="row; form-group">

                <a href="${pageContext.request.contextPath}/returnChange" type="button"
                   class="btn-sm btn-primary" name="returnChange-button"
                   id="returnChange-button">Return Change
                </a>

            </div>
        </div>

        <br/>



    </form>


</div>

<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>


</body>
</html>
