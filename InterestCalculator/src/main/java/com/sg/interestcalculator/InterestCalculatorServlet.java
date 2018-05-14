package com.sg.interestcalculator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "InterestCalculatorServlet")
public class InterestCalculatorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String principalStr = request.getParameter("loanPrincipal");
        String interestStr = request.getParameter("loanInterestRate");
        String numYearsStr = request.getParameter("loanYearsTerm");

        double interestRate = Double.parseDouble(interestStr);
        double numYears = Double.parseDouble(numYearsStr);
        double startingBalance = Double.parseDouble(principalStr);
        double runningPrincipal = startingBalance;

        List<String> listOfYearInfo = new ArrayList<>();
        String currentStr = "";


        for (int i=1; i<+numYears; i++) {
            currentStr = "";
            currentStr = "Year: " + i;
            currentStr += " | Starting Balance: " + runningPrincipal;

            double interestEarned = 0;

            for (int j = 0; j < 4; j++) {
                interestEarned = runningPrincipal * ((interestRate * .01) / 4);
                runningPrincipal = runningPrincipal + interestEarned;
            }

            currentStr += " | Interest earned: " + interestEarned;
            currentStr += " | Ending Balance: " + runningPrincipal;

            listOfYearInfo.add(currentStr);
        }

        request.setAttribute("year", listOfYearInfo);


        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);

    }
}
