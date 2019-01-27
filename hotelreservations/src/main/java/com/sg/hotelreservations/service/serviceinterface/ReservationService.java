package com.sg.hotelreservations.service.serviceinterface;

import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

    public Reservation create(Reservation reservation);
    public Reservation retrieve(Long id);
    public Reservation update(Reservation reservation);
    public void delete(Reservation reservation);
    //public List<Reservation> retrieveAllInRange(LocalDate start, LocalDate end);
    public List<Reservation> retrieveAll(int limit, int offset);
}
