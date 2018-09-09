package com.sg.hotelreservations.service.serviceinterface;

import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.RoomAmenity;

import java.util.List;

public interface RoomAmenityService {

    public RoomAmenity create(RoomAmenity roomAmenity);
    public List<RoomAmenity> retrieveByRoomId(Long roomId, int limit, int offset);
    public List<RoomAmenity> retrieveByAmenityId(Long amenityId, int limit, int offset);
    public void delete(RoomAmenity roomAmenity);
}
