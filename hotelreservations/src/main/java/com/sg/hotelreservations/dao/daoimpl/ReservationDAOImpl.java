package com.sg.hotelreservations.dao.daoimpl;

import com.sg.hotelreservations.dao.daoInterface.PersonDAO;
import com.sg.hotelreservations.dao.daoInterface.ReservationDAO;
import com.sg.hotelreservations.dto.Person;
import com.sg.hotelreservations.dto.Promo;
import com.sg.hotelreservations.dto.Reservation;
import com.sg.hotelreservations.dto.ReservationHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public ReservationDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Reservation create(Reservation reservation) {

        final String QUERY = "insert into reservation (promoid, reservationholderid, startdate, enddate ) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(QUERY,
                reservation.getPromo().getId(),
                reservation.getReservationHolder().getId(),
                reservation.getStartDate().toString(),
                reservation.getEndDate().toString()

        );

        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        reservation.setId(insertedId);

        //Return team with id
        return reservation;
    }

    @Override
    public Reservation retrieve(Long id) {

        final String QUERY = "select * from reservation where id = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new ReservationMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(Reservation reservation) {

        final String QUERY = "update reservation set promoid = ?, reservationholderid = ?, startdate = ?, enddate = ? where id=?";

        jdbcTemplate.update(QUERY,
                reservation.getPromo().getId(),
                reservation.getReservationHolder().getId(),
                reservation.getStartDate().toString(),
                reservation.getEndDate().toString(),
                reservation.getId()
                );

    }

    @Override
    public void delete(Reservation reservation) {

        final String QUERY = "delete from reservation where id=?";

        jdbcTemplate.update(QUERY, reservation.getId());

    }

    @Override
    public List<Reservation> retrieveAll(int limit, int offset) {

        final String QUERY = "select * from reservation limit ? offset ?";

        List<Reservation> returnList = jdbcTemplate.query(QUERY, new ReservationMapper(), limit, offset);
        return returnList;
    }

//    @Override
//    public List<Reservation> retrieveAllInRange(LocalDate start, LocalDate end) {
//        //Retrieves all reservations that have at least one day overlapping with the specified dates.
//
//        final String QUERY = "SELECT * FROM reservation where startdate <= ? and enddate >= ?";
//
//        List<Reservation> returnList = jdbcTemplate.query(QUERY, new ReservationMapper(), end.toString(), start.toString());
//        return returnList;
//    }

    private class ReservationMapper implements RowMapper<Reservation> {
        @Override
        public Reservation mapRow(ResultSet resultSet, int i) throws SQLException {

            Reservation reservation = new Reservation();
            reservation.setId(resultSet.getLong("id"));
            Promo promo = new Promo();
            promo.setId(resultSet.getLong("promoid"));
            reservation.setPromo(promo);
            ReservationHolder reservationHolder = new ReservationHolder();
            reservationHolder.setId(resultSet.getLong("reservationholderid"));
            reservation.setReservationHolder(reservationHolder);
            reservation.setStartDate(LocalDate.parse(resultSet.getString("startdate")));
            reservation.setEndDate(LocalDate.parse(resultSet.getString("enddate")));
            return reservation;

        }
    }
}
