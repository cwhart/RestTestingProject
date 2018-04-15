package com.sg.baseballleague.service;

import com.sg.baseballleague.dao.*;
import com.sg.baseballleague.dto.BaseballPlayer;
import com.sg.baseballleague.dto.BaseballTeam;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class BaseballLeagueServiceLayerTest {

    BaseballLeagueServiceLayer service;

    public BaseballLeagueServiceLayerTest() {
        /*BaseballTeamDao teamDao = new BaseballTeamDaoStubImpl();
        BaseballPlayerDao playerDao = new BaseballPlayerDaoStubImpl();
        service = new BaseballLeagueServiceLayerImpl(teamDao, playerDao);*/

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", BaseballLeagueServiceLayer.class);
    }

    @Test
    public void testAddTeam() throws BaseballTeamPersistenceException {
        BaseballTeam newTeam = new BaseballTeam("Mariners");
        newTeam.setHomeCity("Seattle");
        BaseballTeam addedTeam = service.addTeam(newTeam);

        assertEquals(newTeam, addedTeam);

    }

    @Test
    public void testListAllTeams() throws BaseballTeamPersistenceException{
        List<BaseballTeam> listOfAllTeams = service.listAllTeams();
        assertEquals(2, listOfAllTeams.size());


    }

    @Test
    public void testAddPlayer() throws BaseballTeamPersistenceException{
        BaseballPlayer newPlayer = new BaseballPlayer(55);
        newPlayer.setLastName("Schmoe");
        newPlayer.setFirstName("Joe");
        newPlayer.setTeamName("Cubs");

        BaseballPlayer addedPlayer = service.addPlayer(newPlayer);

        assertEquals(newPlayer, addedPlayer);
    }

    @Test
    public void testListPlayersOnATeam() throws BaseballTeamPersistenceException{
        BaseballPlayer newPlayer1 = new BaseballPlayer(55);
        newPlayer1.setLastName("Schmoe1");
        newPlayer1.setFirstName("Joe1");
        newPlayer1.setTeamName("Cubs");
        service.addPlayer(newPlayer1);

        BaseballPlayer newPlayer2 = new BaseballPlayer(56);
        newPlayer2.setLastName("Schmoe2");
        newPlayer2.setFirstName("Joe2");
        newPlayer2.setTeamName("Cubs");
        service.addPlayer(newPlayer2);

        BaseballPlayer newPlayer3 = new BaseballPlayer(57);
        newPlayer3.setLastName("Schmoe3");
        newPlayer3.setFirstName("Joe3");
        newPlayer3.setTeamName("Cubs");
        service.addPlayer(newPlayer3);

        BaseballPlayer newPlayer4 = new BaseballPlayer(58);
        newPlayer4.setLastName("Schmoe4");
        newPlayer4.setFirstName("Joe4");
        newPlayer4.setTeamName("Yankees");
        service.addPlayer(newPlayer4);

        List<BaseballPlayer> playersOnATeam = service.listPlayersOnATeam("Cubs");
        assertEquals(4, playersOnATeam.size());
        assertFalse(playersOnATeam.contains(newPlayer4));

    }

    @Test
    public void testRetrievePlayerToTrade() throws BaseballTeamPersistenceException {
        BaseballPlayer playerToTrade = service.retrievePlayerToTrade(1);

        assertEquals("Red Sox", playerToTrade.getTeamName());
        assertEquals("Joe", playerToTrade.getFirstName());
        assertEquals("Schmoe", playerToTrade.getLastName());
    }

    @Test
    public void testEditPlayerHappyPath() throws BaseballTeamPersistenceException, TeamDoesNotExistException{
        BaseballPlayer newPlayer1 = new BaseballPlayer(55);
        newPlayer1.setLastName("Schmoe1");
        newPlayer1.setFirstName("Joe1");
        newPlayer1.setTeamName("Cubs");
        service.addPlayer(newPlayer1);

        newPlayer1.setFirstName("Steve");

        BaseballPlayer updatedPlayer = service.editPlayer(newPlayer1);

        assertEquals("Steve", updatedPlayer.getFirstName());
        assertEquals("Cubs", updatedPlayer.getTeamName());
    }

    @Test (expected = TeamDoesNotExistException.class)
    public void testEditPlayerTeamDoesNotExist() throws BaseballTeamPersistenceException, TeamDoesNotExistException {
        BaseballPlayer newPlayer1 = new BaseballPlayer(55);
        newPlayer1.setLastName("Schmoe1");
        newPlayer1.setFirstName("Joe1");
        newPlayer1.setTeamName("Cubs");
        service.addPlayer(newPlayer1);

        newPlayer1.setTeamName("Mariners");
        service.editPlayer(newPlayer1);

    }

    @Test
    public void testRemovePlayer() throws BaseballTeamPersistenceException{
        BaseballPlayer expectedResults = service.retrievePlayerToTrade(1);
        BaseballPlayer removedPlayer = service.removePlayer(1);

        assertEquals(1, service.listPlayersOnATeam("Cubs").size());
        assertEquals(expectedResults, removedPlayer);
    }

    @Test
    public void testVerifyTeamExistsHappyPath() throws TeamDoesNotExistException, BaseballTeamPersistenceException{
        service.verifyTeamExists("Cubs");

    }

    @Test (expected = TeamDoesNotExistException.class)
    public void testVerifyTeamExistsException() throws TeamDoesNotExistException, BaseballTeamPersistenceException{
        service.verifyTeamExists("Mariners");

    }
}