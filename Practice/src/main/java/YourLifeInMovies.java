import java.util.Scanner;

public class YourLifeInMovies {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String name = "";
        int year = 0;

        System.out.println("Hey, let's play a game! What's your name?");
        name = input.nextLine();
        System.out.println("OK, " + name + ", when were you born?");
        year = input.nextInt();

        System.out.println("Well, " + name + "...");

        if (year < 2005 ) {
            System.out.println("Did you know that Pixar's 'Up' came out half a decade ago!");
        }

        if (year < 1995) {
            System.out.println("And the first Harry Potter movie came out over 15 years ago!");
        }

        if (year < 1985) {
            System.out.println("Also Space Jam came out not last decade, but the one before that!");
        }

        if (year < 1975) {
            System.out.println("Furthermore, did you know that the original Jurrasic Park release is closer to the lunar landing" +
                    " than today?");
        }

        if (year < 1965) {
            System.out.println("And, M*A*S*H has been around for almost half a century!");
        }

    }
}
