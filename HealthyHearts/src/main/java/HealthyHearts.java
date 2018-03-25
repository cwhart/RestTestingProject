import java.util.Scanner;

public class HealthyHearts {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        String strAge;
        int age;
        int maxHeartRate, targetMin, targetMax;

        //Prompt the user for their age
        System.out.println("What is your age? ");

        //Read in user input and then convert to an int.

        strAge = userInput.nextLine();
        age = Integer.parseInt(strAge);

        //Calculate max HR and target range
        maxHeartRate = 220 - age;
        targetMax = (int) Math.round(maxHeartRate * .85);
        targetMin = (int) Math.round(maxHeartRate * .5);

        //Print out results
        System.out.println("Your maximum heart rate should be " + maxHeartRate + " beats per minute.");
        System.out.println("Your target HR Zone is " + targetMin + " - " + targetMax + " beats per minute.");
    }
}
