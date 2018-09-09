package com.sg.hotelreservations.service.serviceinterface;

import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.RoomRate;

import java.util.List;

public interface RoomRateService {

    public RoomRate create(RoomRate roomRate);
    public RoomRate retrieve(Long id);
    public void update(RoomRate roomRate);
    public void delete(RoomRate roomRate);
    public List<RoomRate> retrieveAll(int limit, int offset);
}
