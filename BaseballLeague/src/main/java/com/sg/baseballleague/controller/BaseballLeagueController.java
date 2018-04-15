package com.sg.baseballleague.controller;

import com.sg.baseballleague.dao.BaseballTeamPersistenceException;
import com.sg.baseballleague.dto.BaseballPlayer;
import com.sg.baseballleague.dto.BaseballTeam;
import com.sg.baseballleague.service.BaseballLeagueServiceLayer;
import com.sg.baseballleague.service.TeamDoesNotExistException;
import com.sg.baseballleague.ui.BaseballLeagueView;

import java.util.List;

public class BaseballLeagueController {

    BaseballLeagueServiceLayer myService;
    BaseballLeagueView myView;

    public BaseballLeagueController(BaseballLeagueView view, BaseballLeagueServiceLayer service) {
        this.myService = service;
        this.myView = view;
    }

    public void run() {
        myView.printMessage("Welcome!");



            //List all available items every time the menu is displayed.
            boolean keepGoing = true;

            while (keepGoing) {
                int selection = myView.getMenuSelection();

                try {

                    switch (selection) {
                        case 1:
                            addTeam();
                            break;
                        case 2:
                            addPlayer();
                            break;
                        case 3:
                            listAllTeams();
                            break;
                        case 4:
                            listPlayersOnATeam();
                            break;
                        case 5:
                            tradeAPlayer();
                            break;
                        case 6:
                            deleteAPlayer();
                            break;
                        case 0:
                            keepGoing = false;
                            break;
                        default:
                            break;


                    }
                } catch (BaseballTeamPersistenceException e) {
                    myView.printMessage(e.getMessage());
                }

            }

    }

    public void addTeam()  throws BaseballTeamPersistenceException {
        myView.printMessage("====ADD A NEW TEAM====");
        BaseballTeam newTeam = myView.getTeamToAdd();
        myService.addTeam(newTeam);
        myView.printMessage("====TEAM SUCCESSFULLY ADDED====");
    }

    public void addPlayer() throws BaseballTeamPersistenceException {
        myView.printMessage("====ADD A PLAYER===");

        boolean enterValidTeam = false;

        while (enterValidTeam == false) {

            try {
                String teamName = myView.getTeam();
                myService.verifyTeamExists(teamName);
                enterValidTeam = true;
                BaseballPlayer newPlayer = myView.getPlayerToAdd(teamName);
                myService.addPlayer(newPlayer);
                myView.printMessage("====PLAYER SUCCESSFULLY ADDED====");
            } catch (TeamDoesNotExistException e) {
                myView.printMessage(e.getMessage());
            }
        }
    }

    public void listAllTeams() throws BaseballTeamPersistenceException {
        myView.printMessage("====LIST ALL TEAMS====");
        List teamList = myService.listAllTeams();
        myView.listAll(teamList);

    }

    public void listPlayersOnATeam() throws BaseballTeamPersistenceException {
        myView.printMessage("====LIST PLAYERS ON A TEAM====");
        String teamToList = myView.promptForTeamToList();
        List<BaseballPlayer> playersOnTeam = myService.listPlayersOnATeam(teamToList);
        myView.displayPlayers(playersOnTeam);
    }

    public void tradeAPlayer() throws BaseballTeamPersistenceException {
        myView.printMessage("===TRADE A PLAYER===");
        int numPlayerToTrade = myView.getPlayer();
        BaseballPlayer playerToTrade = myService.retrievePlayerToTrade(numPlayerToTrade);
        boolean enterValidTeam = false;

        while (enterValidTeam == false) {

            try {
                BaseballPlayer tradedPlayer = myView.getNewTeam(playerToTrade);
                myService.editPlayer(tradedPlayer);
                enterValidTeam = true;
                myView.printMessage("Player " + tradedPlayer.getFirstName() + " " + tradedPlayer.getLastName() +
                        " has been traded to the " + tradedPlayer.getTeamName() + ".");
            } catch (TeamDoesNotExistException e) {
                myView.printMessage(e.getMessage());
            }
        }

    }

    public void deleteAPlayer() throws BaseballTeamPersistenceException {
        myView.printMessage("====DELETE A PLAYER====");
        int numPlayerToRemove = myView.getPlayer();
        if (myView.confirm()) {
            myService.removePlayer(numPlayerToRemove);
        }
    }
}