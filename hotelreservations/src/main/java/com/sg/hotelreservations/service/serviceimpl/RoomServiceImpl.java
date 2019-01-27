package com.sg.hotelreservations.service.serviceimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoInterface.RoomDAO;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.Room;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;
import com.sg.hotelreservations.service.serviceinterface.RoomService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(value = "RoomServiceImpl")
public class RoomServiceImpl implements RoomService {

    @Inject
    RoomDAO roomDAO;

    @Override
    public Room create(Room room) {
        return roomDAO.create(room);
    }

    @Override
    public Room retrieve(Long id) {
        return roomDAO.retrieve(id);
    }

    @Override
    public void update(Room room) {
        roomDAO.update(room);
    }

    @Override
    public void delete(Room room) {
        roomDAO.delete(room);
    }

    @Override
    public List<Room> retrieveAll(int limit, int offset) {
        return roomDAO.retrieveAll(limit, offset);
    }

    @Override
    public Room retrieveByRoomNumber(int roomNumber) {
        return roomDAO.retrieveByRoomNum(roomNumber);
    }
}
