import java.util.Random;

public class LazyTeenager {

    public static void main(String[] args) {

        Boolean clean = false;
        int percentChance = 5;
        int counter = 1;
        int num;

        while (!clean) {
            System.out.println("Clean your room! x" + counter);

            Random ran = new Random();
            num = ran.nextInt(100);

            if(num <= percentChance) {
                System.out.println("FINE! I'LL CLEAN MY ROOM BUT I REFUSE TO EAT MY PEAS.");
                System.out.println(percentChance);
                clean = true;
            } else {
                percentChance += 5;
                counter++;
                if (counter >=15) {
                    System.out.println("YOU'RE GROUNDED AND I'M TAKING YOUR XBOX!");
                    break;
                }
            }

        }

    }
}
