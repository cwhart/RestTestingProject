package com.sg.hotelreservations.dao.daoInterface;

import com.sg.hotelreservations.dto.RoomBillDetail;

import java.util.List;

public interface RoomBillDetailDAO {

    public RoomBillDetail create(RoomBillDetail roomBillDetail);
    public RoomBillDetail retrieve(Long id);
    public void update(RoomBillDetail roomBillDetail);
    public void delete(RoomBillDetail roomBillDetail);
    public List<RoomBillDetail> retrieveAll(int limit, int offset);
}
