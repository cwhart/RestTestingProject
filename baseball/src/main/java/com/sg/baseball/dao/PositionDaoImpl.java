package com.sg.baseball.dao;

import com.sg.baseball.dao.interfaces.PositionDao;
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

public class PositionDaoImpl implements PositionDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public PositionDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Position create(Position position) {

        final String QUERY = "insert into position (name) VALUES (?)";

        jdbcTemplate.update(QUERY,
                            position.getName()

        );

        //Get newly inserted id
        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        position.setId(insertedId);

        //Return team with id
        return position;
    }

    @Override
    public Position read(Long id) {
        final String QUERY = "select * from position p where p.id = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new PositionMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    @Override
    public void update(Position position) {

        final String QUERY = "update position set name = ? where id=?";

        jdbcTemplate.update(QUERY, position.getName(),  position.getId());

    }

    @Override
    public void delete(Position position) {

        final String QUERY = "delete from position where id = ?";

        jdbcTemplate.update(QUERY, position.getId());

    }

    @Override
    public List<Position> getAll(int limit, int offset) {
        final String QUERY = "select * from position LIMIT ? OFFSET ?";

        return jdbcTemplate.query(QUERY, new PositionMapper(), limit, offset);

    }

    @Override
    public List<Position> getPositionByPlayer(Player player, int limit, int offset) {
        final String QUERY = "select * from position pos " +
                "inner join player_position pp on pos.id=pp.position_id " +
                " where pp.player_id = ? " +
                "LIMIT ? OFFSET ?";

        return jdbcTemplate.query(
                QUERY,
                new PositionMapper(),
                player.getId(),
                limit,
                offset);
    }

    private class PositionMapper implements RowMapper<Position> {
        @Override
        public Position mapRow(ResultSet resultSet, int i) throws SQLException {

            Position position = new Position();
            position.setId(resultSet.getLong("id"));
            position.setName(resultSet.getString("name"));
            return position;
        }
    }
}
