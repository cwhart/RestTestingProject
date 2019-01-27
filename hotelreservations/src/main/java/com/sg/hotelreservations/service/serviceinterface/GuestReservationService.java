package com.sg.hotelreservations.service.serviceinterface;

import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.GuestReservation;

import java.util.List;

public interface GuestReservationService {

    public GuestReservation create(GuestReservation guestReservation);
    public List<GuestReservation> retrieveByGuestId(Long guestId, int limit, int offset);
    public List<GuestReservation> retrieveByReservationId(Long reservationId);
    public void delete(GuestReservation guestReservation);
}
