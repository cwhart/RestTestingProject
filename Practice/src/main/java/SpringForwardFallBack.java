public class SpringForwardFallBack {

    public static void main(String[] args) {

        System.out.println("It's Spring...!");

        for (int i=0; i<10; i++) {  //Start/stop range from 0 to 9
            System.out.println(i + ", ");
        }

        System.out.println("\nOh no, it's fall...");

        for (int i=10; i>0; i--) { //Start/stop range from 10 to 1
            System.out.println(i + ", ");
        }
    }
}
