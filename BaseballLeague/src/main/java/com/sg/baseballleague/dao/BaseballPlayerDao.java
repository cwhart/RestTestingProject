package com.sg.baseballleague.dao;

import com.sg.baseballleague.dto.BaseballPlayer;
import com.sg.baseballleague.dto.BaseballTeam;

import java.util.List;

public interface BaseballPlayerDao {

    public List<BaseballPlayer> retrieveAllPlayers();

    public BaseballPlayer addPlayer(BaseballPlayer playerToAdd);

    public BaseballPlayer updatePlayer(BaseballPlayer playerToUpdate);

    public BaseballPlayer removePlayer(BaseballPlayer playerToRemove);

    public BaseballPlayer retrieveSinglePlayer(int playerNum);
}
