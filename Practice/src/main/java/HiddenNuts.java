import java.util.Random;

public class HiddenNuts {

    public static void main(String[] args) {

        String[] hidingSpots = new String[100];
        Random squirrel = new Random();
        hidingSpots[squirrel.nextInt(hidingSpots.length)] = "Nut";

        System.out.println("The nut has been hidden...");

        for (int i=0; i< hidingSpots.length; i++) {
            if (hidingSpots[i] != (null)) {
                if(hidingSpots[i].equals("Nut"))
                System.out.println("Found it! It's in spot " + i);
            }
        }
    }
}
