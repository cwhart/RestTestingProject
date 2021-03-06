package com.sg.baseballleague.ui;

import com.sg.baseballleague.dto.BaseballPlayer;
import com.sg.baseballleague.dto.BaseballTeam;
import com.sg.baseballleague.service.TeamDoesNotExistException;

import java.util.List;

public class BaseballLeagueView {

    UserIO io;

    public BaseballLeagueView(UserIO io) {
        this.io = io;
    }

    public int getMenuSelection() {

        io.print("=====================");
        io.print("======MAIN MENU======");
        io.print("=====================");
        io.print("1. Add a Team");
        io.print("2. Add a Player");
        io.print("3. List all teams");
        io.print("4. List all players on a team");
        io.print("5. Trade a player");
        io.print("6. Delete a player");
        io.print("0. Exit");

        return io.readInt("Please enter your selection: ", 0, 6);
    }

    public void printMessage(String message) {
        io.print(message);
    }

    public BaseballTeam getTeamToAdd() {
        BaseballTeam newTeam = new BaseballTeam(io.readString("Please enter the team name."));
        newTeam.setHomeCity(io.readString("Please enter the name of the home city."));

        return newTeam;
    }

    public void listAll(List<BaseballTeam> listOfTeams) {

        for (BaseballTeam currentTeam : listOfTeams) {
            io.print("Team Name: " + currentTeam.getTeamName());
            io.print("Home City: " + currentTeam.getHomeCity());

        }
    }

    public String getTeam() {
        return io.readString("Please enter the team this player will be on:");
    }

    public BaseballPlayer getPlayerToAdd(String teamName) {
        BaseballPlayer newPlayer = new BaseballPlayer(io.readInt("Please enter the " +
                "player's number: "));
        newPlayer.setFirstName(io.readString("Enter the player's first name: "));
        newPlayer.setLastName(io.readString("Enter the player's last name: "));
        newPlayer.setTeamName(teamName);

        return newPlayer;
    }

    public String promptForTeamToList() {
        return io.readString("Please enter the name of the team: ");
    }

    public void displayPlayers (List <BaseballPlayer> listOfPlayers) {
        for (BaseballPlayer currentPlayer : listOfPlayers) {
            io.print(currentPlayer.getPlayerNumber() + ": " + currentPlayer.getFirstName() + " " +
                    currentPlayer.getLastName());
        }
    }

    public int getPlayer() {
        return io.readInt("Please enter the player number: ");
    }

    public BaseballPlayer getNewTeam(BaseballPlayer player) {
        String newTeam = io.readString("Please enter the player's new team name: ");
        player.setTeamName(newTeam);
        return player;
    }

    public boolean confirm() {
        String input =  io.readString("Are you sure? Y/N");
        if (input.toUpperCase().equals("Y")) {
            return true;
        } return false;
    }
}
