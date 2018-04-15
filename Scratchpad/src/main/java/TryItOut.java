import java.util.HashMap;
import java.util.Map;

public class TryItOut {

    public static void main(String[] args) {

        Map<Integer, PlayerObject> playerMap = new HashMap<>();

        PlayerObject newPlayer = new PlayerObject(1);
        newPlayer.setFirstName("Joe");
        newPlayer.setLastName("Schmoe");
        newPlayer.setTeamName("Red Sox");

        playerMap.put(newPlayer.getPlayerNum(), newPlayer);
        System.out.println(playerMap.get(1).getFirstName() + playerMap.get(1).getLastName() + playerMap.get(1).getTeamName());

        PlayerObject updatePlayer = new PlayerObject(1);
        updatePlayer.setFirstName("Jane");
        updatePlayer.setLastName("Doe");

        playerMap.put(updatePlayer.getPlayerNum(), updatePlayer);
        System.out.println(playerMap.get(1).getFirstName() + playerMap.get(1).getLastName() + playerMap.get(1).getTeamName());
    }
}
