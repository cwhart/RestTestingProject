package com.sg.baseballleague.dao;

import com.sg.baseballleague.dto.BaseballPlayer;

import java.util.ArrayList;
import java.util.List;

public class BaseballPlayerDaoStubImpl implements BaseballPlayerDao{

    List<BaseballPlayer> playerList = new ArrayList<>();
    BaseballPlayer playerOne;
    BaseballPlayer playerTwo;

    public BaseballPlayerDaoStubImpl() {
        playerOne = new BaseballPlayer(1);
        playerOne.setTeamName("Red Sox");
        playerOne.setLastName("Schmoe");
        playerOne.setFirstName("Joe");

        playerList.add(playerOne);

        playerTwo = new BaseballPlayer(2);
        playerTwo.setTeamName("Cubs");
        playerTwo.setFirstName("Jane");
        playerTwo.setLastName("Doe");

        playerList.add(playerTwo);
    }


    @Override
    public List<BaseballPlayer> retrieveAllPlayers() throws BaseballTeamPersistenceException {
        return playerList;
    }

    @Override
    public BaseballPlayer addPlayer(BaseballPlayer playerToAdd) throws BaseballTeamPersistenceException {
        playerList.add(playerToAdd);
        return playerToAdd;
    }

    @Override
    public BaseballPlayer updatePlayer(BaseballPlayer playerToUpdate) throws BaseballTeamPersistenceException {
        return playerToUpdate;
    }

    @Override
    public BaseballPlayer removePlayer(Integer playerToRemove) throws BaseballTeamPersistenceException {
        if (playerToRemove == 1) {
            return playerOne;
        } else return playerTwo;

            }

    @Override
    public BaseballPlayer retrieveSinglePlayer(int playerNum) throws BaseballTeamPersistenceException {
        if (playerNum == 1) {
            return playerOne;
        } else return playerTwo;
    }
}
