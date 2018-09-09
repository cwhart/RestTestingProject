package com.sg.hotelreservations.dao.daoimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
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

public class AddOnBillDetailDAOImpl implements AddOnBillDetailDAO {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public AddOnBillDetailDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public AddOnBillDetail create(AddOnBillDetail addOnBillDetail) {

        final String QUERY = "insert into addonbilldetail (taxid, promoid, addonrateid, billingid, taxamount, price, transactiondate ) VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(QUERY,
                addOnBillDetail.getTax().getId(),
                addOnBillDetail.getPromo().getId(),
                addOnBillDetail.getAddOnRate().getId(),
                addOnBillDetail.getBill().getId(),
                addOnBillDetail.getTaxAmount(),
                addOnBillDetail.getPrice(),
                addOnBillDetail.getTransactionDate().toString()

        );

        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        addOnBillDetail.setId(insertedId);

        //Return team with id
        return addOnBillDetail;
    }

    @Override
    public AddOnBillDetail retrieve(Long id) {

        final String QUERY = "select * from addonbilldetail where id = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new AddOnBillDetailMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(AddOnBillDetail addOnBillDetail) {

        final String QUERY = "update addonbilldetail set taxid = ?, promoid = ?, addonrateid = ?, billingid = ?, " +
                "taxamount = ?, price = ?, transactiondate = ? where id=?";

        jdbcTemplate.update(QUERY,
                addOnBillDetail.getTax().getId(),
                addOnBillDetail.getPromo().getId(),
                addOnBillDetail.getAddOnRate().getId(),
                addOnBillDetail.getBill().getId(),
                addOnBillDetail.getTaxAmount(),
                addOnBillDetail.getPrice(),
                addOnBillDetail.getTransactionDate().toString(),
                addOnBillDetail.getId()
        );
    }

    @Override
    public void delete(AddOnBillDetail addOnBillDetail) {

        final String QUERY = "delete from addonbilldetail where id=?";

        jdbcTemplate.update(QUERY, addOnBillDetail.getId());

    }

    @Override
    public List<AddOnBillDetail> retrieveAll(int limit, int offset) {

        final String QUERY = "select * from addonbilldetail limit ? offset ?";

        List<AddOnBillDetail> returnList = jdbcTemplate.query(QUERY, new AddOnBillDetailMapper(), limit, offset);
        return returnList;
    }

    private class AddOnBillDetailMapper implements RowMapper<AddOnBillDetail> {
        @Override
        public AddOnBillDetail mapRow(ResultSet resultSet, int i) throws SQLException {

            AddOnBillDetail addOnBillDetail = new AddOnBillDetail();
            addOnBillDetail.setId(resultSet.getLong("id"));
            Tax tax = new Tax();
            tax.setId(resultSet.getLong("taxid"));
            Promo promo = new Promo();
            promo.setId(resultSet.getLong("promoid"));
            AddOnRate addOnRate = new AddOnRate();
            addOnRate.setId(resultSet.getLong("addonrateid"));
            Bill bill = new Bill();
            bill.setId(resultSet.getLong("billingid"));
            addOnBillDetail.setTaxAmount(resultSet.getBigDecimal("taxamount"));
            addOnBillDetail.setPrice(resultSet.getBigDecimal("price"));
            addOnBillDetail.setTransactionDate(LocalDate.parse(resultSet.getString("transactiondate")));
            addOnBillDetail.setTax(tax);
            addOnBillDetail.setPromo(promo);
            addOnBillDetail.setAddOnRate(addOnRate);
            addOnBillDetail.setBill(bill);

            return addOnBillDetail;

        }
    }
}
