public class FruitsBasket {

    public static void main(String[] args) {

        int numApples = 0;
        int numOranges = 0;

        String[] fruit = {"Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange",
                "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Apple",
                "Orange", "Apple", "Apple", "Orange", "Orange", "Apple", "Apple", "Apple", "Apple", "Orange", "Orange",
                "Apple", "Apple", "Orange", "Orange", "Orange", "Orange", "Apple", "Apple", "Apple", "Apple", "Orange",
                "Orange", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange",
                "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Orange"};

        for (String str: fruit) {
            if(str.equals("Orange")) numOranges++;
            if(str.equals("Apple")) numApples++;
        }

        String[] apples = new String[numApples];
        String[] oranges = new String[numOranges];
        int appleCounter = 0;
        int orangeCounter = 0;

        for (int i=0; i<fruit.length; i++) {
            if(fruit[i].equals("Apple")) {
                apples[appleCounter] = fruit[i];
                fruit[i] = null;
                appleCounter++;
            }

            else if(fruit[i].equals("Orange")) {
                oranges[orangeCounter] = fruit[i];
                fruit[i] = null;
                orangeCounter++;
            }
        }

        System.out.println("Total number of fruit: " + (numApples+numOranges));
        System.out.println("Total apples: " + numApples);
        System.out.println("Total oranges: " + numOranges);

    }
}
