package com.sg.superhero.dao;

import com.sg.superhero.dao.interfaces.LocationDao;
import com.sg.superhero.dto.Location;
import com.sg.superhero.dto.Super;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LocationDaoImpl implements LocationDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public LocationDaoImpl (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Location create(Location location) {

        final String QUERY = "insert into location (name, description, street, city, state, zip, latitude, " +
                "longitude) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        //Get newly inserted id
        jdbcTemplate.update(QUERY,
                location.getName(),
                location.getDescription(),
                location.getStreet(),
                location.getCity(),
                location.getState(),
                location.getZip(),
                location.getLatitude(),
                location.getLongitude()

        );
        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        location.setId(insertedId);

        //Return team with id
        return location;

    }

    @Override
    public Location retrieve(Long id) {

        final String QUERY = "select * from location where id = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new LocationMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(Location location) {

        final String QUERY = "update location set name = ?, description = ?, street = ?, city = ?, " +
                "state = ?, zip = ?, latitude = ?, longitude = ? where id=?";

        jdbcTemplate.update(QUERY,
                location.getName(),
                location.getDescription(),
                location.getStreet(),
                location.getCity(),
                location.getState(),
                location.getZip(),
                location.getLongitude(),
                location.getLatitude(),
                location.getId()
        );

    }

    @Override
    public void delete(Location location) {

        final String QUERY = "delete from location where id=?";

        jdbcTemplate.update(QUERY, location.getId());

    }

    @Override
    public List<Location> retrieveAll(int limit, int offset) {
        final String QUERY = "select * from location limit ? offset ?";

        return jdbcTemplate.query(QUERY, new LocationMapper(), limit, offset);
    }

    @Override
    public List<Location> retrieveLocationBySuper(Super superPerson, int limit, int offset) {
        final String QUERY = "select * from location l " +
                "inner join sighting s on l.id = s.location_id " +
                "inner join supersighting ss on s.id = ss.sighting_id " +
                "inner join super on ss.super_id = super.id " +
                "where super.id = ? " +
                "LIMIT ? OFFSET ?";
        return jdbcTemplate.query(QUERY, new LocationMapper(),
                superPerson.getId(),
                limit,
                offset);
    }

    private class LocationMapper implements RowMapper<Location> {
        @Override
        public Location mapRow(ResultSet resultSet, int i) throws SQLException {

            Location location = new Location();
            location.setId(resultSet.getLong("id"));
            location.setName(resultSet.getString("name"));
            location.setDescription(resultSet.getString("description"));
            location.setStreet(resultSet.getString("street"));
            location.setCity(resultSet.getString("city"));
            location.setState(resultSet.getString("state"));
            location.setZip(resultSet.getString("zip"));
            location.setLatitude(resultSet.getLong("latitude"));
            location.setLongitude(resultSet.getLong("longitude"));
            return location;

        }
    }
}
