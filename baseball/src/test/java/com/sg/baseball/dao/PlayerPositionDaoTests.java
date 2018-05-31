package com.sg.baseball.dao;


import com.sg.baseball.TestHelper;
import com.sg.baseball.dao.interfaces.PlayerPositionDao;
import com.sg.baseball.dao.interfaces.TeamDao;
import com.sg.baseball.dto.Player;
import com.sg.baseball.dto.PlayerPosition;
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

import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)  //says Spring in charge of running unit tests.
@ContextConfiguration(locations = {"/test-applicationContext.xml"}) //indicates the xml file to use
@Rollback //Automatically all tests will be transactional, and when complete, won't be committed; will be rolled back.
//need @Rollback and @Transactional annotations both.
@Transactional
public class PlayerPositionDaoTests {

    @Inject
    TestHelper testHelper;

    @Inject
    PlayerPositionDao playerPositionDao;

    @Test
    public void testCreate() {

        //Arrange
        Player player = testHelper.createTestPlayerWithTeam();
        Position position = testHelper.createTestPosition();

        PlayerPosition playerPosition = testHelper.createPlayerPosition(player, position);

        //Assert
        assert playerPosition.getId() != null;
        assert playerPosition.getPlayer().getId().equals(player.getId());
        assert playerPosition.getPosition().getId().equals(position.getId());

    }

    @Test
    public void testRead() {

        Player player = testHelper.createTestPlayerWithTeam();
        Position position = testHelper.createTestPosition();

        PlayerPosition playerPosition = testHelper.createPlayerPosition(player, position);

        //Act
        PlayerPosition read = playerPositionDao.read(playerPosition.getId());

        //Assert
        assert playerPosition.getId().equals(read.getId());
        assert playerPosition.getPosition().getId().equals(read.getPosition().getId());
        assert playerPosition.getPlayer().getId().equals(read.getPlayer().getId());

    }
//
    @Test
    public void testDelete() {

        //Arrange
        Player player = testHelper.createTestPlayerWithTeam();
        Position position = testHelper.createTestPosition();

        PlayerPosition playerPosition = testHelper.createPlayerPosition(player, position);

        //Act
        playerPositionDao.delete(playerPosition);

        //Assert
        PlayerPosition read = playerPositionDao.read(playerPosition.getId());

        assert read == null;
    }

//
//    private void assertTeamListsEqual(List<Team> list1, List<Team> list2) {
//        assert list1.size() == list2.size();
//
//        for (Team team : list1) {
//
//            boolean exists = false;
//
//            for (Team originalTeam : list2) {
//                if (team.getId().equals(originalTeam.getId())) {
//                    exists = true;
//                }
//            }
//
//            assert exists == true;
//        }
//    }
//
//    private void assertTeamEquals(Team team, Team team2) {
//        assert team.getId().equals(team2.getId());
//        assert team.getCity().equals(team2.getCity());
//        assert team.getNickname().equals(team2.getNickname());
//    }
//
//
//
//    private List<Team> createTestTeams(int teamsToCreate) {
//
//        List<Team> createdTeams = new ArrayList<>();
//        teamsToCreate = 25;
//        for (int i=0; i<teamsToCreate; i++) {
//            Team team = new Team();
//            team.setCity("Pittsburgh" + i);
//            team.setNickname("Pirates" + i);
//            createdTeams.add(teamDao.create(team));
//        }
//
//        return createdTeams;
//    }
}