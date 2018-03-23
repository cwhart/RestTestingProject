import java.lang.reflect.Array;
import java.util.Arrays;

public class SimpleSort {

    public static void main(String[] args) {
        int[] firstHalf = {3, 7, 9, 10, 16, 19, 20, 34, 55, 67, 88, 99};
        int[] secondHalf = {1, 4, 8, 11, 15, 18, 21, 44, 54, 79, 89, 100};

        int[] wholeNumbers = new int[24];
        int counter = 0;

        for (int num : firstHalf) {
            wholeNumbers[counter] = num;
            counter++;
        }

        for (int num : secondHalf) {
            wholeNumbers[counter] = num;
            counter++;
        }

        Arrays.sort(wholeNumbers);

        System.out.println("Here ya go = all nice and ordered:");
        System.out.println(Arrays.toString(wholeNumbers));


        }

}
