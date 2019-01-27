package com.sg.hotelreservations.service.serviceimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoInterface.GuestDAO;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.Guest;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;
import com.sg.hotelreservations.service.serviceinterface.GuestService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class GuestServiceImpl implements GuestService {

    @Inject
    GuestDAO guestDAO;

    @Override
    public Guest create(Guest guest) {
        return guestDAO.create(guest);
    }

    @Override
    public Guest retrieve(Long id) {
        return guestDAO.retrieve(id);
    }

    @Override
    public void update(Guest guest) {
        guestDAO.update(guest);
    }

    @Override
    public void delete(Guest guest) {
        guestDAO.delete(guest);
    }

    @Override
    public List<Guest> retrieveAll(int limit, int offset) {
        return guestDAO.retrieveAll(limit, offset);
    }
}
