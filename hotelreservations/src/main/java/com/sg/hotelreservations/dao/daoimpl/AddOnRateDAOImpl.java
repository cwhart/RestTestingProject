package com.sg.hotelreservations.dao.daoimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnRateDAO;
import com.sg.hotelreservations.dao.daoInterface.RoomRateDAO;
import com.sg.hotelreservations.dto.AddOn;
import com.sg.hotelreservations.dto.AddOnRate;
import com.sg.hotelreservations.dto.Room;
import com.sg.hotelreservations.dto.RoomRate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class AddOnRateDAOImpl implements AddOnRateDAO {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public AddOnRateDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public AddOnRate create(AddOnRate addOnRate) {

        final String QUERY = "insert into addonrate (addonid, startdate, enddate, price ) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(QUERY,
                addOnRate.getAddOn().getId(),
                addOnRate.getStartDate().toString(),
                addOnRate.getEndDate().toString(),
                addOnRate.getPrice()

        );

        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        addOnRate.setId(insertedId);

        //Return team with id
        return addOnRate;
    }

    @Override
    public AddOnRate retrieve(Long id) {

        final String QUERY = "select * from addonrate where id = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new AddOnRateMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(AddOnRate addOnRate) {

        final String QUERY = "update addonrate set addonid = ?, startdate = ?, enddate = ?, price = ? where id=?";

        jdbcTemplate.update(QUERY,
                addOnRate.getAddOn().getId(),
                addOnRate.getStartDate().toString(),
                addOnRate.getEndDate().toString(),
                addOnRate.getPrice(),
                addOnRate.getId()
                );

    }

    @Override
    public void delete(AddOnRate addOnRate) {

        final String QUERY = "delete from addonrate where id=?";

        jdbcTemplate.update(QUERY, addOnRate.getId());

    }

    @Override
    public List<AddOnRate> retrieveAll(int limit, int offset) {

        final String QUERY = "select * from addonrate limit ? offset ?";

        List<AddOnRate> returnList = jdbcTemplate.query(QUERY, new AddOnRateMapper(), limit, offset);
        return returnList;
    }

    private class AddOnRateMapper implements RowMapper<AddOnRate> {
        @Override
        public AddOnRate mapRow(ResultSet resultSet, int i) throws SQLException {

            AddOnRate addOnRate = new AddOnRate();
            addOnRate.setId(resultSet.getLong("id"));
            AddOn addOn = new AddOn();
            addOn.setId(resultSet.getLong("addonid"));
            addOnRate.setAddOn(addOn);
            addOnRate.setStartDate(LocalDate.parse(resultSet.getString("startdate")));
            addOnRate.setEndDate(LocalDate.parse(resultSet.getString("enddate")));
            addOnRate.setPrice(resultSet.getBigDecimal("price"));
            return addOnRate;

        }
    }
}
