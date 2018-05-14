<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FlooringCalculator</title>
    </head>

<body>
<h1>Flooring Calculator</h1>
    <p>
        Enter the width of the flooring area:
    </p>

<form method="post" action="FlooringCalculatorServlet">
    <input type="text" name="floorWidth"/><br/>
    <p>
        Enter the length of the flooring area:
    </p>
    <input type="text" name="floorLength"/><br/>
    <p>
        Enter the cost per square foot:
    </p>
    <input type="text" name="costPerSquareFoot"/><br/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
