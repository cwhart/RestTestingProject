package com.sg.hotelreservations.dao.daoimpl;

import com.sg.hotelreservations.dao.daoInterface.AmenityDAO;
import com.sg.hotelreservations.dao.daoInterface.RoomAmenityDAO;
import com.sg.hotelreservations.dto.Amenity;
import com.sg.hotelreservations.dto.Room;
import com.sg.hotelreservations.dto.RoomAmenity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class RoomAmenityDAOImpl implements RoomAmenityDAO {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public RoomAmenityDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public RoomAmenity create(RoomAmenity roomAmenity) {

        final String QUERY = "insert into roomamenity (amenityid, roomid) VALUES (?, ?)";

        jdbcTemplate.update(QUERY,
                roomAmenity.getAmenity().getId(),
                roomAmenity.getRoom().getId()

        );

//        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
//        roomAmenity.setId(insertedId);

        //Return team with id
        return roomAmenity;
    }

    @Override
    public List<RoomAmenity> retrieveByRoomId(Long roomId, int limit, int offset) {
        final String QUERY = "select * from roomamenity where roomid = ? limit ? offset ?";

        List<RoomAmenity> returnList = jdbcTemplate.query(QUERY, new RoomAmenityMapper(), roomId, limit, offset);
        return returnList;
    }

    @Override
    public List<RoomAmenity> retrieveByAmenityId(Long amenityId, int limit, int offset) {
        final String QUERY = "select * from roomamenity where amenityid = ? limit ? offset ?";

        List<RoomAmenity> returnList = jdbcTemplate.query(QUERY, new RoomAmenityMapper(), amenityId, limit, offset);
        return returnList;
    }

    @Override
    public void delete(RoomAmenity roomAmenity) {

        final String QUERY = "delete from roomamenity where roomid=? and amenityid = ?";

        jdbcTemplate.update(QUERY, roomAmenity.getRoom().getId(),
                roomAmenity.getAmenity().getId());

    }


    private class RoomAmenityMapper implements RowMapper<RoomAmenity> {
        @Override
        public RoomAmenity mapRow(ResultSet resultSet, int i) throws SQLException {

            RoomAmenity roomAmenity = new RoomAmenity();
            Amenity amenity = new Amenity();
            amenity.setId(resultSet.getLong("amenityid"));
            roomAmenity.setAmenity(amenity);
            Room room = new Room();
            room.setId(resultSet.getLong("roomid"));
            roomAmenity.setRoom(room);
            return roomAmenity;

        }
    }
}
