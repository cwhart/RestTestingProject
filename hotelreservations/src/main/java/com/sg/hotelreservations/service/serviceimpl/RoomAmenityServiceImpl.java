package com.sg.hotelreservations.service.serviceimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoInterface.RoomAmenityDAO;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.RoomAmenity;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;
import com.sg.hotelreservations.service.serviceinterface.RoomAmenityService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class RoomAmenityServiceImpl implements RoomAmenityService {

    @Inject
    RoomAmenityDAO roomAmenityDAO;

    @Override
    public RoomAmenity create(RoomAmenity roomAmenity) {
        return roomAmenityDAO.create(roomAmenity);
    }

    @Override
    public List<RoomAmenity> retrieveByRoomId(Long roomId, int limit, int offset) {
        return roomAmenityDAO.retrieveByRoomId(roomId, limit, offset);
    }

    @Override
    public List<RoomAmenity> retrieveByAmenityId(Long amenityId, int limit, int offset) {
        return roomAmenityDAO.retrieveByAmenityId(amenityId, limit, offset);
    }

    @Override
    public void delete(RoomAmenity roomAmenity) {

        roomAmenityDAO.delete(roomAmenity);

    }
}
