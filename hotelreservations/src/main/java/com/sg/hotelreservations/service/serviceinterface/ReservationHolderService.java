package com.sg.hotelreservations.service.serviceinterface;

import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.ReservationHolder;

import java.util.List;

public interface ReservationHolderService {

    public ReservationHolder create(ReservationHolder reservationHolder);
    public ReservationHolder retrieve(Long id);
    public void update(ReservationHolder reservationHolder);
    public void delete(ReservationHolder reservationHolder);
    public List<ReservationHolder> retrieveAll(int limit, int offset);
}
