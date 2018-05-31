package com.sg.superhero.dao;

import com.sg.superhero.dao.interfaces.SuperSightingDao;
import com.sg.superhero.dto.Sighting;
import com.sg.superhero.dto.Super;
import com.sg.superhero.dto.SuperSighting;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SuperSightingDaoImpl implements SuperSightingDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public SuperSightingDaoImpl (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SuperSighting create(SuperSighting superSighting) {
        final String QUERY = "insert into supersighting (super_id, sighting_id ) VALUES (?, ?)";

        //Get newly inserted id
        jdbcTemplate.update(QUERY,
                superSighting.getSuperPerson().getId(),
                superSighting.getSighting().getId()

        );
        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        superSighting.setId(insertedId);

        //Return team with id
        return superSighting;
    }

    @Override
    public SuperSighting retrieve(long id) {
        final String QUERY = "select * from supersighting where id = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new SuperSightingMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void delete(SuperSighting superSighting) {

        final String QUERY = "delete from supersighting where id=?";

        jdbcTemplate.update(QUERY, superSighting.getId());

    }

    private class SuperSightingMapper implements RowMapper<SuperSighting> {
        @Override
        public SuperSighting mapRow(ResultSet resultSet, int i) throws SQLException {
            SuperSighting superSighting = new SuperSighting();

            superSighting.setId(resultSet.getLong("id"));
            //Lazy load superPerson
            Long superId = resultSet.getLong("super_id");
            if (superId > 0) {
                Super superPerson = new Super();
                superPerson.setId(superId);
                superSighting.setSuperPerson(superPerson);
            }

            //Lazy load sighting
            Long sightingId = resultSet.getLong("sighting_id");
            if (sightingId > 0) {
                Sighting sighting = new Sighting();
                sighting.setId(sightingId);
                superSighting.setSighting(sighting);
            }

            return superSighting;
        }
    }
}
