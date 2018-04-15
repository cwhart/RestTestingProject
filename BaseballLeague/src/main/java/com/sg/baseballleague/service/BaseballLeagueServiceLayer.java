package com.sg.baseballleague.service;

import com.sg.baseballleague.dao.BaseballTeamPersistenceException;
import com.sg.baseballleague.dto.BaseballPlayer;
import com.sg.baseballleague.dto.BaseballTeam;

import java.util.List;

public interface BaseballLeagueServiceLayer {

    public BaseballTeam addTeam(BaseballTeam newTeam) throws BaseballTeamPersistenceException;

    public List<BaseballTeam> listAllTeams() throws BaseballTeamPersistenceException;

    public BaseballPlayer addPlayer(BaseballPlayer playerToAdd) throws BaseballTeamPersistenceException;

    public List<BaseballPlayer> listPlayersOnATeam(String teamName) throws BaseballTeamPersistenceException;

    public BaseballPlayer retrievePlayerToTrade(int playerNumber) throws BaseballTeamPersistenceException;

    public BaseballPlayer editPlayer(BaseballPlayer tradedPlayer) throws BaseballTeamPersistenceException,
            TeamDoesNotExistException;

    public BaseballPlayer removePlayer(int playerNum) throws BaseballTeamPersistenceException;

    public void verifyTeamExists(String thisTeam) throws TeamDoesNotExistException, BaseballTeamPersistenceException;
}
