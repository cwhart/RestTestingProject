package com.sg.baseball.dao;

import com.sg.baseball.dao.interfaces.TeamDao;
import com.sg.baseball.dto.Team;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TeamDaoImpl implements TeamDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public TeamDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Team create(Team team) {

        final String QUERY = "insert into team (city, nickname) VALUES (?, ?)";

        jdbcTemplate.update(QUERY,
                            team.getCity(),
                            team.getNickname()
        );

        //Get newly inserted id
        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        team.setId(insertedId);

        //Return team with id
        return team;
    }

    @Override
    public Team read(Long id) {
        final String QUERY = "select * from team t where t.id = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new TeamMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(Team team) {

        final String QUERY = "update team set city = ?, nickname = ? where id=?";

        jdbcTemplate.update(QUERY, team.getCity(), team.getNickname(), team.getId());

    }

    @Override
    public void delete(Team team) {

        final String QUERY = "delete from team where id = ?";

        jdbcTemplate.update(QUERY, team.getId());

    }

    @Override
    public List<Team> getAll(int limit, int offset) {
        final String QUERY = "select * from team LIMIT ? OFFSET ?";

        return jdbcTemplate.query(QUERY, new TeamMapper(), limit, offset);

    }

    private class TeamMapper implements RowMapper<Team> {
        @Override
        public Team mapRow(ResultSet resultSet, int i) throws SQLException {

            Team team = new Team();
            team.setId(resultSet.getLong("id"));
            team.setCity(resultSet.getString("city"));
            team.setNickname(resultSet.getString("nickname"));
            return team;
        }
    }
}
