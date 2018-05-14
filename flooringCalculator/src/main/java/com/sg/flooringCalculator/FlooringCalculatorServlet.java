package com.sg.flooringCalculator;

import sun.misc.Request;

import javax.servlet.RequestDispatcher;
import java.io.IOException;

public class FlooringCalculatorServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        // Get the value that was submitted in the index.jsp form
        String flooringWidth = request.getParameter("floorWidth");
        String flooringLength = request.getParameter("floorLength");
        String flooringCostPerSqFt = request.getParameter("costPerSquareFoot");

        double width = Double.parseDouble(flooringWidth);
        double length = Double.parseDouble(flooringLength);
        double costPerSqFt = Double.parseDouble(flooringCostPerSqFt);

        double materialCost = width * length * costPerSqFt;
        double totalTime = (width * length)/5;
        double laborCost = totalTime * 21.5;
        double totalCost = materialCost + laborCost;


        // Set numberToFactor as an attribute on the request so that
        // it is available to result.jsp
        request.setAttribute("materialCost", materialCost);
        request.setAttribute("totalTime", totalTime);
        request.setAttribute("laborCost", laborCost);
        request.setAttribute("totalCost", totalCost);

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
