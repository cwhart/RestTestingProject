package com.sg.hotelreservations.dao.daoimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoInterface.RoomBillDetailDAO;
import com.sg.hotelreservations.dto.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class RoomBillDetailDAOImpl implements RoomBillDetailDAO {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public RoomBillDetailDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public RoomBillDetail create(RoomBillDetail roomBillDetail) {

        final String QUERY = "insert into roombilldetail (taxid, promoid, roomrateid, billingid, taxamount, price, transactiondate ) VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(QUERY,
                roomBillDetail.getTax().getId(),
                roomBillDetail.getPromo().getId(),
                roomBillDetail.getRoomRate().getId(),
                roomBillDetail.getBill().getId(),
                roomBillDetail.getTaxAmount(),
                roomBillDetail.getPrice(),
                roomBillDetail.getTransactionDate().toString()

        );

        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        roomBillDetail.setId(insertedId);

        //Return team with id
        return roomBillDetail;
    }

    @Override
    public RoomBillDetail retrieve(Long id) {

        final String QUERY = "select * from roombilldetail where id = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new RoomBillDetailMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(RoomBillDetail roomBillDetail) {

        final String QUERY = "update roombilldetail set taxid = ?, promoid = ?, roomrateid = ?, billingid = ?, " +
                "taxamount = ?, price = ?, transactiondate = ? where id=?";

        jdbcTemplate.update(QUERY,
                roomBillDetail.getTax().getId(),
                roomBillDetail.getPromo().getId(),
                roomBillDetail.getRoomRate().getId(),
                roomBillDetail.getBill().getId(),
                roomBillDetail.getTaxAmount(),
                roomBillDetail.getPrice(),
                roomBillDetail.getTransactionDate().toString(),
                roomBillDetail.getId()
        );
    }

    @Override
    public void delete(RoomBillDetail roomBillDetail) {

        final String QUERY = "delete from roombilldetail where id=?";

        jdbcTemplate.update(QUERY, roomBillDetail.getId());

    }

    @Override
    public List<RoomBillDetail> retrieveAll(int limit, int offset) {

        final String QUERY = "select * from roombilldetail limit ? offset ?";

        List<RoomBillDetail> returnList = jdbcTemplate.query(QUERY, new RoomBillDetailMapper(), limit, offset);
        return returnList;
    }

    private class RoomBillDetailMapper implements RowMapper<RoomBillDetail> {
        @Override
        public RoomBillDetail mapRow(ResultSet resultSet, int i) throws SQLException {

            RoomBillDetail roomBillDetail = new RoomBillDetail();
            roomBillDetail.setId(resultSet.getLong("id"));
            Tax tax = new Tax();
            tax.setId(resultSet.getLong("taxid"));
            Promo promo = new Promo();
            promo.setId(resultSet.getLong("promoid"));
            RoomRate roomRate = new RoomRate();
            roomRate.setId(resultSet.getLong("roomrateid"));
            Bill bill = new Bill();
            bill.setId(resultSet.getLong("billingid"));
            roomBillDetail.setTaxAmount(resultSet.getBigDecimal("taxamount"));
            roomBillDetail.setPrice(resultSet.getBigDecimal("price"));
            roomBillDetail.setTransactionDate(LocalDate.parse(resultSet.getString("transactiondate")));
            roomBillDetail.setTax(tax);
            roomBillDetail.setPromo(promo);
            roomBillDetail.setRoomRate(roomRate);
            roomBillDetail.setBill(bill);

            return roomBillDetail;

        }
    }
}
