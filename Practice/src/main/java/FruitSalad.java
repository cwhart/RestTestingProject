import java.util.Arrays;

public class FruitSalad {

    public static void main(String[] args) {

        String[] fruit = {"Kiwi Fruit", "Gala Apple", "Granny Smith Apple", "Cherry Tomato", "Gooseberry",
                "Beefsteak Tomato", "Braeburn Apple", "Blueberry", "Strawberry", "Navel Orange", "Pink Pearl Apple",
                "Raspberry", "Blood Orange", "Sungold Tomato", "Fuji Apple", "Blackberry", "Banana", "Pineapple",
                "Florida Orange", "Kiku Apple", "Mango", "Satsuma Orange", "Watermelon", "Snozzberry"};

        String[] fruitSalad = new String[12];
        int numApples = 0;
        int numOranges = 0;
        int saladCounter = 0;

        for (String thisFruit : fruit) {
            if ((thisFruit.toLowerCase().contains("berry"))) {
                fruitSalad[saladCounter] = thisFruit;
                saladCounter++;
                if(saladCounter >= 12) break;
            }

            else if((thisFruit.toLowerCase().contains(" apple")) && numApples < 3) {
                fruitSalad[saladCounter] = thisFruit;
                saladCounter++;
                numApples++;
                if(saladCounter >= 12) break;
            }

            else if((thisFruit.toLowerCase().contains("orange")) && numOranges < 2) {
                fruitSalad[saladCounter] = thisFruit;
                saladCounter++;
                numOranges++;
                if(saladCounter >= 12) break;
            }

            else if ((thisFruit.toLowerCase().contains("tomato")));

            else if ((!thisFruit.toLowerCase().contains("tomato")) && (!thisFruit.toLowerCase().contains(" apple"))
                    && (!thisFruit.toLowerCase().contains("orange"))){
                fruitSalad[saladCounter] = thisFruit;
                saladCounter++;
                if(saladCounter >= 12) break;
            }



        }

        System.out.println(Arrays.toString(fruitSalad));
    }

}

