package com.sg.hotelreservations.dao.daoimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnDAO;
import com.sg.hotelreservations.dto.AddOn;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AddOnDAOImpl implements AddOnDAO {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public AddOnDAOImpl (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public AddOn create(AddOn addOn) {

        final String QUERY = "insert into addon (type ) VALUES (?)";

        //Get newly inserted id
        jdbcTemplate.update(QUERY,
                addOn.getType());

        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        addOn.setId(insertedId);

        //Return team with id
        return addOn;
    }

    @Override
    public AddOn retrieve(Long id) {

        final String QUERY = "select * from addon where id = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new AddOnMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(AddOn addOn) {

        final String QUERY = "update addon set type = ? where id=?";

        jdbcTemplate.update(QUERY,
                addOn.getType(),
                addOn.getId()
        );

    }

    @Override
    public void delete(AddOn addOn) {

        final String QUERY = "delete from addon where id=?";

        jdbcTemplate.update(QUERY, addOn.getId());

    }

    @Override
    public List<AddOn> retrieveAll(int limit, int offset) {

        final String QUERY = "select * from addon limit ? offset ?";

        List<AddOn> returnList = jdbcTemplate.query(QUERY, new AddOnMapper(), limit, offset);
        return returnList;
    }

    private class AddOnMapper implements RowMapper<AddOn> {
        @Override
        public AddOn mapRow(ResultSet resultSet, int i) throws SQLException {

            AddOn addOn = new AddOn();
            addOn.setId(resultSet.getLong("id"));
            addOn.setType(resultSet.getString("type"));
            return addOn;

        }
    }
}
