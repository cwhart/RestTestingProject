package com.sg.hotelreservations.webservice.webinterface;

import com.sg.hotelreservations.viewmodels.room.ListRoomViewModel;
import com.sg.hotelreservations.viewmodels.room.RoomViewModel;
import com.sg.hotelreservations.webservice.exception.InvalidDatesException;


import java.time.LocalDate;
import java.util.List;

public interface RoomWebService {

    public ListRoomViewModel getRoomListViewModel(Integer offset);

    public List<RoomViewModel> getReservationRoomListViewModel(Integer offset, Integer numPersons,
                                    LocalDate start, LocalDate end) throws InvalidDatesException;
//
    public void deleteRoom (Long id);

    public List<RoomViewModel> getListOfRooms();

    public RoomViewModel retrieveRoom(int id) throws Exception;
}
