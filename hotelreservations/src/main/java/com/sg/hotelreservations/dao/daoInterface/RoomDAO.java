package com.sg.hotelreservations.dao.daoInterface;

import com.sg.hotelreservations.dto.Room;

import java.util.List;

public interface RoomDAO {

    public Room create(Room room);
    public Room retrieve(Long id);
    public void update(Room room);
    public void delete(Room room);
    public Room retrieveByRoomNum(int roomNum);
    public List<Room> retrieveAll(int limit, int offset);
}
