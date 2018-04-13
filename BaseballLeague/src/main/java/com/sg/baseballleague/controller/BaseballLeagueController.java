package com.sg.baseballleague.controller;

import com.sg.baseballleague.dao.BaseballPlayerDao;
import com.sg.baseballleague.dao.BaseballTeamDao;
import com.sg.baseballleague.dao.BaseballTeamDaoImpl;
import com.sg.baseballleague.dto.BaseballPlayer;
import com.sg.baseballleague.dto.BaseballTeam;
import com.sg.baseballleague.service.BaseballLeagueServiceLayer;
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

            }

    }

    public void addTeam() {
        myView.printMessage("====ADD A NEW TEAM====");
        BaseballTeam newTeam = myView.getTeamToAdd();
        myService.addTeam(newTeam);
        myView.printMessage("====TEAM SUCCESSFULLY ADDED====");
    }

    public void addPlayer() {
        myView.printMessage("====ADD A PLAYER===");
        BaseballPlayer newPlayer = myView.getPlayerToAdd();
        myService.addPlayer(newPlayer);
        myView.printMessage("====PLAYER SUCCESSFULLY ADDED====");
    }

    public void listAllTeams() {
        myView.printMessage("====LIST ALL TEAMS====");
        List teamList = myService.listAllTeams();
        myView.listAll(teamList);

    }

    public void listPlayersOnATeam() {
        myView.printMessage("====LIST PLAYERS ON A TEAM====");
        String teamToList = myView.promptForTeamToList();
        List<BaseballPlayer> playersOnTeam = myService.listPlayersOnATeam(teamToList);
        myView.displayPlayers(playersOnTeam);
    }

    public void tradeAPlayer() {
        System.out.println("Trade a player");
    }

    public void deleteAPlayer() {
        System.out.println("Delete a player");
    }
}