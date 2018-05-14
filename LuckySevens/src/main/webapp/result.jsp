<%--
  Created by IntelliJ IDEA.
  User: N0148464
  Date: 5/8/2018
  Time: 1:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Result</title>
</head>
<body>
<h1>Result</h1>
<p>
    You bet ${amountBet}
</p>
<p>

    You are broke after ${totalRolls} rolls
</p>
<p>

    You should have quit after ${totalRollsAtMax} rolls when you had ${maxWon}.
</p>
<p>
    <a href="/LuckySevensServlet">Play Again!</a>
</p>

</body>
</html>
