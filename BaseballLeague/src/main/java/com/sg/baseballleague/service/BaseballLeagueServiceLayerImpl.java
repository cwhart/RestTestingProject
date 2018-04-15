package com.sg.baseballleague.service;

import com.sg.baseballleague.dao.BaseballPlayerDao;
import com.sg.baseballleague.dao.BaseballTeamDao;
import com.sg.baseballleague.dao.BaseballTeamPersistenceException;
import com.sg.baseballleague.dto.BaseballPlayer;
import com.sg.baseballleague.dto.BaseballTeam;

import java.util.ArrayList;
import java.util.List;

public class BaseballLeagueServiceLayerImpl implements BaseballLeagueServiceLayer {

    BaseballPlayerDao playerDao;
    BaseballTeamDao teamDao;

    public BaseballLeagueServiceLayerImpl(BaseballTeamDao teamDao, BaseballPlayerDao playerDao) {
        this.playerDao = playerDao;
        this.teamDao = teamDao;
    }

    public BaseballTeam addTeam(BaseballTeam newTeam) throws BaseballTeamPersistenceException {
        return teamDao.addTeam(newTeam);
    }

    public List<BaseballTeam> listAllTeams() throws BaseballTeamPersistenceException{
        return teamDao.retrieveAllTeams();
    }

    public BaseballPlayer addPlayer(BaseballPlayer playerToAdd) throws BaseballTeamPersistenceException {
        //Originally had the View taking in all the info for the new player, then passing to the
        //Service layer & validating that the team exists, however it seemed better practice to
        //verify the team exists first, before entering the rest of the player info. However making
        //those changes moved most of the business logic to the Controller. Need to understand a better
        //way to do this.

        return playerDao.addPlayer(playerToAdd);
    }


    public List<BaseballPlayer> listPlayersOnATeam(String teamName) throws BaseballTeamPersistenceException  {
        List<BaseballPlayer> allPlayers = playerDao.retrieveAllPlayers();
        List<BaseballPlayer> playersOnTeam = new ArrayList<>();

        for (BaseballPlayer currentPlayer : allPlayers) {
            if (currentPlayer.getTeamName().equals(teamName)) {
                playersOnTeam.add(currentPlayer);
            }
        }
        return playersOnTeam;
    }

    public BaseballPlayer retrievePlayerToTrade(int playerNumber) throws BaseballTeamPersistenceException {
        return playerDao.retrieveSinglePlayer(playerNumber);
    }

    public BaseballPlayer editPlayer(BaseballPlayer tradedPlayer) throws BaseballTeamPersistenceException,
        TeamDoesNotExistException{
        verifyTeamExists(tradedPlayer.getTeamName());
        return playerDao.updatePlayer(tradedPlayer);
    }

    public BaseballPlayer removePlayer(int playerNum) throws BaseballTeamPersistenceException {
        return playerDao.removePlayer(playerNum);
    }

    public void verifyTeamExists(String thisTeam) throws TeamDoesNotExistException, BaseballTeamPersistenceException {
        List<BaseballTeam> listOfTeams = listAllTeams();
        for (BaseballTeam currentTeam : listOfTeams) {
            if (currentTeam.getTeamName().equals(thisTeam)) {
                return;
            }
        } throw new TeamDoesNotExistException(thisTeam + ": Team does not exist.");
    }

}
