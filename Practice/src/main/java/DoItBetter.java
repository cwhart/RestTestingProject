import java.util.Scanner;

public class DoItBetter {

    public static void main(String[] args) {

        Scanner myInput = new Scanner(System.in);

        int miles, hotdogs, languages;

        System.out.println("How many miles can you run? ");
        miles = myInput.nextInt();
        System.out.println("I can run " + (miles*2+1) +" miles.");

        System.out.println("How many hot dogs can you eat? ");
        hotdogs = myInput.nextInt();
        System.out.println("I can eat " + (hotdogs*2+1) + " hot dogs.");

        System.out.println("How many languages do you speak? ");
        languages = myInput.nextInt();
        System.out.println("I can speak " + (languages*2+1) + " languages.");


    }
}
