package com.sg.hotelreservations.dao.daoimpl;

import com.sg.hotelreservations.dao.daoInterface.AmenityDAO;
import com.sg.hotelreservations.dao.daoInterface.GuestDAO;
import com.sg.hotelreservations.dto.Amenity;
import com.sg.hotelreservations.dto.Guest;
import com.sg.hotelreservations.dto.Person;
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
public class GuestDAOImpl implements GuestDAO {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public GuestDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Guest create(Guest guest) {

        final String QUERY = "insert into guest (personid) VALUES (?)";

        jdbcTemplate.update(QUERY,
                guest.getPerson().getId()

        );

        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        guest.setId(insertedId);

        //Return team with id
        return guest;
    }

    @Override
    public Guest retrieve(Long id) {

        final String QUERY = "select * from guest where id = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new GuestMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(Guest guest) {

        final String QUERY = "update guest set personid = ? where id=?";

        jdbcTemplate.update(QUERY,
                guest.getPerson().getId(),
                guest.getId()
                );

    }

    @Override
    public void delete(Guest guest) {

        final String QUERY = "delete from guest where id=?";

        jdbcTemplate.update(QUERY, guest.getId());

    }


    @Override
    public List<Guest> retrieveAll(int limit, int offset) {

        final String QUERY = "select * from guest limit ? offset ?";

        List<Guest> returnList = jdbcTemplate.query(QUERY, new GuestMapper(), limit, offset);
        return returnList;
    }

    private class GuestMapper implements RowMapper<Guest> {
        @Override
        public Guest mapRow(ResultSet resultSet, int i) throws SQLException {

            Guest guest = new Guest();
            guest.setId(resultSet.getLong("id"));
            Person person = new Person();
            person.setId(resultSet.getLong("personid"));
            guest.setPerson(person);
            return guest;

        }
    }
}
