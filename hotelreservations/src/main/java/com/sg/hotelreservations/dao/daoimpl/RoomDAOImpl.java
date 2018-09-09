package com.sg.hotelreservations.dao.daoimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnDAO;
import com.sg.hotelreservations.dao.daoInterface.RoomDAO;
import com.sg.hotelreservations.dto.AddOn;
import com.sg.hotelreservations.dto.Room;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public RoomDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Room create(Room room) {

        final String QUERY = "insert into room (floornumber, roomnumber, occupancy, roomtype ) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(QUERY,
                room.getFloorNumber(),
                room.getRoomNumber(),
                room.getOccupancy(),
                room.getType()

        );

        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        room.setId(insertedId);

        //Return team with id
        return room;
    }

    @Override
    public Room retrieve(Long id) {

        final String QUERY = "select * from room where id = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new RoomMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(Room room) {

        final String QUERY = "update room set floornumber = ?, roomnumber = ?, occupancy = ?, roomtype = ? where id=?";

        jdbcTemplate.update(QUERY,
                room.getFloorNumber(),
                room.getRoomNumber(),
                room.getOccupancy(),
                room.getType(),
                room.getId()
                );

    }

    @Override
    public void delete(Room room) {

        final String QUERY = "delete from room where id=?";

        jdbcTemplate.update(QUERY, room.getId());

    }

    @Override
    public List<Room> retrieveAll(int limit, int offset) {

        final String QUERY = "select * from room limit ? offset ?";

        List<Room> returnList = jdbcTemplate.query(QUERY, new RoomMapper(), limit, offset);
        return returnList;
    }

    private class RoomMapper implements RowMapper<Room> {
        @Override
        public Room mapRow(ResultSet resultSet, int i) throws SQLException {

            Room room = new Room();
            room.setId(resultSet.getLong("id"));
            room.setFloorNumber(resultSet.getInt("floornumber"));
            room.setRoomNumber(resultSet.getInt("roomnumber"));
            room.setOccupancy(resultSet.getInt("occupancy"));
            room.setType(resultSet.getString("roomtype"));
            return room;

        }
    }
}
