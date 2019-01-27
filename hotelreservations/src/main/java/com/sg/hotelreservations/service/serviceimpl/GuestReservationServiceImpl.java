package com.sg.hotelreservations.service.serviceimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoInterface.GuestDAO;
import com.sg.hotelreservations.dao.daoInterface.GuestReservationDAO;
import com.sg.hotelreservations.dao.daoInterface.PersonDAO;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.Guest;
import com.sg.hotelreservations.dto.GuestReservation;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;
import com.sg.hotelreservations.service.serviceinterface.GuestReservationService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class GuestReservationServiceImpl implements GuestReservationService {

    @Inject
    GuestReservationDAO guestReservationDAO;

    @Inject
    GuestDAO guestDAO;

    @Override
    public GuestReservation create(GuestReservation guestReservation) {
        return guestReservationDAO.create(guestReservation);
    }

    @Override
    public List<GuestReservation> retrieveByGuestId(Long guestId, int limit, int offset) {
        List<GuestReservation> guestReservations = guestReservationDAO.retrieveByGuestId(guestId, limit, offset);


        return guestReservations;
    }

    @Override
    public List<GuestReservation> retrieveByReservationId(Long reservationId) {
        List<GuestReservation> guestReservations = guestReservationDAO.retrieveByReservationId(reservationId);

        for (GuestReservation currentGuestRes : guestReservations) {
            //Guest guest = guestDAO.retrieve(currentGuestRes.getGuest().getId());
            currentGuestRes.setGuest(guestDAO.retrieve(currentGuestRes.getGuest().getId()));
        }
        return guestReservations;
    }

    @Override
    public void delete(GuestReservation guestReservation) {

        guestReservationDAO.delete(guestReservation);

    }
}
