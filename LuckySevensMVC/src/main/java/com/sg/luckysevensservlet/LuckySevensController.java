package com.sg.luckysevensservlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Random;

@Controller
public class LuckySevensController {

    @RequestMapping(value="/playLuckySevens", method=RequestMethod.POST)
    public String factorNumber(HttpServletRequest request, Map<String, Object> model) {

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

        model.put("amountBet", amountToBet);
        model.put("totalRolls", totalNumberOfRolls);
        model.put("totalRollsAtMax", totalRollsAtMax);
        model.put("maxWon", maximumWon);

        return "result";
    }
}
