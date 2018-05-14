package com.sg.flooringcalculatorMVC;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class FlooringCalculatorController {

    @RequestMapping(value="/calculateFlooring",
            method=RequestMethod.POST)
    public String calculateFlooring(HttpServletRequest request, Map<String, Object> model) {

        String flooringWidth = request.getParameter("floorWidth");
        String flooringLength = request.getParameter("floorLength");
        String flooringCostPerSqFt = request.getParameter("flooringCostPerSqFt");

        double width = Double.parseDouble(flooringWidth);
        double length = Double.parseDouble(flooringLength);
        double costPerSqFt = Double.parseDouble(flooringCostPerSqFt);

        double materialCost = width * length * costPerSqFt;
        double totalTime = (width * length)/5;
        double laborCost = totalTime * 21.5;
        double totalCost = materialCost + laborCost;

        model.put("materialCost", materialCost);
        model.put("totalTime", totalTime);
        model.put("laborCost", laborCost);
        model.put("totalCost", totalCost);


        return "result";
    }
}
