<%--
  Created by IntelliJ IDEA.
  User: N0148464
  Date: 5/8/2018
  Time: 1:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Result</title>
</head>
<body>
<h1>Result</h1>
<p>
    Amount: ${totalFood}
    Tip %: ${tipPercentage}
    Tip Amount: ${tipAmount}
    Total Bill: ${totalBill}
</p>
<p>
    <a href="/TipCalculatorServlet">Calculate another one!</a>
</p>
</body>
</html>