import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Practice {

    public static void main(String[] args)throws Exception {

        PrintWriter out = new PrintWriter(new FileWriter("Outfile.txt"));
        out.println("this is a line in my file...");
        out.println("a second line in my file...");
        out.println("a third line in my file...");
        out.flush();
        out.close();

        Scanner sc = new Scanner(
            new BufferedReader(new FileReader("OutFile.txt")));

    while (sc.hasNextLine()) {
        String currentLine = sc.nextLine();
        System.out.println(currentLine);
    }

        System.out.println("That's all the content in the file.");



    }
}
