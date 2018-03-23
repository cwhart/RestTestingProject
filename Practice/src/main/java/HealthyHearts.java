import java.util.Scanner;

public class HealthyHearts {

    public static void main(String[] args) {

        int age, max;
        double rangeMin, rangeMax;
        Scanner myInput = new Scanner(System.in);

        System.out.println("What is your age?");
        age = myInput.nextInt();
        max = 220 - age;
        rangeMin = Math.round(max * .5);
        rangeMax = Math.round(max * .85);

        System.out.println("Your maximum heart rate should be " + max + " beats per minute");
        System.out.println("Your target HR zone is " + rangeMin + " - " + rangeMax + " beats per minute");

    }
}
