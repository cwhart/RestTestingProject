import java.util.Random;
import java.util.Scanner;

public class PracticeLoops {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int userNumber;
        String userNumberString;

        do {
            System.out.println("Please enter a number between 1 and 20:");
            userNumberString = sc.nextLine();
            userNumber = Integer.parseInt(userNumberString);

        } while (userNumber < 1 || userNumber > 20);

        System.out.println("Thank you!!! Your number was: " + userNumber);




    }
}
