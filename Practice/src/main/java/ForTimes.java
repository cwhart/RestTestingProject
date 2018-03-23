import java.util.Scanner;

public class ForTimes {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int mult = 0;

        System.out.println("Please enter a number: ");
        mult = scan.nextInt();

        for (int i=1; i<=15; i++) {
            System.out.println(mult + " * " + i + " is: " + (mult*i));
        }
    }
}
