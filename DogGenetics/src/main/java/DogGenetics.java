import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class DogGenetics {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String dogsName = "";

        //List of dog breeds to randomly select from
        String[] dogBreedsToSelect = new String[]{"Chihuahua", "Schnauzer", "Coonhound", "Poodle", "Daschund", "German Shepherd",
        "Labrador Retriever", "Rottweiler", "Golden Retriever", "Beagle", "Bulldog", "Husky", "Boxer", "Pug", "Border Collie",
        "Shih Tzu", "Pit Bull", "Sheepdog", "Basset Hound", "Corgi"};

        //Arrays to store my selections
        String[] dogBreeds = new String[5];
        int[] percentages = new int[5];

        //ints for tracking my random percentages
        int runningTally = 95;
        int sum = 0;
        Random rand = new Random();

        //Prompt the user for their dog's name
        System.out.println("What is your dog's name? ");
        dogsName = userInput.nextLine();
        System.out.println("Well then, I have this highly reliable report on " + dogsName +
                "'s prestigious background right here.");

        //Loop through the 20 breeds and choose 5 at random
        for(int j=0; j<5; j++) {
            int thisRandom;

            //Generate a random index for selecting the breed from the array.
            //Once selected, set it to mull so it doesn't get selected again.
            //And before selecting, check to ensure it's not equal to mull.
            do {
                thisRandom = rand.nextInt(20);
            } while (dogBreedsToSelect[thisRandom] == null);

                dogBreeds[j] = dogBreedsToSelect[thisRandom];
                dogBreedsToSelect[thisRandom] = null;

        }

        //Loop through and generate 4 random percentages. Upper bound for the first percentage is 95.
        //Each time, decrement the upper bound as each percentage is generated.
        for(int i=0; i<4; i++) {

            int thisPercentage = rand.nextInt(runningTally) + 1;
            runningTally = runningTally + 1 - thisPercentage;
            sum = sum + thisPercentage;
            percentages[i] = thisPercentage;

            System.out.println(dogsName + " is " + percentages[i] + " percent " + dogBreeds[i] + ".");
        }

        //The final percentage must be 100 minus the sum of the 4 previous, to ensure it all adds up to 100.
        percentages[4] = 100 - sum;
        System.out.println(dogsName + " is " + percentages[4] + " percent " + dogBreeds[4] + ".");


    }
}
