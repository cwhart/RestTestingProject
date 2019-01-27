package com.sg.hotelreservations.service.serviceimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoInterface.ReservationDAO;
import com.sg.hotelreservations.dao.daoInterface.ReservationRoomDAO;
import com.sg.hotelreservations.dao.daoInterface.RoomDAO;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.Reservation;
import com.sg.hotelreservations.dto.ReservationRoom;
import com.sg.hotelreservations.dto.Room;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;
import com.sg.hotelreservations.service.serviceinterface.ReservationRoomService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "ReservationRoomServiceImpl")
public class ReservationRoomServiceImpl implements ReservationRoomService {

    @Inject
    ReservationRoomDAO reservationRoomDAO;

    @Inject
    ReservationDAO reservationDAO;

    @Inject
    RoomDAO roomDAO;

    @Override
    public ReservationRoom create(ReservationRoom reservationRoom) {
        return reservationRoomDAO.create(reservationRoom);
    }

    @Override
    public List<ReservationRoom> retrieveByReservationId(Reservation reservation) {
        return reservationRoomDAO.retrieveByReservationId(reservation.getId());
    }

    @Override
    public List<ReservationRoom> retrieveByRoomId(Long roomId, int limit, int offset) {
        return reservationRoomDAO.retrieveByRoomId(roomId, limit, offset);
    }

    @Override
    public void delete(ReservationRoom reservationRoom) {
        reservationRoomDAO.delete(reservationRoom);
    }

    @Override
    public List<ReservationRoom> retrieveByDates(LocalDate start, LocalDate end) {

        return reservationRoomDAO.retrieveByDates(start, end);
    }

    @Override
    public Boolean isBooked(Long roomId, LocalDate date) {

        return reservationRoomDAO.isBooked(roomId, date);
    }

    @Override
    public Boolean isBookedForDateRange (int roomNum, LocalDate start, LocalDate end) {
        Boolean returnValue = false;
        Room room = roomDAO.retrieveByRoomNum(roomNum);

        for (LocalDate thisDate = start; thisDate.isBefore(end); thisDate = thisDate.plusDays(1)) {
            if (isBooked(room.getId(), thisDate)) returnValue = true;
        }
        return returnValue;
    }

//    @Override
//    public HashMap<Room, HashMap<LocalDate, Boolean>> calculateBookingMaps(List<ReservationRoom> reservationRoomList, LocalDate start, LocalDate end) {
//
//        start = start.minusDays(10);
//        end = end.plusDays(10);
//
//        HashMap<Room, HashMap<LocalDate, Boolean>> returnMap = new HashMap<Room, HashMap<LocalDate, Boolean>>();
//
//        List<Long> roomList = new ArrayList<>();
//        for (ReservationRoom thisResRoom : reservationRoomList) {
//            if (!roomList.contains(thisResRoom.getRoom().getId())) roomList.add(thisResRoom.getRoom().getId());
//            HashMap<LocalDate, Boolean> theseBookings = new HashMap<>();
//
//            for (LocalDate thisDate = start; thisDate.isBefore(end) || thisDate.isEqual(end); thisDate = thisDate.plusDays(1)) {
//                if (thisDate.isBefore(thisResRoom.getReservation().getStartDate()) || thisDate.isAfter(thisResRoom.getReservation().getEndDate())) {
//                    theseBookings.put(thisDate, false);
//                } else theseBookings.put(thisDate, true);
//            }
//
//            returnMap.put(thisResRoom.getRoom(), theseBookings);
//
//        }
//
//        return returnMap;
//    }
}
