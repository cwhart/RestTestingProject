package com.sg.hotelreservations.dao.daoInterface;

import com.sg.hotelreservations.dto.ReservationRoom;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRoomDAO {

    public ReservationRoom create(ReservationRoom reservationRoom);
    public List<ReservationRoom> retrieveByReservationId(Long reservationId, int limit, int offset);
    public List<ReservationRoom> retrieveByRoomId(Long roomId, int limit, int offset);
    public List<ReservationRoom> retrieveByDates(LocalDate start, LocalDate end);
    public Boolean isBooked(Long roomId, LocalDate date);
    public void delete(ReservationRoom reservationRoom);

}
