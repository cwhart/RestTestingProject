package com.sg.baseball.dao;

import com.sg.baseball.dao.interfaces.PlayerDao;
import com.sg.baseball.dao.interfaces.TeamDao;
import com.sg.baseball.dto.Player;
import com.sg.baseball.dto.Position;
import com.sg.baseball.dto.Team;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PlayerDaoImpl implements PlayerDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public PlayerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Player create(Player player) {

        final String QUERY = "insert into player (first, last, team_id) VALUES (?, ?, ?)";

        Long teamId = getTeamId(player);

        jdbcTemplate.update(QUERY,
                            player.getFirst(),
                            player.getLast(),
                            teamId
        );

        //Get newly inserted id
        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        player.setId(insertedId);

        //Return team with id
        return player;

    }



    @Override
    public Player read(Long id) {
        final String QUERY = "select * from player p where p.id = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new PlayerLazyLoadTeamMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    @Override
    public void update(Player player) {

        final String QUERY = "update player set first = ?, last = ?, team_id = ? where id=?";

        Long teamId = null;
        if(player.getTeam() != null) {
            teamId = player.getTeam().getId();
        }

        jdbcTemplate.update(QUERY,
                        player.getFirst(),
                        player.getLast(),
                        teamId,
                        player.getId()
        );

    }

    @Override
    public void delete(Player player) {

        final String QUERY = "delete from player where id = ?";

        jdbcTemplate.update(QUERY, player.getId());

    }

    @Override
    public List<Player> getAll(int limit, int offset) {
        final String QUERY = "select * from player LIMIT ? OFFSET ?";

        return jdbcTemplate.query(QUERY, new PlayerLazyLoadTeamMapper(), limit, offset);

    }

    @Override
    public List<Player> getPlayerByTeam(Team team, int limit, int offset) {
        final String QUERY = "select * from player where team_id = ? LIMIT ? OFFSET ?";

        return jdbcTemplate.query(
                QUERY,
                new PlayerLazyLoadTeamMapper(),
                team.getId(),
                limit,
                offset);
    }

    private Long getTeamId(Player player) {
        Long teamId = null;
        if (player.getTeam() != null) {
            teamId = player.getTeam().getId();
        }
        return teamId;
    }

    @Override
    public List<Player> getPlayerByPosition(Position position, int limit, int offset) {

        final String QUERY = "select * from player play " +
                "inner join player_position pp on play.id=pp.player_id " +
                " where pp.position_id = ? " +
                "LIMIT ? OFFSET ?";

        return jdbcTemplate.query(
                QUERY,
                new PlayerLazyLoadTeamMapper(),
                position.getId(),
                limit,
                offset);
        //return null;
    }

    private class PlayerLazyLoadTeamMapper implements RowMapper<Player> {
        @Override
        public Player mapRow(ResultSet resultSet, int i) throws SQLException {

            Player player = new Player();
            player.setId(resultSet.getLong("id"));
            player.setFirst(resultSet.getString("first"));
            player.setLast(resultSet.getString("last"));

            //lazy load team
            Long teamId = resultSet.getLong("team_id");

            if(teamId >0) {
                Team team = new Team();
                team.setId(teamId);
                player.setTeam(team);
            }
            return player;

        }
    }


}
