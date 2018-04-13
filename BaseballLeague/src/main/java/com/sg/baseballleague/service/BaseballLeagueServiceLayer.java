package com.sg.baseballleague.service;

import com.sg.baseballleague.dto.BaseballPlayer;
import com.sg.baseballleague.dto.BaseballTeam;

import java.util.List;

public interface BaseballLeagueServiceLayer {

    public BaseballTeam addTeam(BaseballTeam newTeam);

    public List<BaseballTeam> listAllTeams();

    public BaseballPlayer addPlayer(BaseballPlayer playerToAdd);

    public List<BaseballPlayer> listPlayersOnATeam(String teamName);
}
