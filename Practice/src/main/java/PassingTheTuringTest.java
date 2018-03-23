import java.util.Scanner;

public class PassingTheTuringTest {

    public static void main(String[] args) {

        String name, color, fruit;
        int number;

        Scanner myInput = new Scanner(System.in);

        System.out.println("Hello there!");
        System.out.println("What's your name? ");
        name = myInput.nextLine();

        System.out.print("Hi, " + name + "! ");
        System.out.println("What's your favorite color?");
        color = myInput.nextLine();

        System.out.println("Huh, " + color + "? Mine's electric lime.");
        System.out.println("I really like limes. They're my favorite fruit, too.");
        System.out.println("What's YOUR favorite fruit, " + name + "?");
        fruit = myInput.nextLine();

        System.out.println("Really? " + fruit + "? That's wild!");
        System.out.println("Speaking of favorites, what's your favorite number?");
        number = myInput.nextInt();

        System.out.println(number + " is a cool number. Mine's -7.");
        System.out.println("Did you know 42 * -7 is -294? That's a cool number too!");
        System.out.println("Well, thanks for talking to me, " + name + "!");
    }
}
