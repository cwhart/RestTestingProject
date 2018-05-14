package com.sg.interestCalculatorservlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class InterestCalculatorController {
    @RequestMapping(value = "/calculateInterest",
            method = RequestMethod.POST)
    public String factorNumber(HttpServletRequest request, Map<String, Object> model) {


        String principalStr = request.getParameter("loanPrincipal");
        String interestStr = request.getParameter("interestRate");
        String numYearsStr = request.getParameter("numYears");

        double interestRate = Double.parseDouble(interestStr);
        double numYears = Double.parseDouble(numYearsStr);
        double startingBalance = Double.parseDouble(principalStr);
        double runningPrincipal = startingBalance;

        List<String> listOfYearInfo = new ArrayList<>();
        String currentStr = "";


        for (int i = 1; i < +numYears; i++) {
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

        model.put("output", listOfYearInfo);
        return "result";

    }

}


