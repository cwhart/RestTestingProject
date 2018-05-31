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
import com.sg.baseball.service.interfaces.TeamService;

import javax.inject.Inject;

public class TestHelper {

    @Inject
    TeamService teamService;

    @Inject
    PlayerService playerService;

    @Inject
    PositionDao positionDao;

    @Inject
    PlayerPositionDao playerPositionDao;

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
}
