import java.util.*;

public class ListExamples {

    public static void main(String[] args) {

        Map<String, Integer> populations = new HashMap<>();

        populations.put("USA", 313000000);
        populations.put("Canada", 34000000);
        populations.put("United Kingdom" , 63000000);
        populations.put("Japan", 127000000);

        System.out.println("Map size is: " + populations.size());

        Collection<Integer> populationValues = populations.values();

        for (Integer currentPopulation : populationValues) {
            System.out.println(currentPopulation);
        }

    }


}
