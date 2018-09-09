package com.sg.hotelreservations.dao.daoInterface;

import com.sg.hotelreservations.dto.RoomAmenity;

import java.util.List;

public interface RoomAmenityDAO {

    public RoomAmenity create(RoomAmenity roomAmenity);
    public List<RoomAmenity> retrieveByRoomId(Long roomId, int limit, int offset);
    public List<RoomAmenity> retrieveByAmenityId(Long amenityId, int limit, int offset);
    public void delete(RoomAmenity roomAmenity);
}
