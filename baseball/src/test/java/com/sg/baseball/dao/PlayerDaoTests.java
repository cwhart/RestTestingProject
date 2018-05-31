package com.sg.baseball.dao;


import com.sg.baseball.TestHelper;
import com.sg.baseball.dao.interfaces.PlayerDao;
import com.sg.baseball.dto.Player;
import com.sg.baseball.dto.Position;
import com.sg.baseball.dto.Team;
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

import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)  //says Spring in charge of running unit tests.
@ContextConfiguration(locations = {"/test-applicationContext.xml"}) //indicates the xml file to use
@Rollback //Automatically all tests will be transactional, and when complete, won't be committed; will be rolled back.
//need @Rollback and @Transactional annotations both.
@Transactional
public class PlayerDaoTests {

    @Inject
    PlayerDao playerDao;

    @Inject
    TestHelper testHelper;

    @Inject
    PositionService positionService;

    @Test
    public void testCreateWithTeam() {

        //Arrange & Act
        Team team = testHelper.createTestTeam();
        Player createdPlayer = testHelper.createTestPlayer(team);

        //Assert
        assert createdPlayer.getId() != null;
        assert "Pat".equals(createdPlayer.getFirst()); //any time a string is compared to a constant, put
        //the constant first.
        assert "Toner".equals(createdPlayer.getLast());
        assert createdPlayer.getTeam().getId().equals(team.getId());

    }

    @Test
    public void testCreateNoTeam() {

        //Arrange & Act
        //Team team = testHelper.createTestTeam();
        Player createdPlayer = testHelper.createTestPlayerNoTeam();

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
        Player readPlayer = playerDao.read(createdPlayer.getId());

        //Assert
        assertPlayerEquals(createdPlayer, readPlayer);

    }

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
        playerDao.update(player);

        //Assert
        Player readPlayer = playerDao.read(player.getId());
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

        playerDao.update(player);

        //Act

        //Assert
        Player readPlayer = playerDao.read(player.getId());
        assert "Bob".equals(readPlayer.getFirst());
        assert "Rumplestore".equals(readPlayer.getLast());
        assert readPlayer.getTeam() == null;
    }

    @Test
    public void testDelete() {

        //Arrange
        Player player = testHelper.createTestPlayerWithTeam();

        //Act
        playerDao.delete(player);
        Player readPlayer = playerDao.read(player.getId());

        //Assert
        assert readPlayer == null;
    }

    @Test
    public void testGetAll() {

        //Arrange
        List<Player> createPlayers = createTestPlayers(25);

        //Act
        List<Player> playerList = playerDao.getAll(Integer.MAX_VALUE, 0);

        //Assert
        assertPlayerListsEqual(playerList, createPlayers);

    }

    @Test
    public void testGetAllPage() {

        //Arrange
        createTestPlayers(25);

        //Act
        List<Player> playerList = playerDao.getAll(10, 5);

        //Assert
        assert playerList.size() == 10;

        for (int i=5; i<15; i++) {
            Player player = playerList.get(i-5);
            assert ("Joe" + i).equals(player.getFirst());
            assert ("Schmoe" + i).equals(player.getLast());
        }

    }

    @Test
    public void testGetPlayerByTeam() {

        //Arrange
        for(int i=0; i<15; i++) {
            testHelper.createTestPlayerNoTeam();
        }

        Team team = testHelper.createTestTeam();
        for (int i=0; i<10; i++) {
            testHelper.createTestPlayer(team);
        }

        //Act
        List<Player> playerList = playerDao.getPlayerByTeam(team, Integer.MAX_VALUE, 0);

        //Assert
        assert playerList.size() == 10;

        for (Player player : playerList) {
            assert player.getTeam().getId().equals(team.getId());
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
        List<Player> playerList = playerDao.getPlayerByPosition(position, Integer.MAX_VALUE, 0);

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

    private void assertPlayerListsEqual(List<Player> list1, List<Player> list2) {
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



    private List<Player> createTestPlayers(int playersToCreate) {

        List<Player> createdPlayers = new ArrayList<>();
        playersToCreate = 25;
        for (int i=0; i<playersToCreate; i++) {
            Player player = new Player();
            player.setFirst("Joe" + i);
            player.setLast("Schmoe" + i);
            createdPlayers.add(playerDao.create(player));
        }

        return createdPlayers;
    }
}