package com.sg.hotelreservations.webservice.webinterface;

import com.sg.hotelreservations.viewmodels.room.ListRoomViewModel;
import com.sg.hotelreservations.webservice.exception.InvalidDatesException;


import java.time.LocalDate;

public interface RoomWebService {

    public ListRoomViewModel getRoomListViewModel(Integer offset);

    public ListRoomViewModel getReservationRoomListViewModel(Integer offset, Integer numPersons,
                                    LocalDate start, LocalDate end) throws InvalidDatesException;
//
    public void deleteRoom (Long id);
}
