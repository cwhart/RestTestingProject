package com.sg.baseball.dao;

import com.sg.baseball.dao.interfaces.PlayerPositionDao;
import com.sg.baseball.dao.interfaces.TeamDao;
import com.sg.baseball.dto.Player;
import com.sg.baseball.dto.PlayerPosition;
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

public class PlayerPositionDaoImpl implements PlayerPositionDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public PlayerPositionDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public PlayerPosition create(PlayerPosition playerPosition) {

        final String QUERY = "insert into player_position (player_id, position_id) VALUES (?, ?)";

        jdbcTemplate.update(QUERY,
                            playerPosition.getPlayer().getId(),
                            playerPosition.getPosition().getId()
        );

        //Get newly inserted id
        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        playerPosition.setId(insertedId);

        //Return team with id
        return playerPosition;

    }

    @Override
    public PlayerPosition read(Long id) {
        final String QUERY = "select * from player_position pp where pp.id = ?";

        //Bridge tables are almost always lazily loaded
        try{
            return jdbcTemplate.queryForObject(QUERY, new PlayerPositionMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }


    @Override
    public void delete(PlayerPosition playerPosition) {

        final String QUERY = "delete from player_position where id = ?";

        jdbcTemplate.update(QUERY, playerPosition.getId());

    }


    private class PlayerPositionMapper implements RowMapper<PlayerPosition> {
        @Override
        public PlayerPosition mapRow(ResultSet resultSet, int i) throws SQLException {

            PlayerPosition playerPosition = new PlayerPosition();

            playerPosition.setId(resultSet.getLong("id"));

            //Lazy load player
            Long playerId = resultSet.getLong("player_id");

            if(playerId >0) {
                Player player = new Player();
                player.setId(playerId);
                playerPosition.setPlayer(player);
            }

            //Lazy load position
            Long positionId = resultSet.getLong("position_id");

            if(positionId >0) {
                Position position = new Position();
                position.setId(positionId);
                playerPosition.setPosition(position);
            }

           return playerPosition;
        }
    }
}
