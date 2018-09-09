package com.sg.hotelreservations.service.serviceimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoInterface.RoomBillDetailDAO;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.RoomBillDetail;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;
import com.sg.hotelreservations.service.serviceinterface.RoomBillDetailService;

import javax.inject.Inject;
import java.util.List;

public class RoomBillDetailServiceImpl implements RoomBillDetailService {

    @Inject
    RoomBillDetailDAO roomBillDetailDAO;

    @Override
    public RoomBillDetail create(RoomBillDetail roomBillDetail) {
        return roomBillDetailDAO.create(roomBillDetail);
    }

    @Override
    public RoomBillDetail retrieve(Long id) {
        return roomBillDetailDAO.retrieve(id);
    }

    @Override
    public void update(RoomBillDetail roomBillDetail) {
        roomBillDetailDAO.update(roomBillDetail);
    }

    @Override
    public void delete(RoomBillDetail roomBillDetail) {
        roomBillDetailDAO.delete(roomBillDetail);
    }

    @Override
    public List<RoomBillDetail> retrieveAll(int limit, int offset) {
        return roomBillDetailDAO.retrieveAll(limit, offset);
    }
}
