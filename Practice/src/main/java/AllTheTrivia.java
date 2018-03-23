import java.util.Scanner;

public class AllTheTrivia {

    public static void main(String[] args) {
        Scanner myInput = new Scanner(System.in);

        String input1, input2, input3, input4;

        System.out.println("1024 Gigabytes is equal to one what?");
        input1 = myInput.nextLine();

        System.out.println("In our solar system which is the only planet that rotates clockwise?");
        input2 = myInput.nextLine();

        System.out.println("The largest volcano ever discovered in our solar system is located on which planet?");
        input3 = myInput.nextLine();

        System.out.println("What is the most abundant element in the earth's atmosphere?");
        input4 = myInput.nextLine();

        System.out.println("Wow, 1024 Gigabytes is a " + input2 + "!");
        System.out.println("I didn't know that the largest ever volcano was discovered on " + input4 + "!");
        System.out.println("That's amazing that " + input3 + " is the most abundant element in the atmosphere...");
        System.out.println(input1 + " is the only planet that rotates clockwise, neat!");
    }
}
