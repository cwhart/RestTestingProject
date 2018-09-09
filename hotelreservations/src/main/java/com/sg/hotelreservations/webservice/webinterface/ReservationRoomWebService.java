package com.sg.hotelreservations.webservice.webinterface;

import com.sg.hotelreservations.dto.ReservationRoom;
import com.sg.hotelreservations.viewmodels.reservationroom.list.ListReservationRoomViewModel;
import com.sg.hotelreservations.viewmodels.reservationroom.search.InputReservationDatesCommandModel;
import com.sg.hotelreservations.viewmodels.reservationroom.search.InputReservationDatesViewModel;

import java.time.LocalDate;

public interface ReservationRoomWebService {

    public ListReservationRoomViewModel getReservationRoomListViewModel(Integer offset, Integer numPersons,
                                                                        LocalDate start, LocalDate end);

    public InputReservationDatesViewModel getInputReservationDatesViewModel(LocalDate startDate, LocalDate endDate);

    //
    public void deleteReservation(Long id);
}
