public class App  {

    public static void main(String[] args) {

        UserIOConsoleImpl myIO = new UserIOConsoleImpl();

        myIO.readLong("Enter a number between 0 and 100", 0, 100);

    }
}
