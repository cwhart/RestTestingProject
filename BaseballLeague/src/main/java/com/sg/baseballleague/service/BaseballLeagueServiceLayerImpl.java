package com.sg.baseballleague.service;

import com.sg.baseballleague.dao.BaseballPlayerDao;
import com.sg.baseballleague.dao.BaseballTeamDao;
import com.sg.baseballleague.dto.BaseballPlayer;
import com.sg.baseballleague.dto.BaseballTeam;

import java.util.ArrayList;
import java.util.List;

public class BaseballLeagueServiceLayerImpl implements BaseballLeagueServiceLayer{

    BaseballPlayerDao player;
    BaseballTeamDao team;

    public BaseballLeagueServiceLayerImpl(BaseballTeamDao team, BaseballPlayerDao player ) {
        this.player = player;
        this.team = team;
    }

    public BaseballTeam addTeam(BaseballTeam newTeam) {
        return team.addTeam(newTeam);
    }

    public List<BaseballTeam> listAllTeams() {
        return team.retrieveAllTeams();
    }

    public BaseballPlayer addPlayer(BaseballPlayer playerToAdd) {
        return player.addPlayer(playerToAdd);
    }

    public List<BaseballPlayer> listPlayersOnATeam(String teamName) {
        List<BaseballPlayer> allPlayers = player.retrieveAllPlayers();
        List<BaseballPlayer> playersOnTeam = new ArrayList<>();

        for (BaseballPlayer currentPlayer : allPlayers) {
            if(currentPlayer.getTeamName().equals(teamName)) {
                playersOnTeam.add(currentPlayer);
            }
        } return playersOnTeam;
    }
}
