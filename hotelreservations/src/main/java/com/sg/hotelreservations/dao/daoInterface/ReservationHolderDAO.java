package com.sg.hotelreservations.dao.daoInterface;

import com.sg.hotelreservations.dto.ReservationHolder;

import java.util.List;

public interface ReservationHolderDAO {

    public ReservationHolder create(ReservationHolder reservationHolder);
    public ReservationHolder retrieve(Long id);
    public void update(ReservationHolder reservationHolder);
    public void delete(ReservationHolder reservationHolder);
    public List<ReservationHolder> retrieveAll(int limit, int offset);
}
