package com.sg.baseballleague.dao;

import com.sg.baseballleague.dto.BaseballPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseballPlayerDaoImpl implements BaseballPlayerDao {

    private Map<Integer, BaseballPlayer> playerMap = new HashMap<>();

    @Override
    public List<BaseballPlayer> retrieveAllPlayers() {
        List<BaseballPlayer> listOfAllPlayers = new ArrayList<>();

        for(BaseballPlayer currentPlayer : playerMap.values()) {
            listOfAllPlayers.add(currentPlayer);
        }
        return listOfAllPlayers;
    }

    @Override
    public BaseballPlayer addPlayer(BaseballPlayer playerToAdd) {
        Integer playerNumber = playerToAdd.getPlayerNumber();
        playerMap.put(playerNumber, playerToAdd);

        return playerMap.get(playerNumber);
    }

    @Override
    public BaseballPlayer updatePlayer(BaseballPlayer playerToUpdate) {
        Integer playerNumber = playerToUpdate.getPlayerNumber();
        playerMap.put(playerNumber, playerToUpdate);

        return playerMap.get(playerNumber);
    }

    @Override
    public BaseballPlayer removePlayer(BaseballPlayer playerToRemove) {
        Integer playerNumber = playerToRemove.getPlayerNumber();
        playerMap.remove(playerNumber);

        return playerToRemove;
    }

    @Override
    public BaseballPlayer retrieveSinglePlayer(int playerNum) {
        return playerMap.get(playerNum);
    }
}
