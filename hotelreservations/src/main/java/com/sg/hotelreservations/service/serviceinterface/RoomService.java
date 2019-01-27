package com.sg.hotelreservations.service.serviceinterface;

import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.Room;

import java.util.List;

public interface RoomService {

    public Room create(Room room);
    public Room retrieve(Long id);
    public void update(Room room);
    public void delete(Room room);
    public List<Room> retrieveAll(int limit, int offset);
    public Room retrieveByRoomNumber(int roomNumber);
}
