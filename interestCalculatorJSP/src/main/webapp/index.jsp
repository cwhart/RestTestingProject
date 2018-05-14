<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Tip Calculator</title>
</head>
<body>
<h1>Tip Calculator</h1>

<form method="post" action="InterestCalculatorServlet">
    <p>
        Please enter the amount of the bill:
    </p>
    <input type="text" name="billTotal"/><br/>
    <p>
        Please enter the percentage you want to tip:
    </p>
    <input type="text" name="tipPercentage"/><br/>
    <input type="submit" value="Calculate Tip"/>
</form>
</body>
</html>