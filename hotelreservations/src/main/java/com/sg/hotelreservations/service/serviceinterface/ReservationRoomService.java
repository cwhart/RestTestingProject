package com.sg.hotelreservations.service.serviceinterface;

import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.Reservation;
import com.sg.hotelreservations.dto.ReservationRoom;
import com.sg.hotelreservations.dto.Room;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ReservationRoomService {

    public ReservationRoom create(ReservationRoom reservationRoom);
    public List<ReservationRoom> retrieveByReservationId(Reservation reservation);
    public List<ReservationRoom> retrieveByRoomId(Long roomId, int limit, int offset);
    public List<ReservationRoom> retrieveByDates(LocalDate start, LocalDate end);
    public void delete(ReservationRoom reservationRoom);
    public Boolean isBooked(Long roomId, LocalDate date);
    //public HashMap<Room, HashMap<LocalDate, Boolean>> calculateBookingMaps(List<ReservationRoom> reservationRoomList, LocalDate start, LocalDate end) ;
    public Boolean isBookedForDateRange (int roomId, LocalDate start, LocalDate end);

    }
