package com.sg.baseballleague.dao;

import com.sg.baseballleague.dto.BaseballPlayer;
import com.sg.baseballleague.dto.BaseballTeam;

import java.util.List;

public interface BaseballPlayerDao {

    public List<BaseballPlayer> retrieveAllPlayers() throws BaseballTeamPersistenceException;

    public BaseballPlayer addPlayer(BaseballPlayer playerToAdd) throws BaseballTeamPersistenceException;

    public BaseballPlayer updatePlayer(BaseballPlayer playerToUpdate) throws BaseballTeamPersistenceException;

    public BaseballPlayer removePlayer(Integer playerToRemove) throws BaseballTeamPersistenceException;

    public BaseballPlayer retrieveSinglePlayer(int playerNum) throws BaseballTeamPersistenceException;
}
