public class Practice {

    public static void main(String[] args) {

        if (isOldEnoughToVote() && isAmericanCitizen()) {
            System.out.println("You can vote.");
        } else {
            System.out.println("nope");
        }

    }

    //access modifier + return type + method name + parameter list = method declaraion
    private static boolean isOldEnoughToVote() {
        return true;

    }

    private static boolean isAmericanCitizen() {
        return false;
    }

}
