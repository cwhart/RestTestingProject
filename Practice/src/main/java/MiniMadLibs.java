import java.util.Scanner;

public class MiniMadLibs {

    public static void main(String[] args) {

        Scanner myInput = new Scanner(System.in);

        String one, two, three, four, five, six, seven, eight, nine, ten;

        System.out.println("Input a noun:");
        one = myInput.nextLine();
        System.out.println("Input an adjective: ");
        two = myInput.nextLine();
        System.out.println("Input another noun:");
        three = myInput.nextLine();
        System.out.println("Input a number: ");
        four = myInput.nextLine();
        System.out.println("Input an adjective: ");
        five = myInput.nextLine();
        System.out.println("Input a plural noun: ");
        six = myInput.nextLine();
        System.out.println("Input a plural noun: ");
        seven = myInput.nextLine();
        System.out.println("Input a plural noun: ");
        eight = myInput.nextLine();
        System.out.println("Input a present tense verb: ");
        nine = myInput.nextLine();
        System.out.println("Now input the same verb, past tense: ");
        ten = myInput.nextLine();

        System.out.print(one + ": the " + two + " frontier. These are the voyages of the starship ");
        System.out.print(three + ". Its " + four + "-year mission: to explore strange ");
        System.out.print(five + " " + six + ", to seek out " + five + " " + seven + " and ");
        System.out.print(five + " " + eight + ", to boldly " + nine + " where no one has ");
        System.out.print(ten + " before.");


    }
}
