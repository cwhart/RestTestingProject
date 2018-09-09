package com.sg.hotelreservations.dao.daoimpl;

import com.sg.hotelreservations.dao.daoInterface.GuestReservationDAO;
import com.sg.hotelreservations.dao.daoInterface.RoomAmenityDAO;
import com.sg.hotelreservations.dto.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GuestReservationDAOImpl implements GuestReservationDAO {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public GuestReservationDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public GuestReservation create(GuestReservation guestReservation) {

        final String QUERY = "insert into guestreservation (guestid, reservationid) VALUES (?, ?)";

        jdbcTemplate.update(QUERY,
                guestReservation.getGuest().getId(),
                guestReservation.getReservation().getId()

        );

//        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
//        roomAmenity.setId(insertedId);

        //Return team with id
        return guestReservation;
    }

    @Override
    public List<GuestReservation> retrieveByGuestId(Long guestId, int limit, int offset) {
        final String QUERY = "select * from guestreservation where guestid = ? limit ? offset ?";

        List<GuestReservation> returnList = jdbcTemplate.query(QUERY, new GuestReservationMapper(), guestId, limit, offset);
        return returnList;
    }

    @Override
    public List<GuestReservation> retrieveByReservationId(Long reservationId, int limit, int offset) {
        final String QUERY = "select * from guestreservation where reservationId = ? limit ? offset ?";

        List<GuestReservation> returnList = jdbcTemplate.query(QUERY, new GuestReservationMapper(), reservationId, limit, offset);
        return returnList;
    }

    @Override
    public void delete(GuestReservation guestReservation) {

        final String QUERY = "delete from guestreservation where guestid=? and reservationid = ?";

        jdbcTemplate.update(QUERY, guestReservation.getGuest().getId(),
                guestReservation.getReservation().getId());

    }


    private class GuestReservationMapper implements RowMapper<GuestReservation> {
        @Override
        public GuestReservation mapRow(ResultSet resultSet, int i) throws SQLException {

            GuestReservation guestReservation = new GuestReservation();
            Guest guest = new Guest();
            guest.setId(resultSet.getLong("guestid"));
            guestReservation.setGuest(guest);
            Reservation reservation = new Reservation();
            reservation.setId(resultSet.getLong("reservationid"));
            guestReservation.setReservation(reservation);
            return guestReservation;

        }
    }
}
