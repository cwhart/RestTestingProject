package com.sg.hotelreservations.dao.daoInterface;

import com.sg.hotelreservations.dto.RoomRate;

import java.time.LocalDate;
import java.util.List;

public interface RoomRateDAO {

    public RoomRate create(RoomRate roomRate);
    public RoomRate retrieve(Long id);
    public void update(RoomRate roomRate);
    public void delete(RoomRate roomRate);
    public List<RoomRate> retrieveAll(int limit, int offset);
    public List<RoomRate> retrieveByRoomId(Long roomId);
}
