package com.sg.hotelreservations.dao.daoimpl;

import com.sg.hotelreservations.dao.daoInterface.RoomDAO;
import com.sg.hotelreservations.dao.daoInterface.TaxDAO;
import com.sg.hotelreservations.dto.Room;
import com.sg.hotelreservations.dto.Tax;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class TaxDAOImpl implements TaxDAO {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public TaxDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Tax create(Tax tax) {

        final String QUERY = "insert into tax (type, startdate, enddate, rate ) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(QUERY,
                tax.getType(),
                tax.getStartDate().toString(),
                tax.getEndDate().toString(),
                tax.getRate()

        );

        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        tax.setId(insertedId);

        //Return team with id
        return tax;
    }

    @Override
    public Tax retrieve(Long id) {

        final String QUERY = "select * from tax where id = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new TaxMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(Tax tax) {

        final String QUERY = "update tax set type = ?, startdate = ?, enddate = ?, rate = ? where id=?";

        jdbcTemplate.update(QUERY,
                tax.getType(),
                tax.getStartDate().toString(),
                tax.getEndDate().toString(),
                tax.getRate(),
                tax.getId()
                );

    }

    @Override
    public void delete(Tax tax) {

        final String QUERY = "delete from tax where id=?";

        jdbcTemplate.update(QUERY, tax.getId());

    }

    @Override
    public List<Tax> retrieveAll(int limit, int offset) {

        final String QUERY = "select * from tax limit ? offset ?";

        List<Tax> returnList = jdbcTemplate.query(QUERY, new TaxMapper(), limit, offset);
        return returnList;
    }

    private class TaxMapper implements RowMapper<Tax> {
        @Override
        public Tax mapRow(ResultSet resultSet, int i) throws SQLException {

            Tax tax = new Tax();
            tax.setId(resultSet.getLong("id"));
            tax.setType(resultSet.getString("type"));
            tax.setStartDate(LocalDate.parse(resultSet.getString("startdate")));
            tax.setEndDate(LocalDate.parse(resultSet.getString("enddate")));
            tax.setRate(resultSet.getBigDecimal("rate"));
            return tax;

        }
    }
}
