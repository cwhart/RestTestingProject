import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter a day of the week.");
        String dayEntered = userInput.nextLine();

        DaysOfTheWeek enumDayEntered = DaysOfTheWeek.valueOf(dayEntered.toUpperCase());

        switch (enumDayEntered) {
            case SUNDAY:

                System.out.println("There are 5 days until Friday");
                break;
            case MONDAY:

                System.out.println("There are 4 days until Friday");
                break;
            case TUESDAY:

                System.out.println("There are 3 days until Friday");
                break;
            case WEDNESDAY:

                System.out.println("There are 2 days until Friday");
                break;
            case THURSDAY:

                System.out.println("There are 1 days until Friday");
                break;
            case FRIDAY:

                System.out.println("There are 0 days until Friday");
                break;
            case SATURDAY:

                System.out.println("There are 6 days until Friday");
                break;


        }


    }

    public enum DaysOfTheWeek {

        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;

         }
}
