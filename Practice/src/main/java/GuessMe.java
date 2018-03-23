import java.util.Random;
import java.util.Scanner;

public class GuessMe {

    public static void main(String[] args) {

        Random rgen = new Random();
        int random = rgen.nextInt(21) - 10;
        Scanner input = new Scanner(System.in);
        int guess;

        do {
        System.out.println("Please enter an integer: ");
        guess = input.nextInt();


            if (random == guess) {
                System.out.println("Wow, nice guess! That was it!");
            } else if (guess < random) {
                System.out.println("Ha, nice try - too low! ");
            } else {
                System.out.println("Too bad, way too high. ");
            }

        }
            while (random != guess) ;


    }
}
