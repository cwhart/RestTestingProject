package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Change;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ChangeDaoJdbcTemplateImpl implements ChangeDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_CHANGE
            = "insert into change (quarters, dimes, nickels, pennies) "
            + "values (?, ?, ?, ?)";

    private static final String SQL_DELETE_CHANGE
            = "delete from change where change_id = ?";

    private static final String SQL_UPDATE_CHANGE
            = "update change set quarters = ?, dimes = ?, nickels = ?, pennies = ? where change_id = ? ";

    private static final String SQL_RETRIEVE_CHANGE
            = "select * from change where change_id = ?";

    private static final String SQL_RETRIEVE_ALL_CHANGE
            = "select * from change";

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void createChange(Change change) {
        jdbcTemplate.update(SQL_INSERT_CHANGE,
                change.getQuarters(),
                change.getDimes(),
                change.getNickels(),
                change.getPennies(),
                change.getChangeId());

        int changeId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        change.setChangeId(changeId);
    }

    public void removeChange(int changeId) {
        jdbcTemplate.update(SQL_DELETE_CHANGE, changeId);
    }

    public void updateChange(Change change) {
        jdbcTemplate.update(SQL_UPDATE_CHANGE,
                change.getQuarters(),
                change.getDimes(),
                change.getNickels(),
                change.getPennies(),
                change.getChangeId());
    }

    public Change retrieveChange(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_RETRIEVE_CHANGE, new ChangeDaoJdbcTemplateImpl.ChangeMapper(), id);

        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public List<Change> retrieveAllChange() {
        return jdbcTemplate.query(SQL_RETRIEVE_ALL_CHANGE, new ChangeDaoJdbcTemplateImpl.ChangeMapper());
    }

    private static final class ChangeMapper implements RowMapper<Change> {

        @Override
        public Change mapRow(ResultSet rs, int i) throws SQLException {
            Change change = new Change();
            change.setQuarters(rs.getInt("quarters"));
            change.setDimes(rs.getInt("dimes"));
            change.setNickels(rs.getInt("nickels"));
            change.setPennies(rs.getInt("pennies"));
            return change;
        }
    }
}
