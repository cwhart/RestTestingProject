package com.sg.tipCalculator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TipCalculatorServlet")
public class TipCalculatorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String totalBillStr = request.getParameter("billTotal");
        String tipPercentageStr = request.getParameter("tipPercentage");

        double totalFood = Double.parseDouble(totalBillStr);
        double tipPercentage = Double.parseDouble(tipPercentageStr);

        double tipAmount = totalFood * (tipPercentage * .01);
        double totalBill = totalFood + tipAmount;

        request.setAttribute("totalFood", totalFood);
        request.setAttribute("tipPercentage", tipPercentage);
        request.setAttribute("tipAmount", tipAmount);
        request.setAttribute("totalBill", totalBill);

        // Get the Request Dispatcher for result.jsp and forward the
        // request to result.jsp
        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }
}
