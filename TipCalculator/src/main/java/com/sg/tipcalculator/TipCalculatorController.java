package com.sg.tipcalculator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class TipCalculatorController {

    @RequestMapping(value="/calculateTip", method=RequestMethod.POST)
    public String calculateTip(HttpServletRequest request, Map<String, Object> model) {

        String totalBillStr = request.getParameter("foodTotal");
        String tipPercentageStr = request.getParameter("tipPercentage");

        double totalFood = Double.parseDouble(totalBillStr);
        double tipPercentage = Double.parseDouble(tipPercentageStr);

        double tipAmount = totalFood * (tipPercentage * .01);
        double totalBill = totalFood + tipAmount;

        request.setAttribute("totalFood", totalFood);
        request.setAttribute("tipPercentage", tipPercentage);
        request.setAttribute("tipAmount", tipAmount);
        request.setAttribute("totalBill", totalBill);

        return "result";
    }
}
