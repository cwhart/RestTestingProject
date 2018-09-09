package com.sg.hotelreservations.service.serviceimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoInterface.RoomRateDAO;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.RoomRate;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;
import com.sg.hotelreservations.service.serviceinterface.RoomRateService;

import javax.inject.Inject;
import java.util.List;

public class RoomRateServiceImpl implements RoomRateService {

    @Inject
    RoomRateDAO roomRateDAO;

    @Override
    public RoomRate create(RoomRate roomRate) {
        return roomRateDAO.create(roomRate);
    }

    @Override
    public RoomRate retrieve(Long id) {
        return roomRateDAO.retrieve(id);
    }

    @Override
    public void update(RoomRate roomRate) {
        roomRateDAO.update(roomRate);
    }

    @Override
    public void delete(RoomRate roomRate) {
        roomRateDAO.delete(roomRate);
    }

    @Override
    public List<RoomRate> retrieveAll(int limit, int offset) {
        return roomRateDAO.retrieveAll(limit, offset);
    }
}
