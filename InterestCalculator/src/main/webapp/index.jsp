<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UFT-8">
        <title>InterestCalculator</title>
    </head>
<body>
    <h1>Interest Calculator</h1>

    <form method="post" action="InterestCalculatorServlet">
        <p>
            Please enter the principal amount for the loan:
        </p>
        <input type="text" name="loanPrincipal"/><br/>
        <p>
            Please enter the interest rate:
        </p>
        <input type="text" name="loanInterestRate"/><br/>
        <p>
            Please enter the number of years:
        </p>
        <input type="text" name="loanYearsTerm"/><br/>
        <input type="submit" value="Calculate"/>
    </form>

</body>
</html>
