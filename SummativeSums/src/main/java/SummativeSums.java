import java.util.Arrays;

public class SummativeSums {

    public static void main(String[] args) {
        //Declare the arrays
        int[] array1 = new int[]{ 1, 90, -33, -55, 67, -16, 28, -55, 15 };
        int[] array2 = new int[]{ 999, -60, -77, 14, 160, 301 };
        int[] array3 = new int[]{ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130,
                140, 150, 160, 170, 180, 190, 200, -99 } ;

        //Add up the arrays and print out.

        System.out.println("#1 Array Sum: " + addItUp(array1));
        System.out.println("#2 Array Sum: " + addItUp(array2));
        System.out.println("#3 Array Sum: " + addItUp(array3));

    }

    public static int addItUp (int[] nums) {
        int sum = 0;

        //Loop through the elements of the array and add each one to sum.
        for (int thisNum : nums) {
            sum += thisNum;
        }
        return sum;
    }
}
