import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class HashMapPractice {

    public static void main(String[] args) {

        HashMap<String, Integer> populations = new HashMap<>();

        populations.put("USA", 313000000);

        populations.put("Canada", 34000000);

        populations.put("United Kingdom", 63000000);

        populations.put("Japan", 127000000);

        System.out.println("Map size is: " + populations.size());

        Collection<Integer> popValues = populations.values();

        for (Integer p : popValues) {
            System.out.println(p);
        }

    }
}
