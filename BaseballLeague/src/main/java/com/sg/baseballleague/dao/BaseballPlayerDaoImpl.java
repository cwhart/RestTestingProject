package com.sg.baseballleague.dao;

import com.sg.baseballleague.dto.BaseballPlayer;
import com.sg.baseballleague.dto.BaseballTeam;

import java.io.*;
import java.util.*;

public class BaseballPlayerDaoImpl implements BaseballPlayerDao {

    private Map<Integer, BaseballPlayer> playerMap = new HashMap<>();
    private final String PLAYER_FILE = "players.txt";
    private final String DELIMITER = "::";

    @Override
    public List<BaseballPlayer> retrieveAllPlayers() throws BaseballTeamPersistenceException {
        loadPlayerFile();
        List<BaseballPlayer> listOfAllPlayers = new ArrayList<>();

        for(BaseballPlayer currentPlayer : playerMap.values()) {
            listOfAllPlayers.add(currentPlayer);
        }
        return listOfAllPlayers;
    }

    @Override
    public BaseballPlayer addPlayer(BaseballPlayer playerToAdd) throws BaseballTeamPersistenceException {
        loadPlayerFile();
        Integer playerNumber = playerToAdd.getPlayerNumber();
        playerMap.put(playerNumber, playerToAdd);
        writePlayerFile();

        return playerMap.get(playerNumber);
    }

    @Override
    public BaseballPlayer updatePlayer(BaseballPlayer playerToUpdate)throws BaseballTeamPersistenceException {
        loadPlayerFile();
        Integer playerNumber = playerToUpdate.getPlayerNumber();
        playerMap.put(playerNumber, playerToUpdate);
        writePlayerFile();

        return playerMap.get(playerNumber);
    }

    @Override
    public BaseballPlayer removePlayer(Integer playerNum) throws BaseballTeamPersistenceException {
        loadPlayerFile();
        BaseballPlayer removedPlayer = playerMap.get(playerNum);
        playerMap.remove(playerNum);
        writePlayerFile();

        return removedPlayer;
    }

    @Override
    public BaseballPlayer retrieveSinglePlayer(int playerNum) throws BaseballTeamPersistenceException {
        loadPlayerFile();
        return playerMap.get(playerNum);
    }

    private void loadPlayerFile()  throws BaseballTeamPersistenceException {

        Scanner scanner;

        try {

            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(PLAYER_FILE)));
        } catch (FileNotFoundException e) {
            throw new BaseballTeamPersistenceException("-_- Could not load Player list" +
                    " data into memory", e);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            BaseballPlayer currentPlayer = new BaseballPlayer(Integer.parseInt(currentTokens[0]));
            currentPlayer.setFirstName(currentTokens[1]);
            currentPlayer.setLastName(currentTokens[2]);
            currentPlayer.setTeamName(currentTokens[3]);

            playerMap.put(currentPlayer.getPlayerNumber(), currentPlayer);

        }

        scanner.close();

    }


    private void writePlayerFile() throws BaseballTeamPersistenceException  {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(PLAYER_FILE));

        } catch (IOException e) {
            throw new BaseballTeamPersistenceException("Could not save team data." , e);
        }

        List<BaseballPlayer> playerList = this.retrieveAllPlayers();
        for (BaseballPlayer currentPlayer : playerList) {
            out.println(currentPlayer.getPlayerNumber() + DELIMITER
                    + currentPlayer.getFirstName() + DELIMITER
                    + currentPlayer.getLastName() + DELIMITER
                    + currentPlayer.getTeamName() + DELIMITER);

            out.flush();
        }

        out.close();

    }
}
