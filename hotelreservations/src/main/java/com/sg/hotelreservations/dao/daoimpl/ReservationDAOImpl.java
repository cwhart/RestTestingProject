package com.sg.hotelreservations.dao.daoimpl;

import com.sg.hotelreservations.constants.HotelReservationConstants;
import com.sg.hotelreservations.dao.daoInterface.PersonDAO;
import com.sg.hotelreservations.dao.daoInterface.ReservationDAO;
import com.sg.hotelreservations.dto.Person;
import com.sg.hotelreservations.dto.Promo;
import com.sg.hotelreservations.dto.Reservation;
import com.sg.hotelreservations.dto.ReservationHolder;
import com.sg.hotelreservations.service.serviceinterface.PersonService;
import com.sg.hotelreservations.service.serviceinterface.PromoService;
import com.sg.hotelreservations.service.serviceinterface.ReservationHolderService;
import com.sg.hotelreservations.service.serviceinterface.ReservationService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class ReservationDAOImpl implements ReservationDAO {

    private JdbcTemplate jdbcTemplate;
    private PromoService promoService;
    private ReservationHolderService reservationHolderService;
    private PersonService personService;

    @Inject
    public ReservationDAOImpl(JdbcTemplate jdbcTemplate, PromoService promoService, ReservationHolderService reservationHolderService,
                              PersonService personService) {
        this.jdbcTemplate = jdbcTemplate;
        this.promoService = promoService;
        this.reservationHolderService = reservationHolderService;
        this.personService = personService;
    }

    @Override
    @Transactional
    public Reservation create(Reservation reservation) {

        if(reservation.getPromo() == null) {
            Promo promo = new Promo();
            reservation.setPromo(promo);
        }

        final String QUERY = "insert into reservation (promoid, reservationholderid, startdate, enddate ) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(QUERY,
                reservation.getPromo().getId(),
                reservation.getReservationHolder().getId(),
                reservation.getStartDate().toString(),
                reservation.getEndDate().toString()

        );

        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        reservation.setId(insertedId);

        //Return team with id
        return reservation;
    }

    @Override
    public Reservation retrieve(Long id) {

        final String QUERY = "select * from reservation where id = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new ReservationMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Reservation update(Reservation reservation) {

        final String QUERY = "update reservation set promoid = ?, reservationholderid = ?, startdate = ?, enddate = ? where id=?";

        jdbcTemplate.update(QUERY,
                reservation.getPromo().getId(),
                reservation.getReservationHolder().getId(),
                reservation.getStartDate().toString(),
                reservation.getEndDate().toString(),
                reservation.getId()
                );

        return reservation;
    }

    @Override
    public void delete(Reservation reservation) {

        final String QUERY = "delete from reservation where id=?";

        jdbcTemplate.update(QUERY, reservation.getId());

    }

    @Override
    public List<Reservation> retrieveAll(int limit, int offset) {

        final String QUERY = "select * from reservation limit ? offset ?";

        List<Reservation> returnList = jdbcTemplate.query(QUERY, new ReservationMapper(), limit, offset);
        return returnList;
    }



    private class ReservationMapper implements RowMapper<Reservation> {
        @Override
        public Reservation mapRow(ResultSet resultSet, int i) throws SQLException {

            Reservation reservation = new Reservation();
            reservation.setId(resultSet.getLong("id"));

            reservation.setStartDate(LocalDate.parse(resultSet.getString("startdate")));
            reservation.setEndDate(LocalDate.parse(resultSet.getString("enddate")));

            Long reservationHolderId = resultSet.getLong("reservationHolderId");

            if(reservationHolderId >0) {
                ReservationHolder reservationHolder = reservationHolderService.retrieve(reservationHolderId);

                reservation.setReservationHolder(reservationHolder);
                Long personId = reservationHolder.getPerson().getId();
                Person person = personService.retrieve(personId);
                reservationHolder.setPerson(person);
            }

            Long promoId = resultSet.getLong("promoId");

            if(promoId >0) {
                Promo promo = promoService.retrieve(promoId);

                reservation.setPromo(promo);
            }


            return reservation;

        }
    }
}
