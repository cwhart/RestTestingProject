import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

public class InterestCalculator {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);


        BigDecimal startingInvestment;
        BigDecimal interestRate;
        int numYears;
        BigDecimal runningTotal;
        //int thisYear;
        BigDecimal intEarned;
        BigDecimal period;
        //String holder;
        //DecimalFormat df = new DecimalFormat("#.00");

        System.out.println("How much do you want to invest?");
        startingInvestment = new BigDecimal(userInput.nextLine());
        runningTotal = startingInvestment;
        System.out.println("What is the interest rate?");
        interestRate = new BigDecimal(userInput.nextLine());
        System.out.println("How many years do you want to invest for?");
        numYears = Integer.parseInt(userInput.nextLine());
        System.out.println("How many times a year do you want your investment compounded?" +
                "(1-365):");
        period = new BigDecimal(userInput.nextLine());

        for (int i=1; i<=numYears; i++) {
            System.out.println("Year number: " + i);
            System.out.println("Principal at start of year: $" + startingInvestment.setScale(2, RoundingMode.HALF_UP));

            for (int j=0; j<period.intValue(); j++) {


                BigDecimal temp = (interestRate.divide(period.multiply(BigDecimal.valueOf(100))).add(BigDecimal.valueOf(1)));
                runningTotal = runningTotal.multiply(temp);

                //runningTotal = runningTotal * (interestRate/(period *100) + 1);

            }

            System.out.println("Interest earned: $" +
                    (runningTotal.subtract(startingInvestment)).setScale(2, RoundingMode.HALF_UP));
            System.out.println("Ending principal: $" + runningTotal.setScale(2, RoundingMode.HALF_UP));

            startingInvestment = runningTotal;
        }
    }



}
