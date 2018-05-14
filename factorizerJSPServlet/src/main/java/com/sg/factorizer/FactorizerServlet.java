package com.sg.factorizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FactorizerServlet")
public class FactorizerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //A list to hold factors
        List<Integer> factorList = new ArrayList<>();

        //A sum to help us calculate whether or not the number is perfect

        int factorSum = 0;
        boolean isPrime = false;
        boolean isPerfect = false;

        //Get the input from the user and convert it to an int
        String input = request.getParameter("numberToFactor");
        int numberToFactor = Integer.parseInt(input);

        //Factor the number
        for (int i=1; i<numberToFactor; i++) {
            if (numberToFactor % i ==0) {
                factorList.add(i);
                factorSum += i;
            }
        }

        if(factorSum == numberToFactor) {
            isPerfect = true;
        }

        if(factorSum==1) {
            isPrime = true;
        }

        //Set all the results as attributes on the request so they are available to result.jsp
        request.setAttribute("numberToFactor", numberToFactor);
        request.setAttribute("factors", factorList);
        request.setAttribute("isPrime", isPrime);
        request.setAttribute("isPerfect", isPerfect);

        //Get the Request Dispatcher for result.jsp and forward the request to result.jsp
        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);

    }
}
