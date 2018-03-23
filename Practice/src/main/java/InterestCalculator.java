import java.text.DecimalFormat;
import java.util.Scanner;

public class InterestCalculator {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);


        double startingInvestment;
        double interestRate;
        int numYears;
        double runningTotal;
        int thisYear;
        double intEarned;
        int period = 1;
        DecimalFormat df = new DecimalFormat("#.00");

        System.out.println("How much do you want to invest?");
        startingInvestment = userInput.nextDouble();
        runningTotal = startingInvestment;
        System.out.println("What is the interest rate?");
        interestRate = userInput.nextDouble();
        System.out.println("How many years do you want to invest for?");
        numYears = userInput.nextInt();
        System.out.println("How many times a year do you want your investment compounded?" +
                "(1-365):");
        period = userInput.nextInt();

        for (int i=1; i<=numYears; i++) {
            System.out.println("Year number: " + i);
            System.out.println("Principal at start of year: $" + df.format(startingInvestment));

            for (int j=0; j<period; j++) {

                runningTotal = runningTotal * (interestRate/(period *100) + 1);

            }

            System.out.println("Interest earned: $" + df.format(runningTotal - startingInvestment));
            System.out.println("Ending principal: $" + df.format(runningTotal));

            startingInvestment = runningTotal;
        }
    }



}
