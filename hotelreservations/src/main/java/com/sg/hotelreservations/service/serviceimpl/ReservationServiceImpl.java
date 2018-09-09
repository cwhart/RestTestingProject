package com.sg.hotelreservations.service.serviceimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoInterface.ReservationDAO;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.Reservation;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;
import com.sg.hotelreservations.service.serviceinterface.ReservationService;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    @Inject
    ReservationDAO reservationDAO;

    @Override
    public Reservation create(Reservation reservation) {
        return reservationDAO.create(reservation);
    }

    @Override
    public Reservation retrieve(Long id) {
        return reservationDAO.retrieve(id);
    }

    @Override
    public void update(Reservation reservation) {
        reservationDAO.update(reservation);
    }

    @Override
    public void delete(Reservation reservation) {
        reservationDAO.delete(reservation);
    }

//    @Override
//    public List<Reservation> retrieveAllInRange(LocalDate start, LocalDate end) {
//        //User inputs a date range; we want to display an additional 10 days before and after.
//        start = start.minusDays(10);
//        end = end.plusDays(10);
//        return reservationDAO.retrieveAllInRange(start, end);
//    }

    @Override
    public List<Reservation> retrieveAll(int limit, int offset) {
        return reservationDAO.retrieveAll(limit, offset);
    }
}
