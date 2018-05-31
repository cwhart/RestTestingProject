package com.sg.baseball.service;


import com.sg.baseball.dao.interfaces.TeamDao;
import com.sg.baseball.dto.Team;
import com.sg.baseball.service.interfaces.TeamService;
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
public class TeamServiceTests {

    @Inject
    TeamService teamService;

    @Test
    public void testCreate() {

        //Arrange & Act
        Team createdTeam = createTestTeam();

        //Assert
        assert createdTeam.getId() != null;
        assert "Pittsburgh".equals(createdTeam.getCity()); //any time a string is compared to a constant, put
        //the constant first.
        assert "Pirates".equals(createdTeam.getNickname());

    }

    @Test
    public void testRead() {

        //Arrange
        Team team = createTestTeam();

        //Act
        Team team2 = teamService.read(team.getId());

        //Assert
        assertTeamEquals(team, team2);

    }

    @Test
    public void testUpdate() {

        //Arrange
        Team team = createTestTeam();

        //Change some stuff
        team.setCity("Boston");
        team.setNickname("Graverobbers");

        //Act
        teamService.update(team);

        //Assert
        Team readTeam = teamService.read(team.getId());
        assert "Boston".equals(readTeam.getCity());
        assert "Graverobbers".equals(readTeam.getNickname());
    }

    @Test
    public void testDelete() {

        //Arrange
        Team team = createTestTeam();

        //Act
        teamService.delete(team);

        //Assert
        assertNull(teamService.read(team.getId()));
    }

    @Test
    public void testGetAll() {

        //Arrange
        List<Team> createTeams = createTestTeams(25);

        //Act
        List<Team> teamList = teamService.getAll(Integer.MAX_VALUE, 0);

        //Assert
        assertTeamListsEqual(teamList, createTeams);

    }

    @Test
    public void testGetAllPage() {

        //Arrange
        createTestTeams(25);

        //Act
        List<Team> teamList = teamService.getAll(10, 5);

        //Assert
        assert teamList.size() == 10;

        for (int i=5; i<15; i++) {
            Team team = teamList.get(i-5);
            assert ("Pittsburgh" + i).equals(team.getCity());
            assert ("Pirates" + i).equals(team.getNickname());
        }

    }

    private void assertTeamListsEqual(List<Team> list1, List<Team> list2) {
        assert list1.size() == list2.size();

        for (Team team : list1) {

            boolean exists = false;

            for (Team originalTeam : list2) {
                if (team.getId().equals(originalTeam.getId())) {
                    exists = true;
                }
            }

            assert exists == true;
        }
    }

    private void assertTeamEquals(Team team, Team team2) {
        assert team.getId().equals(team2.getId());
        assert team.getCity().equals(team2.getCity());
        assert team.getNickname().equals(team2.getNickname());
    }

    private Team createTestTeam() {
        Team team = new Team();
        team.setCity("Pittsburgh");
        team.setNickname("Pirates");

        return teamService.create(team);
    }

    private List<Team> createTestTeams(int teamsToCreate) {

        List<Team> createdTeams = new ArrayList<>();
        teamsToCreate = 25;
        for (int i=0; i<teamsToCreate; i++) {
            Team team = new Team();
            team.setCity("Pittsburgh" + i);
            team.setNickname("Pirates" + i);
            createdTeams.add(teamService.create(team));
        }

        return createdTeams;
    }
}