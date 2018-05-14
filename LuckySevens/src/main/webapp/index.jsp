<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lucky Sevens</title>
</head>
<body>
<h1>Lucky Sevens</h1>
<p>
    Please enter the amount you want to bet:
</p>
<form method="post" action="LuckySevensServlet">
    <input type="text" name="amountToBet"/><br/>
    <input type="submit" value="Play!"/>
</form>
</body>
</html>
