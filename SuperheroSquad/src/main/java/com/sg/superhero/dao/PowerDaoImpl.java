package com.sg.superhero.dao;

import com.sg.superhero.dao.interfaces.PowerDao;
import com.sg.superhero.dto.Power;
import com.sg.superhero.dto.Super;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PowerDaoImpl implements PowerDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public PowerDaoImpl (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Power create(Power power) {
        final String QUERY = "insert into power (name ) VALUES (?)";

        //Get newly inserted id
        jdbcTemplate.update(QUERY,
                power.getName()

        );
        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        power.setId(insertedId);

        //Return team with id
        return power;
    }

    @Override
    public List<Power> retrieveAll(int limit, int offset) {
        final String QUERY = "select * from power limit ? offset ?";

        return jdbcTemplate.query(QUERY, new PowerDaoImpl.PowerMapper(), limit, offset);
    }

    @Override
    public Power retrieve(Long id) {
        final String QUERY = "select * from power where id = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new PowerMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(Power power) {

        final String QUERY = "update power set name = ? where id=?";

        jdbcTemplate.update(QUERY,
                power.getName(),

                power.getId()
        );

    }

    @Override
    public void delete(Power power) {

        final String QUERY = "delete from power where id=?";

        jdbcTemplate.update(QUERY, power.getId());

    }//

    @Override
    public List<Power> retrievePowersBySuper(Super superPerson, int limit, int offset) {
        final String QUERY = "select * from power p " +
                "inner join superpower sp on p.id = sp.power_id " +
                "where sp.super_id = ? " +
                "LIMIT ? OFFSET ?";;
        return jdbcTemplate.query(QUERY, new PowerMapper(),
                superPerson.getId(),
                limit,
                offset);
    }

    //MAPPER
    private class PowerMapper implements RowMapper<Power> {
        @Override
        public Power mapRow(ResultSet resultSet, int i) throws SQLException {
            Power power = new Power();

            power.setId(resultSet.getLong("id"));
            power.setName(resultSet.getString("name"));

            return power;
        }
    }
}
