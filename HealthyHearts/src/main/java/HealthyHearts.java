import java.util.Scanner;

public class HealthyHearts {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        String strAge;
        int age;
        int maxHeartRate, targetMin, targetMax;

        System.out.println("What is your age? ");
        strAge = userInput.nextLine();
        age = Integer.parseInt(strAge);
        maxHeartRate = 220 - age;
        targetMax = (int) Math.round(maxHeartRate * .85);
        targetMin = (int) Math.round(maxHeartRate * .5);

        System.out.println("Your maximum heart rate should be " + maxHeartRate + " beats per minute.");
        System.out.println("Your target HR Zone is " + targetMin + " - " + targetMax + " beats per minute");
    }
}
