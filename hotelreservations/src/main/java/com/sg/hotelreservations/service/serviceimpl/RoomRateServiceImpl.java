package com.sg.hotelreservations.service.serviceimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoInterface.RoomRateDAO;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.RoomRate;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;
import com.sg.hotelreservations.service.serviceinterface.RoomRateService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

@Service(value = "RoomRateServiceImpl")
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
    public RoomRate retrieveDefaultRate(Long roomId) {
        List <RoomRate> roomRateList = roomRateDAO.retrieveByRoomId(roomId);
        RoomRate roomRate = new RoomRate();

        for (RoomRate currentRoomRate : roomRateList) {
            if (currentRoomRate.getDefaultFlag().equals("D")) {
                roomRate = currentRoomRate;
            }
        }
        return roomRate;
    }

    @Override
    public RoomRate retrieveCurrentRate(Long roomId, LocalDate start, LocalDate end) {
        List <RoomRate> roomRateList = roomRateDAO.retrieveByRoomId(roomId);
        RoomRate roomRate = new RoomRate();

        for (RoomRate currentRoomRate : roomRateList) {
            if(currentRoomRate.getEndDate() != null && currentRoomRate.getDefaultFlag().equals("S") &&
                    (start.isBefore(currentRoomRate.getEndDate()) && end.isAfter(currentRoomRate.getStartDate()))) {
               roomRate =  currentRoomRate;
               break;
            }
        }
        return roomRate;
    }

    @Override
    public List<RoomRate> retrieveAll(int limit, int offset) {
        return roomRateDAO.retrieveAll(limit, offset);
    }
}
