package com.sg.hotelreservations.dao.daoimpl;

import com.sg.hotelreservations.dao.daoInterface.RoomDAO;
import com.sg.hotelreservations.dao.daoInterface.RoomRateDAO;
import com.sg.hotelreservations.dto.Room;
import com.sg.hotelreservations.dto.RoomRate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomRateDAOImpl implements RoomRateDAO {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public RoomRateDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public RoomRate create(RoomRate roomRate) {

        final String QUERY = "insert into roomrate (roomid, defaultFlag, startdate, enddate, price ) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(QUERY,
                roomRate.getRoom().getId(),
                roomRate.getDefaultFlag(),
                roomRate.getStartDate().toString(),
                roomRate.getEndDate().toString(),
                roomRate.getPrice()

        );

        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        roomRate.setId(insertedId);

        //Return team with id
        return roomRate;
    }

    @Override
    public RoomRate retrieve(Long id) {

        final String QUERY = "select * from roomrate where id = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new RoomMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(RoomRate roomRate) {

        final String QUERY = "update roomrate set roomid = ?, defaultFlag = ?, startdate = ?, enddate = ?, price = ? where id=?";

        jdbcTemplate.update(QUERY,
                roomRate.getRoom().getId(),
                roomRate.getDefaultFlag(),
                roomRate.getStartDate().toString(),
                roomRate.getEndDate().toString(),
                roomRate.getPrice(),
                roomRate.getId()
                );

    }

    @Override
    public void delete(RoomRate roomRate) {

        final String QUERY = "delete from roomrate where id=?";

        jdbcTemplate.update(QUERY, roomRate.getId());

    }

    @Override
    public List<RoomRate> retrieveByRoomId(Long roomId ) {

        final String QUERY = "select * from roomrate where roomid = ? ";


        return jdbcTemplate.query(QUERY, new RoomMapper(), roomId);



    }

    @Override
    public List<RoomRate> retrieveAll(int limit, int offset) {

        final String QUERY = "select * from roomrate limit ? offset ?";

        List<RoomRate> returnList = jdbcTemplate.query(QUERY, new RoomMapper(), limit, offset);
        return returnList;
    }

    private class RoomMapper implements RowMapper<RoomRate> {
        @Override
        public RoomRate mapRow(ResultSet resultSet, int i) throws SQLException {

            RoomRate roomRate = new RoomRate();
            roomRate.setId(resultSet.getLong("id"));
            roomRate.setDefaultFlag(resultSet.getString("defaultFlag"));
            Room room = new Room();
            room.setId(resultSet.getLong("roomid"));
            roomRate.setRoom(room);
            roomRate.setStartDate(LocalDate.parse(resultSet.getString("startdate")));
            if (resultSet.getString("enddate") != null) {
                roomRate.setEndDate(LocalDate.parse(resultSet.getString("enddate")));
            }
            roomRate.setPrice(resultSet.getBigDecimal("price"));
            return roomRate;

        }
    }
}
