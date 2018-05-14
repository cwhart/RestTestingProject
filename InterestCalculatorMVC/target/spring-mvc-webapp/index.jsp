<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Interest Calculator</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Interest Calculator</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                	<li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/index.jsp">
                            Home
                        </a>
                    </li>
                	<li role="presentation">
                        <a href="${pageContext.request.contextPath}/hello/sayhi">
                            Hello Controller
                        </a>
                    </li>
                </ul>    
            </div>
            <h2>Enter Your Loan Information</h2>
            <form method="POST" action="calculateInterest">
                <label for="loanPrincipal">Loan Principal Amount:</label>
                <input type="text" name="loanPrincipal" id="loanPrincipal"/>
                <br/>
                <label for="interestRate">Interest Rate Percentage:</label>
                <input type="text" name="interestRate" id="interestRate"/>
                <br/>
                <label for="numYears">Number of Years:</label>
                <input type="text" name="numYears" id="numYears"/>
                <br/>
                <input type="submit" value="Calculate Interest"/>

            </form>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

