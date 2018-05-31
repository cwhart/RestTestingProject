package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Director;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.sql.Date.valueOf;

public class DirectorJdbcTemplateImpl implements DirectorDao {

    private JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT_DIRECTOR
            = "insert into director (first_name, last_name) "
            + " values (?, ?)";

    private static final String SQL_DELETE_DIRECTOR
            = "delete from director where director_id = ?";

    private static final String SQL_UPDATE_DIRECTOR
            = "update director set first_name = ?, last_name = ? "
            + "where director_id = ?";

    private static final String SQL_SELECT_DIRECTOR_BY_ID
            = "select * from director where director_id = ?";

    private static final String SQL_SELECT_DIRECTOR_BY_LASTNAME
            = "select * from director where last_name = ?";

    private static final String SQL_SELECT_ALL_DIRECTORS
            = "select * from director";

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addDirector(Director director) {
        jdbcTemplate.update(SQL_INSERT_DIRECTOR,
                director.getFirst_name(),
                director.getLast_name());

        int directorId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        director.setDirector_id(directorId);
    }

    @Override
    public void removeDirector(int directorId) {
        jdbcTemplate.update(SQL_DELETE_DIRECTOR, directorId);

    }

    @Override
    public void updateDirector(Director director) {
        jdbcTemplate.update(SQL_UPDATE_DIRECTOR,
                director.getFirst_name(),
                director.getLast_name(),
                director.getDirector_id());

    }

    @Override
    public List<Director> getAllDirectors() {
        return jdbcTemplate.query(SQL_SELECT_ALL_DIRECTORS, new DirectorMapper());
    }

    @Override
    public Director getDirectorById(int directorId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_DIRECTOR_BY_ID, new DirectorMapper(), directorId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Director getDirectorByLastName(String lastName) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_DIRECTOR_BY_LASTNAME, new DirectorMapper(), lastName);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private static final class DirectorMapper implements RowMapper<Director> {

        @Override
        public Director mapRow(ResultSet rs, int i) throws SQLException {
            Director director = new Director(rs.getString("last_name"));
            director.setDirector_id(rs.getInt("director_id"));
            director.setFirst_name(rs.getString("first_name"));
            //director.setLast_name(rs.getString("last_name"));

            return director;
        }
    }


}
