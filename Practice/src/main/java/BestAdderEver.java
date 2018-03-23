import java.util.Scanner;

public class BestAdderEver {

    public static void main(String[] args) {

        String name, favoriteFood;
        int pets, whistleAge;
        boolean gnocci;
        Scanner myInput = new Scanner(System.in);

        System.out.println("What is your name? ");
        name = myInput.nextLine();

        System.out.println("What is your favorite food? ");
        favoriteFood = myInput.nextLine();

        System.out.println("How many pets do you have?");
        pets = myInput.nextInt();

        System.out.println("How old were you when you learned to whistle? ");
        whistleAge = myInput.nextInt();

        System.out.println("You have eaten gnocci before - true/false: ");
        gnocci = myInput.nextBoolean();

        System.out.println("My name is: " + name);
        System.out.println("My favorite food is: " + favoriteFood);
        System.out.println("I have " + pets + " pets.");
        System.out.println("I learned to whistle when I was " + whistleAge + " years old.");
        System.out.println("It is " + gnocci + " that I have eaten gnocci");


    }
}
