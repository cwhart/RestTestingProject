import java.lang.reflect.Array;
import java.util.*;

public class App {

    static Map<String, ArrayList> studentScores = new HashMap<>();
    static UserIOConsoleImpl myIO = new UserIOConsoleImpl();
    static Set<String> keys = studentScores.keySet();

    public static void main(String[] args) {

        int userSelection;

        do {
            userSelection = myIO.readInt("Please select an option: " +
                    "\n(1) View list of students" +
                    "\n(2) Add a student" +
                    "\n(3) Remove a student" +
                    "\n(4) View list of scores for a student" +
                    "\n(5) View average score for a student" +
                    "\n(6) Exit");

            switch (userSelection) {
                case 1:
                    printListOfStudents();
                    myIO.print("\n");
                    break;
                case 2:
                    addStudent();
                    printListOfStudents();
                    myIO.print("\n");
                    break;
                case 3:
                    removeStudent();
                    printListOfStudents();
                    myIO.print("\n");
                    break;
                case 4:
                    printScores();
                    myIO.print("\n");
                    break;
                case 5:
                    averageScores();
                    myIO.print("\n");
                    break;
                case 6:
                    System.out.println("Good-bye");
                    break;
            }
        } while (userSelection >=1 && userSelection <= 5);


    }

    private static void printListOfStudents() {
        System.out.println("List of current students: ");
        for (String student : keys) {
            System.out.println(student);
        }
    }

    private static String inputName() {
        return myIO.readString("Please enter student's Lastname, Firstname: ");
    }

    private static void addStudent() {
        int addAnother;

        do {

            studentScores.put(inputName(), addScores());
            addAnother = myIO.readInt("Add another student? \n(0) - no\n(1) - yes");
        } while(addAnother == 1);
    }

    private static ArrayList<Integer> addScores() {
        ArrayList<Integer> scores = new ArrayList<>();
        int keepGoing;

        do {
            scores.add(myIO.readInt("Enter score:"));
            keepGoing = myIO.readInt("More? \n(0) - no\n(1) - yes");

        } while (keepGoing == 1);

        return scores;

    }

    private static void removeStudent() {
        int removeAnother;

        do {
            studentScores.remove(inputName());
            removeAnother = myIO.readInt("Remove another student?\n(0) - no\n(1) - yes");
        } while (removeAnother == 1);
    }

    private static void printScores() {
        int printAnother;

        do {
            List<Integer> scores = new ArrayList<>();
            scores = studentScores.get(inputName());

            myIO.print("List of scores for student is: ");

            for (Integer currentScore : scores) {
                myIO.print(Integer.toString(currentScore));
            }
            printAnother = myIO.readInt("Print scores for another student?\n(0) - no\n(1) - yes");
        } while (printAnother == 1);
    }

    private static void averageScores() {
        int averageAnother;
        int sum;

        do {
            sum=0;
            List<Integer> scores = new ArrayList<>();
            scores = studentScores.get(inputName());

            myIO.print("Average score for student is: ");

            for (Integer currentScore : scores) {
                sum = sum + currentScore;
            }

            myIO.print (Integer.toString(sum / scores.size()));

            averageAnother = myIO.readInt("Average scores for another student?\n(0) - no\n(1) - yes");
        } while (averageAnother == 1);

    }
}
