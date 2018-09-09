package com.sg.hotelreservations.dao.daoimpl;

import com.sg.hotelreservations.dao.daoInterface.BillDAO;
import com.sg.hotelreservations.dto.Bill;
import com.sg.hotelreservations.dto.Reservation;
import com.sg.hotelreservations.dto.Room;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BillDAOImpl implements BillDAO {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public BillDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Bill create(Bill bill) {

        final String QUERY = "insert into bill (reservationId, total ) VALUES (?, ?)";

        jdbcTemplate.update(QUERY,
                bill.getReservation().getId(),
                bill.getTotal()

        );

        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        bill.setId(insertedId);

        //Return team with id
        return bill;
    }

    @Override
    public Bill retrieve(Long id) {

        final String QUERY = "select * from bill where id = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new BillMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(Bill bill) {

        final String QUERY = "update bill set reservationid = ?, total = ? where id=?";

        jdbcTemplate.update(QUERY,
                bill.getReservation().getId(),
                bill.getTotal(),
                bill.getId()
                );

    }

    @Override
    public void delete(Bill bill) {

        final String QUERY = "delete from bill where id=?";

        jdbcTemplate.update(QUERY, bill.getId());

    }

    @Override
    public List<Bill> retrieveAll(int limit, int offset) {

        final String QUERY = "select * from bill limit ? offset ?";

        List<Bill> returnList = jdbcTemplate.query(QUERY, new BillMapper(), limit, offset);
        return returnList;
    }

    private class BillMapper implements RowMapper<Bill> {
        @Override
        public Bill mapRow(ResultSet resultSet, int i) throws SQLException {

            Bill bill = new Bill();
            bill.setId(resultSet.getLong("id"));
            Reservation reservation = new Reservation();
            reservation.setId(resultSet.getLong("reservationid"));
            bill.setReservation(reservation);
            bill.setTotal(resultSet.getBigDecimal("total"));
            return bill;

        }
    }
}
