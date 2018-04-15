package com.sg.baseballleague.dao;

import com.sg.baseballleague.dto.BaseballPlayer;
import com.sg.baseballleague.dto.BaseballTeam;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class BaseballTeamDaoImpl implements BaseballTeamDao {

    Map<String, BaseballTeam> mapOfTeams = new HashMap<>();
    private final String TEAM_FILE = "teams.txt";
    private final String DELIMITER = "::";

    @Override
    public List<BaseballTeam> retrieveAllTeams() throws BaseballTeamPersistenceException {
        loadTeamFile();
        List<BaseballTeam> teamList = new ArrayList<>();

        for (BaseballTeam currentTeam : mapOfTeams.values()) {
            teamList.add(currentTeam);
        } return teamList;
    }

    @Override
    public BaseballTeam addTeam(BaseballTeam teamToAdd) throws BaseballTeamPersistenceException{
        loadTeamFile();
        BaseballTeam newTeam = mapOfTeams.put(teamToAdd.getTeamName(), teamToAdd);
        writeTeamFile();
        return newTeam;
    }

    @Override
    public BaseballTeam updateTeam(BaseballTeam teamToUpdate) throws BaseballTeamPersistenceException {
        loadTeamFile();
        BaseballTeam updatedTeam = mapOfTeams.put(teamToUpdate.getTeamName(), teamToUpdate);
        writeTeamFile();
        return mapOfTeams.get(updatedTeam.getTeamName());
    }

    @Override
    public BaseballTeam removeTeam(BaseballTeam teamToRemove) throws BaseballTeamPersistenceException {
        loadTeamFile();
        String teamNumber = teamToRemove.getTeamName();
        mapOfTeams.remove(teamNumber);
        writeTeamFile();

        return teamToRemove;
    }

    @Override
    public BaseballTeam retrieveSingleTeam(String teamName) throws BaseballTeamPersistenceException{
        loadTeamFile();
        return mapOfTeams.get(teamName);
    }

    private void loadTeamFile()  throws BaseballTeamPersistenceException {

        Scanner scanner;

        try {

            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(TEAM_FILE)));
        } catch (FileNotFoundException e) {
            throw new BaseballTeamPersistenceException("-_- Could not load Team list" +
                    " data into memory", e);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            BaseballTeam currentTeam = new BaseballTeam(currentTokens[0]);

            currentTeam.setHomeCity(currentTokens[1]);


            mapOfTeams.put(currentTeam.getTeamName(), currentTeam);

        }

        scanner.close();

    }


    private void writeTeamFile() throws BaseballTeamPersistenceException  {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(TEAM_FILE));

        } catch (IOException e) {
            throw new BaseballTeamPersistenceException("Could not save team data." , e);
        }

        List<BaseballTeam> teamList = this.retrieveAllTeams();
        for (BaseballTeam currentTeam : teamList) {
            out.println(currentTeam.getTeamName() + DELIMITER
                    + currentTeam.getHomeCity() + DELIMITER);

            out.flush();
        }

        out.close();

    }


}
