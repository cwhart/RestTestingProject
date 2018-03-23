import java.util.Random;
import java.util.Scanner;

public class StayPositive {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int num;
        int counter = 0;

        System.out.println("Please enter an integer between 0 and 100:");
        num = scan.nextInt();

        while (num >= 0) {
            System.out.print(num + " ");
            num--;
            counter++;
            if (counter%10==0) System.out.println();
        }

        System.out.println("Whew, better stop there!");
    }
}
