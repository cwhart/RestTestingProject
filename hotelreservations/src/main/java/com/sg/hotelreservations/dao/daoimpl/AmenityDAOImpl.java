package com.sg.hotelreservations.dao.daoimpl;

import com.sg.hotelreservations.dao.daoInterface.AmenityDAO;
import com.sg.hotelreservations.dao.daoInterface.RoomDAO;
import com.sg.hotelreservations.dto.Amenity;
import com.sg.hotelreservations.dto.Room;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AmenityDAOImpl implements AmenityDAO {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public AmenityDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Amenity create(Amenity amenity) {

        final String QUERY = "insert into amenity (type) VALUES (?)";

        jdbcTemplate.update(QUERY,
                amenity.getType()

        );

        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        amenity.setId(insertedId);

        //Return team with id
        return amenity;
    }

    @Override
    public Amenity retrieve(Long id) {

        final String QUERY = "select * from amenity where id = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new AmenityMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(Amenity amenity) {

        final String QUERY = "update amenity set type = ? where id=?";

        jdbcTemplate.update(QUERY,
                amenity.getType(),
                amenity.getId()
                );

    }

    @Override
    public void delete(Amenity room) {

        final String QUERY = "delete from amenity where id=?";

        jdbcTemplate.update(QUERY, room.getId());

    }




    @Override
    public List<Amenity> retrieveAll(int limit, int offset) {

        final String QUERY = "select * from amenity limit ? offset ?";

        List<Amenity> returnList = jdbcTemplate.query(QUERY, new AmenityMapper(), limit, offset);
        return returnList;
    }

    @Override
    public List<Amenity> retrieveAmenityByRoom(Long id) {

        final String QUERY = "select * FROM amenity inner join roomamenity on amenity.ID = roomamenity.AmenityID \n" +
                "inner join room on roomamenity.RoomID=room.ID where RoomId=?";
        List<Amenity> amenityList = jdbcTemplate.query(QUERY, new AmenityMapper(), id);

        return amenityList;
    }

    private class AmenityMapper implements RowMapper<Amenity> {
        @Override
        public Amenity mapRow(ResultSet resultSet, int i) throws SQLException {

            Amenity amenity = new Amenity();
            amenity.setId(resultSet.getLong("id"));
            amenity.setType(resultSet.getString("type"));
            return amenity;

        }
    }
}
