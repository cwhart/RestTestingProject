import java.util.Random;

public class BarelyControlledChaos {

    public static void main(String[] args) {

        String color = chooseColor(); // call color method here
        String animal = chooseAnimal(); // call animal method again here
        String colorAgain = chooseColor(); // call color method again here
        int weight = chooseNum(5, 200); // call number method,
        // with a range between 5 - 200
        int distance = chooseNum(10, 20); // call number method,
        // with a range between 10 - 20
        int number = chooseNum(10000, 20000); // call number method,
        // with a range between 10000 - 20000
        int time = chooseNum(2, 6); // call number method,
        // with a range between 2 - 6

        System.out.println("Once, when I was very small...");

        System.out.println("I was chased by a " + color + ", " + weight + "lb " + " miniature " + animal
                + " for over " + distance + " miles!!");

        System.out.println("I had to hide in a field of over "
                + number + " " + colorAgain + " poppies for nearly "
                + time + " hours until it left me alone!");

        System.out.println("\nIt was QUITE the experience, "
                + "let me tell you!");
    }

    public static String chooseColor() {
        Random rand = new Random();
        int color = rand.nextInt(6);

        switch (color) {
            case 1:
                return "red";

            case 2:
                return "blue";

            case 3:
                return "green";

            case 4:
                return "purple";

            default: return "yellow";


        }
    }

    public static String chooseAnimal() {
        Random rand = new Random();
        int animal = rand.nextInt(6);

        switch (animal) {
            case 1:
                return "dog";

            case 2:
                return "cat";

            case 3:
                return "parrot";

            case 4:
                return "raccoon";

            default:
                return "bear";


        }

    }

    public static int chooseNum(int min, int max) {
        Random rand = new Random();
        int num = (rand.nextInt(max-min + 1) + min);
        return num;
    }

}



