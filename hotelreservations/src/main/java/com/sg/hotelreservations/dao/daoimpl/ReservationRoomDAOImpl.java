package com.sg.hotelreservations.dao.daoimpl;

import com.sg.hotelreservations.dao.daoInterface.GuestReservationDAO;
import com.sg.hotelreservations.dao.daoInterface.ReservationDAO;
import com.sg.hotelreservations.dao.daoInterface.ReservationRoomDAO;
import com.sg.hotelreservations.dao.daoInterface.RoomDAO;
import com.sg.hotelreservations.dto.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationRoomDAOImpl implements ReservationRoomDAO {

    private JdbcTemplate jdbcTemplate;
    private ReservationDAO reservationDAO;
    private RoomDAO roomDAO;

    @Inject
    public ReservationRoomDAOImpl(JdbcTemplate jdbcTemplate, ReservationDAO reservationDAO, RoomDAO roomDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.reservationDAO = reservationDAO;
        this.roomDAO = roomDAO;
    }

    @Override
    @Transactional
    public ReservationRoom create(ReservationRoom reservationRoom) {

        final String QUERY = "insert into reservationroom (reservationid, roomid) VALUES (?, ?)";

        jdbcTemplate.update(QUERY,
                reservationRoom.getReservation().getId(),
                reservationRoom.getRoom().getId()

        );

//        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
//        roomAmenity.setId(insertedId);

        //Return team with id
        return reservationRoom;
    }

    @Override
    public List<ReservationRoom> retrieveByReservationId(Long reservationId, int limit, int offset) {
        final String QUERY = "select * from reservationroom where reservationid = ? limit ? offset ?";

        List<ReservationRoom> returnList = jdbcTemplate.query(QUERY, new ReservationRoomMapper(), reservationId, limit, offset);
        return returnList;
    }

    @Override
    public List<ReservationRoom> retrieveByRoomId(Long roomId, int limit, int offset) {
        final String QUERY = "select * from reservationroom where roomid = ? limit ? offset ?";

        List<ReservationRoom> returnList = jdbcTemplate.query(QUERY, new ReservationRoomMapper(), roomId, limit, offset);
        return returnList;
    }

    @Override
    public void delete(ReservationRoom reservationRoom) {

        final String QUERY = "delete from reservationroom where reservationid=? and roomid = ?";

        jdbcTemplate.update(QUERY, reservationRoom.getReservation().getId(),
                reservationRoom.getRoom().getId());

    }

    @Override
    public List<ReservationRoom> retrieveByDates(LocalDate start, LocalDate end) {

        final String QUERY = "select * from reservationroom rr " +
                "left outer join reservation re on re.id = rr.reservationid  \n" +
                "where startdate <= ? and enddate >= ?";

        List<ReservationRoom> returnList = jdbcTemplate.query(QUERY, new ReservationRoomMapper(),
                end.toString(), start.toString());

        return returnList;
    }

    @Override
    public Boolean isBooked(Long roomId, LocalDate date) {

        final String QUERY = "select * from reservationroom rr join reservation re on re.id = rr.reservationid join room r on r.id = rr.RoomID\n" +
                "where r.id = ? and re.StartDate <= ? and re.EndDate >= ?";

        List<ReservationRoom> returnList = jdbcTemplate.query(QUERY, new ReservationRoomMapper(), roomId, date, date);
        if (returnList.size()==0) return false;
        else return true;

    }

    private class ReservationRoomMapper implements RowMapper<ReservationRoom> {
        @Override
        public ReservationRoom mapRow(ResultSet resultSet, int i) throws SQLException {
            ReservationRoom reservationRoom = new ReservationRoom();

            //reservationRoom.setReservation(reservationDAO.retrieve(resultSet.getLong("id")));
            Long reservationId = resultSet.getLong("reservationid");
            if (reservationId > 0) {
                Reservation reservation = new Reservation();
                reservation.setId(reservationId);
                reservation.setStartDate(reservationDAO.retrieve(reservationId).getStartDate());
                reservation.setEndDate(reservationDAO.retrieve(reservationId).getEndDate());
                reservationRoom.setReservation(reservation);
            }

            Long roomId = resultSet.getLong("roomid");
            if (roomId > 0) {
                Room room = new Room();
                room.setId(roomId);
                room.setType(roomDAO.retrieve(roomId).getType());
                room.setRoomNumber(roomDAO.retrieve(roomId).getRoomNumber());
                reservationRoom.setRoom(room);
            }

            return reservationRoom;
        }
    }
}
