import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    public static int tiedRounds, userWins, computerWins;

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        int numRounds, intUserSelection, intComputerSelection;
        String userSelection, stringNumRounds;


        System.out.println("Let's play Rock, Paper, Scissors!");
        System.out.println("How many rounds would you like to play?");
        stringNumRounds = userInput.nextLine();
        numRounds = Integer.parseInt(stringNumRounds);

        for(int i=numRounds; i>0; i--) {
            System.out.println("Enter your move (rock/paper/scissors): ");
            userSelection = userInput.nextLine();
            intUserSelection = convertSelectionToInt(userSelection);
            intComputerSelection = computerGenerateGuess();

            compareGuesses(intUserSelection, intComputerSelection);
        }

        System.out.println("Number of ties: " + tiedRounds);
        System.out.println("Number of user wins: " + userWins);
        System.out.println("Number of computer wins: " + computerWins);


    }

    public static int convertSelectionToInt (String input) {
        int returnValue;

        if(input.toLowerCase().equals("rock")) returnValue = 0;
        else if (input.toLowerCase().equals("paper")) returnValue = 1;
        else if (input.toLowerCase().equals("scissors")) returnValue = 2;
        else returnValue = 3;

        return returnValue;
    }

    public static int computerGenerateGuess() {
        Random rand = new Random();
        return rand.nextInt(3);
    }

    public static void compareGuesses(int userGuess, int computerGuess) {


        if(userGuess == computerGuess) {
            System.out.println("Tie!");
            tiedRounds++;
        }
        else if((userGuess==0 && computerGuess==2) || (userGuess==1 && computerGuess==0) || (userGuess==2 && computerGuess==1))
        {
            System.out.println("Computer guesses: " + convertIntToSelection(computerGuess) + ". User wins!");
            userWins++;
        }
        else {
            System.out.println("Computer guesses: " + convertIntToSelection(computerGuess) + ". Computer wins!");
            computerWins++;
        }



    }

    public static String convertIntToSelection (int num) {
        if(num==0) return "Rock";
        if(num==1) return "Paper";
        if(num==2) return "Scissors";
        else return "Error!";
    }
}
