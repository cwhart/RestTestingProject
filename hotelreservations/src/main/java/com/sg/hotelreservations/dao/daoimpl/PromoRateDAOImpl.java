package com.sg.hotelreservations.dao.daoimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnDAO;
import com.sg.hotelreservations.dao.daoInterface.PromoRateDAO;
import com.sg.hotelreservations.dto.AddOn;
import com.sg.hotelreservations.dto.PromoRate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PromoRateDAOImpl implements PromoRateDAO {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public PromoRateDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public PromoRate create(PromoRate promoRate) {

        final String QUERY = "insert into promorate (type, rate ) VALUES (?, ?)";

        //Get newly inserted id
        jdbcTemplate.update(QUERY,
                promoRate.getType(),
                promoRate.getRate());

        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        promoRate.setId(insertedId);

        //Return team with id
        return promoRate;
    }

    @Override
    public PromoRate retrieve(Long id) {

        final String QUERY = "select * from promorate where id = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new PromoRateMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(PromoRate promoRate) {

        final String QUERY = "update promorate set type = ?, rate = ? where id=?";

        jdbcTemplate.update(QUERY,
                promoRate.getType(),
                promoRate.getRate(),
                promoRate.getId()
        );

    }

    @Override
    public void delete(PromoRate promoRate) {

        final String QUERY = "delete from promorate where id=?";

        jdbcTemplate.update(QUERY, promoRate.getId());

    }

    @Override
    public List<PromoRate> retrieveAll(int limit, int offset) {

        final String QUERY = "select * from promorate limit ? offset ?";

        List<PromoRate> returnList = jdbcTemplate.query(QUERY, new PromoRateMapper(), limit, offset);
        return returnList;
    }

    private class PromoRateMapper implements RowMapper<PromoRate> {
        @Override
        public PromoRate mapRow(ResultSet resultSet, int i) throws SQLException {

            PromoRate promoRate = new PromoRate();
            promoRate.setId(resultSet.getLong("id"));
            promoRate.setRate(resultSet.getBigDecimal("rate"));
            promoRate.setType(resultSet.getString("type"));
            return promoRate;

        }
    }
}
