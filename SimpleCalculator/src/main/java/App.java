import java.util.Scanner;


public class App {

    private static UserIOConsoleImpl myInput = new UserIOConsoleImpl();

    private static double operand1;
    private static double operand2;

    public static void main(String[] args) {

        String operation;

        do {

            displayMenu();

            operation = myInput.readString("");

            if (operation.equals("5")) {
                myInput.print("Thank you for using SimpleCalculator!");
                return;
            } else {

                promptForOperands();

                switch (operation) {
                    case "1":
                        myInput.print(operand1 + " + " + operand2 + " = " + SimpleCalculator.add(operand1, operand2));
                        break;
                    case "2":
                        myInput.print(operand1 + " - " + operand2 + " = " + SimpleCalculator.subtract(operand1, operand2));
                        break;
                    case "3":
                        myInput.print(operand1 + " * " + operand2 + " = " + SimpleCalculator.multiply(operand1, operand2));
                        break;
                    case "4":
                        myInput.print(operand1 + " / " + operand2 + " = " + SimpleCalculator.divide(operand1, operand2));
                        break;
                    default:
                        break;
                }

            }

        } while (!operation.equals("5"));

    }

    private static void displayMenu() {
        myInput.print("Welcome to Simple Calculator!");
        myInput.print("Please choose your operation: ");
        myInput.print("1 - Add");
        myInput.print("2 - Subtract");
        myInput.print("3 - Multiply");
        myInput.print("4 - Divide");
        myInput.print("5 - Exit");
    }

    private static void promptForOperands() {
        operand1 = myInput.readDouble("Please enter your first operand:");
        operand2 = myInput.readDouble("Please enter your second operand:");

    }


}
