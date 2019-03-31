package com.sg.hotelreservations.service.serviceimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoInterface.ReservationDAO;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.Reservation;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;
import com.sg.hotelreservations.service.serviceinterface.ReservationService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

@Service
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
    public Reservation update(Reservation reservation) {
        return reservationDAO.update(reservation);
    }

    @Override
    public void delete(Reservation reservation) {
        reservationDAO.delete(reservation);
    }

    @Override
    public List<Reservation> retrieveAll(int limit, int offset) {
        return reservationDAO.retrieveAll(limit, offset);
    }
}
