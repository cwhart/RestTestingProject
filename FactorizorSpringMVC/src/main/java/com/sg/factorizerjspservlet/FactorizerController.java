package com.sg.factorizerjspservlet;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class FactorizerController {

    @RequestMapping(value="/factorNumber", method=RequestMethod.POST)
    public String factorNumber(HttpServletRequest request, Map<String, Object> model) {

        //A list to hold our factors
        List<Integer> factorList = new ArrayList<>();

        //A sum to help us calculate whether or not the number is perfect
        int factorSum = 0;
        boolean isPrime = false;
        boolean isPerfect = false;

        //Get the input from the user and convert it to an int
        String input = request.getParameter("numberToFactor");
        int numberToFactor = Integer.parseInt(input);

        for(int i=1; i<numberToFactor; i++) {
            if (numberToFactor % i == 0) {
                factorList.add(i);
                factorSum += i;
            }
        }

        if (factorSum == numberToFactor ) {
            isPerfect = true;
        }

        if (factorSum == 1) {
            isPrime = true;
        }

        model.put("numberToFactor", numberToFactor);
        model.put("factors", factorList);
        model.put("isPrime", isPrime);
        model.put("isPerfect", isPerfect);
        return "result";
    }

}
