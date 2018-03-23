import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class DogGenetics {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String dogsName;

        String[] dogBreeds = new String[]{"Chihuahua", "Schnauzer", "Coonhound", "Poodle", "Daschund"};
        int[] percentages = new int[5];
        int runningTally = 100;
        Random rand = new Random();

        System.out.println("What is your dog's name? ");
        dogsName = userInput.nextLine();
        System.out.println("Well then, I have this highly reliable report on " + dogsName +
                "'s prestigious background right here.");

        for(int i=0; i<4; i++) {

                int thisPercentage = (rand.nextInt(runningTally) + 1);
                runningTally = runningTally - thisPercentage;
                percentages[i] = thisPercentage;

            System.out.println(dogsName + " is " + percentages[i] + " percent " + dogBreeds[i]);
        }

        //In order to total to 100, the final percentage cannot be random, it must equal the remainder.
        percentages[4] = runningTally;
        System.out.println(dogsName + " is " + percentages[4] + " percent " + dogBreeds[4]);


    }
}
