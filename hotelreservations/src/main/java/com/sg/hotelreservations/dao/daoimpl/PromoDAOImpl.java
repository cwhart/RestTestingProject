package com.sg.hotelreservations.dao.daoimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnDAO;
import com.sg.hotelreservations.dao.daoInterface.PromoDAO;
import com.sg.hotelreservations.dto.AddOn;
import com.sg.hotelreservations.dto.Promo;
import com.sg.hotelreservations.dto.PromoType;
import com.sg.hotelreservations.service.serviceinterface.PromoTypeService;
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
public class PromoDAOImpl implements PromoDAO {

    private JdbcTemplate jdbcTemplate;
    PromoTypeService promoTypeService;

    @Inject
    public PromoDAOImpl(JdbcTemplate jdbcTemplate, PromoTypeService promoTypeService) {
        this.jdbcTemplate = jdbcTemplate;
        this.promoTypeService = promoTypeService;
    }

    @Override
    @Transactional
    public Promo create(Promo promo) {

        final String QUERY = "insert into promo (promotypeid, startdate, enddate ) VALUES (?, ?, ?)";

        //Get newly inserted id
        jdbcTemplate.update(QUERY,
                promo.getPromoType().getId(),
                promo.getStartDate().toString(),
                promo.getEndDate().toString());

        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        promo.setId(insertedId);

        //Return team with id
        return promo;
    }

    @Override
    public Promo retrieve(Long id) {

        final String QUERY = "select * from promo where id = ?";

        try{
            Promo returnedPromo =  jdbcTemplate.queryForObject(QUERY, new PromoMapper(), id);
            return returnedPromo;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(Promo promo) {

        final String QUERY = "update promo set promotypeid = ?, startdate = ?, enddate = ? where id=?";

        jdbcTemplate.update(QUERY,
                promo.getPromoType().getId(),
                promo.getStartDate().toString(),
                promo.getEndDate().toString(),
                promo.getId()
        );

    }

    @Override
    public void delete(Promo promo) {

        final String QUERY = "delete from promo where id=?";

        jdbcTemplate.update(QUERY, promo.getId());

    }

    @Override
    public List<Promo> retrieveAll(int limit, int offset) {

        final String QUERY = "select * from promo limit ? offset ?";

        List<Promo> returnList = jdbcTemplate.query(QUERY, new PromoMapper(), limit, offset);
        return returnList;
    }

    @Override
    public List<Promo> retrieveByPromoTypeId(Long id) {

        List<Promo> returnList = new ArrayList<>();
        final String QUERY = "select * from promo where promotypeid = ?";

        try{
            returnList = jdbcTemplate.query(QUERY, new PromoMapper(), id);
        } catch (EmptyResultDataAccessException ex) {

        }

        return returnList;
    }

    private class PromoMapper implements RowMapper<Promo> {
        @Override
        public Promo mapRow(ResultSet resultSet, int i) throws SQLException {

            Promo promo = new Promo();
            promo.setId(resultSet.getLong("id"));
            PromoType promoType = new PromoType();
            promoType = promoTypeService.retrieve(resultSet.getLong("promotypeid"));
            promo.setPromoType(promoType);
            promo.setStartDate(LocalDate.parse(resultSet.getString("startdate")));
            promo.setEndDate(LocalDate.parse(resultSet.getString("enddate")));
            return promo;

        }
    }
}
