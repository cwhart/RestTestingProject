package com.sg.hotelreservations.dao.daoInterface;

import com.sg.hotelreservations.dto.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDAO {

    public Reservation create(Reservation reservation);
    public Reservation retrieve(Long id);
    public void update(Reservation reservation);
    public void delete(Reservation reservation);
    //public List<Reservation> retrieveAllInRange(LocalDate start, LocalDate end);
    public List<Reservation> retrieveAll(int limit, int offset);
}
