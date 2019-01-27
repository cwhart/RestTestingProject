package com.sg.hotelreservations.dao.daoimpl;

import com.sg.hotelreservations.dao.daoInterface.PromoTypeDAO;
import com.sg.hotelreservations.dao.daoInterface.RoomDAO;
import com.sg.hotelreservations.dto.PromoRate;
import com.sg.hotelreservations.dto.PromoType;
import com.sg.hotelreservations.dto.Room;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PromoTypeDAOImpl implements PromoTypeDAO {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public PromoTypeDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public PromoType create(PromoType promoType) {

        final String QUERY = "insert into promotype (promorateid, promocode, description ) VALUES (?, ?, ?)";

        jdbcTemplate.update(QUERY,
                promoType.getPromoRate().getId(),
                promoType.getPromoCode(),
                promoType.getDescription()
        );

        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        promoType.setId(insertedId);

        //Return team with id
        return promoType;
    }

    @Override
    public PromoType retrieve(Long id) {

        final String QUERY = "select * from promotype where id = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new PromoTypeMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(PromoType promoType) {

        final String QUERY = "update promotype set promorateid = ?, promocode = ?, description = ? where id=?";

        jdbcTemplate.update(QUERY,
                promoType.getPromoRate().getId(),
                promoType.getPromoCode(),
                promoType.getDescription(),
                promoType.getId()
                );

    }

    @Override
    public void delete(PromoType promoType) {

        final String QUERY = "delete from promotype where id=?";

        jdbcTemplate.update(QUERY, promoType.getId());

    }

    @Override
    public List<PromoType> retrieveAll(int limit, int offset) {

        final String QUERY = "select * from promotype limit ? offset ?";

        List<PromoType> returnList = jdbcTemplate.query(QUERY, new PromoTypeMapper(), limit, offset);
        return returnList;
    }

    @Override
    public PromoType retrieveByPromoCode(String promoCode) {
        final String QUERY = "select * from promotype where promocode = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new PromoTypeMapper(), promoCode);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private class PromoTypeMapper implements RowMapper<PromoType> {
        @Override
        public PromoType mapRow(ResultSet resultSet, int i) throws SQLException {

            PromoType promoType = new PromoType();
            promoType.setId(resultSet.getLong("id"));
            PromoRate promoRate = new PromoRate();
            promoRate.setId(resultSet.getLong("promorateid"));
            promoType.setPromoRate(promoRate);
            promoType.setPromoCode(resultSet.getString("promocode"));
            promoType.setDescription(resultSet.getString("description"));
            return promoType;

        }
    }
}
