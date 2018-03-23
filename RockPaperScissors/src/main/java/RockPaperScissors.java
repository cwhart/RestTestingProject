import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        int numRounds, intUserSelection, intComputerSelection;
        String userSelection, stringNumRounds;
        String playAgain = "no";

        do {
            int tiedRounds = 0;
            int userWins = 0;
            int computerWins = 0;
            System.out.println("Let's play Rock, Paper, Scissors!");
            System.out.println("How many rounds would you like to play? Enter a number between 1 and 10.");
            stringNumRounds = userInput.nextLine();
            numRounds = Integer.parseInt(stringNumRounds);
            String winner;

            if (!(numRounds >= 1 && numRounds <= 10)) {
                System.out.println("Error! That is not a number between 1 and 10.");
                return;
            }

            for (int i = numRounds; i > 0; i--) {

                do {
                    System.out.println("Enter your move (rock/paper/scissors): ");
                    userSelection = userInput.nextLine();
                }

                while (!(userSelection.toLowerCase().equals("rock") || userSelection.toLowerCase().equals("paper")
                        || userSelection.toLowerCase().equals("scissors")));

                intUserSelection = convertSelectionToInt(userSelection);

                intComputerSelection = computerGenerateGuess();

                winner = compareGuesses(intUserSelection, intComputerSelection);
                if(winner.equals("tie")) tiedRounds++;
                else if(winner.equals("user")) userWins++;
                else computerWins++;
            }

            System.out.println("Number of ties: " + tiedRounds);
            System.out.println("Number of user wins: " + userWins);
            System.out.println("Number of computer wins: " + computerWins);
            System.out.println("Would you like to play again?");
            playAgain = userInput.nextLine();

        }
        while (playAgain.toLowerCase().equals("yes"));


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

    public static String compareGuesses(int userGuess, int computerGuess) {
        String winner;

        if(userGuess == computerGuess) {

            System.out.println("Computer guesses: " + convertIntToSelection(computerGuess) + ". Tie!");
            winner = "tie";
        }
        else if((userGuess==0 && computerGuess==2) || (userGuess==1 && computerGuess==0) || (userGuess==2 && computerGuess==1))
        {
            System.out.println("Computer guesses: " + convertIntToSelection(computerGuess) + ". User wins!");
            winner = "user";
        }
        else {
            System.out.println("Computer guesses: " + convertIntToSelection(computerGuess) + ". Computer wins!");
            winner = "computer";
        }
        return winner;
    }

    public static String convertIntToSelection (int num) {
        if(num==0) return "Rock";
        if(num==1) return "Paper";
        if(num==2) return "Scissors";
        else return "Error!";
    }
}
