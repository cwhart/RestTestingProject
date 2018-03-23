import java.util.Scanner;

public class VoterRegistration {
    //class level variable
    //the scope of this variable is the entire class
    //any method in the class can access it.
    private static Scanner scanner = new Scanner(System.in);
    private static final int ELIGIBLE_VOTER_AGE = 18;
    private static final String ELIGIBLE_VOTER_COUNTRY = "America";

    public static void main(String[] args) {

        //Prompt the user for their ageFromUser.
        int ageFromUser = promptUserForAge();
        String country = promptUserForCountry();

        if(isEligibleToRegister(ageFromUser, country)) {
            printMessage("You are registered to vote.");
        } else {
            printMessage("We could not register you to vote.");
        }
    }

    private static int promptUserForAge() {
        int userAge;
        boolean invalidNumber = false;
        do {
            printMessage("Please enter your age: ");
            String userAgeString = scanner.nextLine();
            userAge = Integer.parseInt(userAgeString);
            if(userAge <= 0) {
                invalidNumber = true;
            } else invalidNumber = false;
        } while (invalidNumber);
        return userAge;
    }

    private static String promptUserForCountry() {
        printMessage("Please enter the country you live in: ");
        String userCountry = scanner.nextLine();
        return userCountry;
    }

    private static boolean isEligibleToRegister(int age, String country) {
        if(age >=ELIGIBLE_VOTER_AGE && country.equals(ELIGIBLE_VOTER_COUNTRY)) {
            return true;
        }
        else return false;
    }

    private static void printMessage(String message) {
        System.out.println(message);
    }
}
