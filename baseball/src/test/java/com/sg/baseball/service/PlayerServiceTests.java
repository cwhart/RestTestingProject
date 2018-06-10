package com.sg.baseball.service;


import com.sg.baseball.TestHelper;
import com.sg.baseball.dao.interfaces.PlayerDao;
import com.sg.baseball.dto.Player;
import com.sg.baseball.dto.Position;
import com.sg.baseball.dto.Team;
import com.sg.baseball.service.interfaces.PlayerService;
import com.sg.baseball.service.interfaces.PositionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)  //says Spring in charge of running unit tests.
@ContextConfiguration(locations = {"/test-applicationContext.xml"}) //indicates the xml file to use
@Rollback //Automatically all tests will be transactional, and when complete, won't be committed; will be rolled back.
//need @Rollback and @Transactional annotations both.
@Transactional
public class PlayerServiceTests {

    @Inject
    PlayerService playerService;

    @Inject
    TestHelper testHelper;

    @Inject
    PositionService positionService;

    @Test
    public void testCreateWithTeam() {

        //Arrange & Act
        Player player = testHelper.createTestPlayerWithTeam();
        Player createdPlayer = playerService.create(player);

        //Assert
        assert createdPlayer.getId() != null;
        assert "Pat".equals(createdPlayer.getFirst()); //any time a string is compared to a constant, put
        //the constant first.
        assert "Toner".equals(createdPlayer.getLast());
        assert createdPlayer.getTeam().getId().equals(player.getTeam().getId());

    }

    @Test
    public void testCreateNoTeam() {

        //Arrange & Act
        //Team team = testHelper.createTestTeam();
        Player player = testHelper.createTestPlayerNoTeam();
        Player createdPlayer = playerService.create(player);

        //Assert
        assert createdPlayer.getId() != null;
        assert "Pat".equals(createdPlayer.getFirst()); //any time a string is compared to a constant, put
        //the constant first.
        assert "Toner".equals(createdPlayer.getLast());
        assert createdPlayer.getTeam() == null;

    }

    @Test
    public void testRead() {

        //Arrange
        Team team = testHelper.createTestTeam();
        Player createdPlayer = testHelper.createTestPlayer(team);

        //Act
        Player readPlayer = playerService.read(createdPlayer.getId());

        //Assert
        assertPlayerEquals(createdPlayer, readPlayer);

    }

    //Add test for if read returns null.

    @Test
    public void testUpdateWithTeam() {

        //Arrange

        Player player = testHelper.createTestPlayerWithTeam();

        //Change some stuff
        player.setFirst("Bob");
        player.setLast("Rumplestore");

        Team updatedTeam = testHelper.createTestTeam();
        player.setTeam(updatedTeam);

        //Act
        playerService.update(player);

        //Assert
        Player readPlayer = playerService.read(player.getId());
        assert "Bob".equals(readPlayer.getFirst());
        assert "Rumplestore".equals(readPlayer.getLast());
        assert readPlayer.getTeam().getId().equals(updatedTeam.getId());
    }

    @Test
    public void testUpdateNoTeam() {

        //Arrange

        Player player = testHelper.createTestPlayerWithTeam();

        //Change some stuff
        player.setFirst("Bob");
        player.setLast("Rumplestore");
        player.setTeam(null);

        playerService.update(player);

        //Act

        //Assert
        Player readPlayer = playerService.read(player.getId());
        assert "Bob".equals(readPlayer.getFirst());
        assert "Rumplestore".equals(readPlayer.getLast());
        assert readPlayer.getTeam() == null;
    }

    @Test
    public void testDelete() {

        //Arrange
        Player player = testHelper.createTestPlayerWithTeam();

        //Act
        playerService.delete(player);
        Player readPlayer = playerService.read(player.getId());

        //Assert
        assert readPlayer == null;
    }

    @Test
    public void testGetAll() {

        //Arrange
        List<Player> createPlayers = createTestPlayers(25);

        //Act
        List<Player> playerList = playerService.getAll(Integer.MAX_VALUE, 0);

        //Assert
       assertPlayerListsEqual(playerList, createPlayers);

    }

    @Test
    public void testGetAllPage() {

        //Arrange
        createTestPlayers(25);

        //Act
        List<Player> playerList = playerService.getAll(10, 5);

        //Assert
        assert playerList.size() == 10;

        for (int i=5; i<15; i++) {
            Player player = playerList.get(i-5);
            assert ("Joe" + i).equals(player.getFirst());
            assert ("Schmoe" + i).equals(player.getLast());
        }

    }

    @Test
    public void testGetPlayerByPosition() {

        //Arrange
        for(int i=0; i<15; i++) {
            testHelper.createTestPlayerNoTeam();
        }

        Position position = testHelper.createTestPosition();
        for (int i=0; i<10; i++) {
            Player player = testHelper.createTestPlayerNoTeam();
            testHelper.createPlayerPosition(player, position);
        }

        //Act
        List<Player> playerList = playerService.getPlayerByPosition(position, Integer.MAX_VALUE, 0);

        //Assert
        assert playerList.size() == 10;

        for(Player player : playerList) {
            List<Position> positionList = positionService.getPositionByPlayer(player, Integer.MAX_VALUE, 0);

            boolean exists = false;

            for (Position pos : positionList) {
                if (pos.getId().equals(position.getId())) exists = true;
            }

            assert exists;
        }
    }

    private List<Player> createTestPlayers(int playersToCreate) {

        List<Player> createdPlayers = new ArrayList<>();
        playersToCreate = 25;
        for (int i=0; i<playersToCreate; i++) {
            Player player = new Player();
            player.setFirst("Joe" + i);
            player.setLast("Schmoe" + i);
            createdPlayers.add(playerService.create(player));
        }

        return createdPlayers;
    }



    //@Test
//    public void testGetPlayerByTeam() {
//
//        //Arrange
//        for(int i=0; i<15; i++) {
//            testHelper.createTestPlayerNoTeam();
//        }
//
//        Team team = testHelper.createTestTeam();
//        for (int i=0; i<10; i++) {
//            testHelper.createTestPlayer(team);
//        }
//
//        //Act
//        List<Player> playerList = playerService.getPlayerByTeam(team, Integer.MAX_VALUE, 0);
//
//        //Assert
//        assert playerList.size() == 10;
//
//        for (Player player : playerList) {
//            assert player.getTeam().getId().equals(team.getId());
//        }
//    }


    public void assertPlayerListsEqual(List<Player> list1, List<Player> list2) {
        assert list1.size() == list2.size();

        for (Player player : list1) {

            boolean exists = false;

            for (Player originalPlayer : list2) {
                if (player.getId().equals(originalPlayer.getId())) {
                    exists = true;
                }
            }

            assert exists == true;
        }
    }

    private void assertPlayerEquals(Player player, Player player2) {
        assert player.getId().equals(player2.getId());
        assert player.getFirst().equals(player2.getFirst());
        assert player.getLast().equals(player2.getLast());

        Long playerTeamId = null;
        Long player2TeamId = null;

        if (player.getTeam() != null) {
            playerTeamId = player.getTeam().getId();
        }

        if (player2.getTeam() != null) {
            player2TeamId = player2.getTeam().getId();
        }

        assert playerTeamId.equals( player2TeamId);
    }




}