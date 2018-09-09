package com.sg.hotelreservations.service.serviceimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoInterface.GuestReservationDAO;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.GuestReservation;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;
import com.sg.hotelreservations.service.serviceinterface.GuestReservationService;

import javax.inject.Inject;
import java.util.List;

public class GuestReservationServiceImpl implements GuestReservationService {

    @Inject
    GuestReservationDAO guestReservationDAO;

    @Override
    public GuestReservation create(GuestReservation guestReservation) {
        return guestReservationDAO.create(guestReservation);
    }

    @Override
    public List<GuestReservation> retrieveByGuestId(Long guestId, int limit, int offset) {
        return guestReservationDAO.retrieveByGuestId(guestId, limit, offset);
    }

    @Override
    public List<GuestReservation> retrieveByReservationId(Long reservationId, int limit, int offset) {
        return guestReservationDAO.retrieveByReservationId(reservationId, limit, offset);
    }

    @Override
    public void delete(GuestReservation guestReservation) {

        guestReservationDAO.delete(guestReservation);

    }
}
