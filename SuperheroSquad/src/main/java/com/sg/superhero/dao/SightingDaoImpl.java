package com.sg.superhero.dao;

import com.sg.superhero.dao.interfaces.SightingDao;
import com.sg.superhero.dto.Location;
import com.sg.superhero.dto.Power;
import com.sg.superhero.dto.Sighting;
import com.sg.superhero.dto.Super;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.inject.Inject;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class SightingDaoImpl implements SightingDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public SightingDaoImpl (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Sighting create(Sighting sighting) {
        final String QUERY = "insert into sighting (location_id, date ) VALUES (?, ?)";

        //Get newly inserted id
        jdbcTemplate.update(QUERY,
                sighting.getLocation().getId(),
                //sighting.getDate().toString()
                Date.valueOf(sighting.getDate())

        );
        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        sighting.setId(insertedId);

        //Return team with id
        return sighting;
    }

    @Override
    public Sighting retrieve(Long id) {

        final String QUERY = "select * from sighting where id = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new SightingMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    @Override
    public void update(Sighting sighting) {

        final String QUERY = "update sighting set location_id = ?, date = ? where id=?";

        jdbcTemplate.update(QUERY,
                sighting.getLocation().getId(),
                sighting.getDate().toString(),
                //java.util.Date.from(sighting.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                sighting.getId()

        );

    }

    @Override
    public void delete(Sighting sighting) {

        final String QUERY = "delete from sighting where id=?";

        jdbcTemplate.update(QUERY, sighting.getId());

    }

    @Override
    public List<Sighting> retrieveAll(int limit, int offset) {
        final String QUERY = "select * from sighting limit ? offset ?";

        return jdbcTemplate.query(QUERY, new SightingMapper(), limit, offset);
    }//

    @Override
    public List<Sighting> retrieveSightingsBySuper(Super superPerson, int limit, int offset) {
        final String QUERY = "select * from sighting s " +
                "inner join supersighting ss on s.id = ss.sighting_id " +
                "where ss.super_id = ? " +
                "LIMIT ? OFFSET ?";
        return jdbcTemplate.query(QUERY, new SightingMapper(),
                superPerson.getId(),
                limit,
                offset);
    }

    @Override
    public List<Sighting> retrieveSightingsByLocationAndSuperAndDate(Location location, Super superPerson,
                                                                     LocalDate localDate, int limit, int offset) {
        final String QUERY = "select * from sighting s " +
                "inner join supersighting ss on s.id = ss.sighting_id " +
                "where ss.super_id = ? " +
                "and s.location_id = ? " +
                "and s.date = ?" +
                "LIMIT ? OFFSET ?";
        return jdbcTemplate.query(QUERY, new SightingMapper(),
                superPerson.getId(),
                location.getId(),
                Date.valueOf(localDate),
                limit,
                offset);
    }

    @Override
    public List<Sighting> retrieveSightingByLocation(Location location, int limit, int offset) {

            final String QUERY = "select * from sighting s " +
                    "where s.location_id = ? LIMIT ? OFFSET ?";
            return jdbcTemplate.query(QUERY, new SightingMapper(), location.getId(), limit, offset);
        }


    private class SightingMapper implements RowMapper<Sighting> {
        @Override
        public Sighting mapRow(ResultSet resultSet, int i) throws SQLException {
            Sighting sighting = new Sighting();

            sighting.setId(resultSet.getLong("id"));
            sighting.setDate(resultSet.getTimestamp("date").toLocalDateTime().toLocalDate());
            //Lazy load sighting
            Long locationId = resultSet.getLong("location_id");
            if (locationId > 0) {
                Location location = new Location();
                location.setId(locationId);
                sighting.setLocation(location);
            }
            return sighting;
        }
    }
}
