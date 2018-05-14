<%--
  Created by IntelliJ IDEA.
  User: N0148464
  Date: 5/8/2018
  Time: 9:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Total Cost</title>
</head>
<body>
<h1>Total Cost</h1>
<p>
    Total material cost: ${materialCost}
</p>
<p>
    Total time required: ${totalTime}
</p>
<p>
    Total labor cost: ${laborCost}
</p>
<p>
    Total estimate: ${totalCost}
</p>
<p>
    <a href="/FlooringCalculatorServlet">Calculate Another One!</a>
</p>
</body>
</html>
