package com.sg.superhero.dao;

import com.sg.superhero.dao.interfaces.SuperPowerDao;
import com.sg.superhero.dto.Power;
import com.sg.superhero.dto.Super;
import com.sg.superhero.dto.SuperPower;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SuperPowerDaoImpl implements SuperPowerDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public SuperPowerDaoImpl (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public SuperPower create(SuperPower superPower) {
        final String QUERY = "insert into superpower (super_id, power_id ) VALUES (?, ?)";

        //Get newly inserted id
        jdbcTemplate.update(QUERY,
                superPower.getSuperPerson().getId(),
                superPower.getPower().getId()

        );
        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        superPower.setId(insertedId);

        //Return team with id
        return superPower;
    }

    @Override
    public SuperPower retrieve(long id) {
        final String QUERY = "select * from superpower where id = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new SuperPowerMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void delete(SuperPower superPower) {

        final String QUERY = "delete from superpower where id=?";

        jdbcTemplate.update(QUERY, superPower.getId());

    }//

    private class SuperPowerMapper implements RowMapper<SuperPower> {
        @Override
        public SuperPower mapRow(ResultSet resultSet, int i) throws SQLException {
            SuperPower superPower = new SuperPower();

            superPower.setId(resultSet.getLong("id"));
            //Lazy load superPerson
            Long superId = resultSet.getLong("super_id");
            if (superId > 0) {
                Super superPerson = new Super();
                superPerson.setId(superId);
                superPower.setSuperPerson(superPerson);
            }

            //Lazy load power
            Long powerId = resultSet.getLong("power_id");
            if (powerId > 0) {
                Power power = new Power();
                power.setId(powerId);
                superPower.setPower(power);
            }

            return superPower;
        }
    }
}
