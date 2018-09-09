package com.sg.hotelreservations.dao.daoimpl;

import com.sg.hotelreservations.dao.daoInterface.ReservationHolderDAO;
import com.sg.hotelreservations.dto.Person;
import com.sg.hotelreservations.dto.ReservationHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReservationHolderDAOImpl implements ReservationHolderDAO {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public ReservationHolderDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public ReservationHolder create(ReservationHolder reservationHolder) {

        final String QUERY = "insert into reservationholder (personid) VALUES (?)";

        jdbcTemplate.update(QUERY,
                reservationHolder.getPerson().getId()

        );

        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        reservationHolder.setId(insertedId);

        //Return team with id
        return reservationHolder;
    }

    @Override
    public ReservationHolder retrieve(Long id) {

        final String QUERY = "select * from reservationholder where id = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new ReservationHolderMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(ReservationHolder reservationHolder) {

        final String QUERY = "update reservationholder set personid = ? where id=?";

        jdbcTemplate.update(QUERY,
                reservationHolder.getPerson().getId(),
                reservationHolder.getId()
                );

    }

    @Override
    public void delete(ReservationHolder reservationHolder) {

        final String QUERY = "delete from reservationholder where id=?";

        jdbcTemplate.update(QUERY, reservationHolder.getId());

    }


    @Override
    public List<ReservationHolder> retrieveAll(int limit, int offset) {

        final String QUERY = "select * from reservationholder limit ? offset ?";

        List<ReservationHolder> returnList = jdbcTemplate.query(QUERY, new ReservationHolderMapper(), limit, offset);
        return returnList;
    }

    private class ReservationHolderMapper implements RowMapper<ReservationHolder> {
        @Override
        public ReservationHolder mapRow(ResultSet resultSet, int i) throws SQLException {

            ReservationHolder reservationHolder = new ReservationHolder();
            reservationHolder.setId(resultSet.getLong("id"));
            Person person = new Person();
            person.setId(resultSet.getLong("personid"));
            reservationHolder.setPerson(person);
            return reservationHolder;

        }
    }
}
