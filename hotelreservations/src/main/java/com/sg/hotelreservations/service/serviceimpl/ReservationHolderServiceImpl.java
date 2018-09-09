package com.sg.hotelreservations.service.serviceimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoInterface.ReservationHolderDAO;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.ReservationHolder;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;
import com.sg.hotelreservations.service.serviceinterface.ReservationHolderService;

import javax.inject.Inject;
import java.util.List;

public class ReservationHolderServiceImpl implements ReservationHolderService {

    @Inject
    ReservationHolderDAO reservationHolderDAO;

    @Override
    public ReservationHolder create(ReservationHolder reservationHolder) {
        return reservationHolderDAO.create(reservationHolder);
    }

    @Override
    public ReservationHolder retrieve(Long id) {
        return reservationHolderDAO.retrieve(id);
    }

    @Override
    public void update(ReservationHolder reservationHolder) {
        reservationHolderDAO.update(reservationHolder);
    }

    @Override
    public void delete(ReservationHolder reservationHolder) {
        reservationHolderDAO.delete(reservationHolder);
    }

    @Override
    public List<ReservationHolder> retrieveAll(int limit, int offset) {
        return reservationHolderDAO.retrieveAll(limit, offset);
    }
}
