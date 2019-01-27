package com.sg.hotelreservations.dao.daoInterface;

import com.sg.hotelreservations.dto.GuestReservation;

import java.util.List;

public interface GuestReservationDAO {

    public GuestReservation create(GuestReservation guestReservation);
    public List<GuestReservation> retrieveByGuestId(Long guestId, int limit, int offset);
    public List<GuestReservation> retrieveByReservationId(Long reservationId);
    public void delete(GuestReservation guestReservation);
}
