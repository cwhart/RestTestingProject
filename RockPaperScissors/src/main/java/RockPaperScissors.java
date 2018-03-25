import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        int numRounds;
        String userSelection, stringNumRounds, computerSelection;
        String playAgain = "no";

        //Loop through do-while loop to repeat the game as long as the user wants to play again.
        do {

            //Initialize counters each time the game is played.
            int tiedRounds = 0;
            int userWins = 0;
            int computerWins = 0;

            //Prompt the user for # of rounds. Read in as a string and then convert to an int.
            System.out.println("Let's play Rock, Paper, Scissors!");
            System.out.println("How many rounds would you like to play? Enter a number between 1 and 10.");
            stringNumRounds = userInput.nextLine();
            numRounds = Integer.parseInt(stringNumRounds);

            //Generate an error if the user doesn't input a number between 1 and 10
            if (!(numRounds >= 1 && numRounds <= 10)) {
                System.out.println("Error! That is not a number between 1 and 10.");
                return;
            }

            //Loop for each round
            for (int i = numRounds; i > 0; i--) {

                String winner;

                //If user does not enter one of the 3 valid values, continue to prompt them.
                do {
                    System.out.println("Enter your move (rock/paper/scissors): ");
                    userSelection = userInput.nextLine().toLowerCase();
                }

                while (!(userSelection.equals("rock") || userSelection.equals("paper")
                        || userSelection.equals("scissors")));

                //Generate the computer's random guess
                computerSelection = computerGenerateGuess();

                //Now compare the computer's guess with the user's and increment the appropriate counter
                winner = compareGuesses(userSelection, computerSelection);
                if(winner.equals("tie")) tiedRounds++;
                else if(winner.equals("user")) userWins++;
                else computerWins++;
            }

            //End of the round - print out the results and ask the user if they want to play again
            System.out.println("Number of ties: " + tiedRounds);
            System.out.println("Number of user wins: " + userWins);
            System.out.println("Number of computer wins: " + computerWins);
            System.out.println("Would you like to play again?");
            playAgain = userInput.nextLine();

        }
        //if user wants to play again, loop back to the beginning
        while (playAgain.toLowerCase().equals("yes"));


    }

    //Generate a random number from 0 to 2, convert to rock, paper, scissors string and return

    public static String computerGenerateGuess() {
        Random rand = new Random();
        int guess = rand.nextInt(3);
        if(guess == 0) return "rock";
        else if(guess ==1) return "paper";
        else return "scissors";
    }

    //Compare computer's guess with user's and return results - tie, user, or computer wins.
    public static String compareGuesses(String userGuess, String computerGuess) {
        String winner;

        if(userGuess.equals(computerGuess)) {

            System.out.println("Computer guesses: " + computerGuess + ". Tie!");
            winner = "tie";
        }
        else if((userGuess.equals("rock") && computerGuess.equals("scissors")) || (userGuess.equals("paper")
                && computerGuess.equals("rock")) || (userGuess.equals("scissors") && computerGuess.equals("rock")))
        {
            System.out.println("Computer guesses: " + computerGuess + ". User wins!");
            winner = "user";
        }
        else {
            System.out.println("Computer guesses: " + computerGuess + ". Computer wins!");
            winner = "computer";
        }
        return winner;
    }

}
