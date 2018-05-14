package com.sg.luckysevens;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.Random;

public class LuckySevensServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {

        String amountToBetStr = request.getParameter("amountToBet");
        int amountToBet = Integer.parseInt(amountToBetStr);
        int runningTotal = amountToBet;
        int maximumWon = amountToBet;

        Random rand = new Random();
        int totalNumberOfRolls = 0;
        int totalRollsAtMax = 0;

        do {
            int first = rand.nextInt(6)+1;
            int second = rand.nextInt(6) + 1;
            totalNumberOfRolls += 1;
            if (first + second ==7) {
                runningTotal = runningTotal + 4;
                if(runningTotal > maximumWon ) {
                    maximumWon = runningTotal;
                    totalRollsAtMax = totalNumberOfRolls;
                }
            } else {
                runningTotal = runningTotal - 1;

            }


        } while (runningTotal > 0);

        request.setAttribute("amountBet", amountToBet);
        request.setAttribute("totalRolls", totalNumberOfRolls);
        request.setAttribute("totalRollsAtMax", totalRollsAtMax);
        request.setAttribute("maxWon", maximumWon);

        // Get the Request Dispatcher for result.jsp and forward the
        // request to result.jsp
        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }
}
