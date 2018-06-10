package com.sg.baseball;

import com.sg.baseball.dao.interfaces.PlayerDao;
import com.sg.baseball.dao.interfaces.PlayerPositionDao;
import com.sg.baseball.dao.interfaces.PositionDao;
import com.sg.baseball.dao.interfaces.TeamDao;
import com.sg.baseball.dto.Player;
import com.sg.baseball.dto.PlayerPosition;
import com.sg.baseball.dto.Position;
import com.sg.baseball.dto.Team;
import com.sg.baseball.service.interfaces.PlayerService;
import com.sg.baseball.service.interfaces.PositionService;
import com.sg.baseball.service.interfaces.TeamService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TestHelper {

    @Inject
    TeamService teamService;

    @Inject
    PlayerService playerService;

    @Inject
    PositionDao positionDao;

    @Inject
    PlayerPositionDao playerPositionDao;

    @Inject
    PositionService positionService;

    public PlayerPosition createPlayerPosition(Player player, Position position) {
        PlayerPosition playerPosition = new PlayerPosition();
        playerPosition.setPlayer(player);
        playerPosition.setPosition(position);

        return playerPositionDao.create(playerPosition);
    }


    public Position createTestPosition() {
        Position position = new Position();

        position.setName("Pitcher");
        return positionDao.create(position);
    }

    public Team createTestTeam() {
        Team team = new Team();
        team.setCity("Pittsburgh");
        team.setNickname("Pirates");

        return teamService.create(team);
    }

    public Player createTestPlayerNoTeam() {
        return createTestPlayer(null);
    }

    public Player createTestPlayerWithTeam() {
        Team team = createTestTeam();
        return createTestPlayer(team);
    }

    public Player createTestPlayer(Team team) {
        Player player = new Player();

        player.setFirst("Pat");
        player.setLast("Toner");
        player.setTeam(team);

        return playerService.create(player);
    }

    public List<Position> createTestPositions (int n) {
        List<Position> createdPositions = new ArrayList<>();
        //positionsToCreate = 25;
        for (int i=0; i<n; i++) {
            Position position = new Position();
            position.setName("First Base" + i);

            createdPositions.add(positionService.create(position));
        }

        return createdPositions;
    }

    public List<Team> createTestTeams (int n) {
        List<Team> createdTeams = new ArrayList<>();
        //teamsToCreate = 25;
        for (int i=0; i<n; i++) {
            Team team = new Team();
            team.setCity("Pittsburgh" + i);
            team.setNickname("Pirates" + i);
            createdTeams.add(teamService.create(team));
        }

        return createdTeams;
    }
}
