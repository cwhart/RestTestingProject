import java.util.Scanner;

public class TraditionalFizzBuzz {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int num;
        int counter = 0;

        System.out.println("Please enter a number: ");
        num = sc.nextInt();

        while (counter < num) {
            for(int i=0; i<=num*2; i++) {
                if (i!=0 && i%15==0) {
                    System.out.println("fizz buzz");
                    counter +=2;
                } else if (i!=0 && i%3==0) {
                    System.out.println("fizz");
                    counter++;
                } else if (i!=0 && i%5==0) {
                    System.out.println("buzz");
                    counter++;
                } else {
                    System.out.println(i);
                }
            }

        }



        System.out.println("TRADITION!!");
    }
}
