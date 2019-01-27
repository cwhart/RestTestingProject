package com.sg.hotelreservations.service.serviceimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoInterface.BillDAO;
import com.sg.hotelreservations.dao.daoInterface.RoomBillDetailDAO;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.Bill;
import com.sg.hotelreservations.dto.RoomBillDetail;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;
import com.sg.hotelreservations.service.serviceinterface.BillService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Inject
    BillDAO billDAO;

    @Inject
    AddOnBillDetailDAO addOnBillDetailDAO;

    @Inject
    RoomBillDetailDAO roomBillDetailDAO;

    @Override
    public Bill create(Bill bill) {
        return billDAO.create(bill);
    }

    @Override
    public Bill retrieve(Long id) {

        Bill bill = new Bill();
        //Update and save the total each time the bill is retrieved.

        if (billDAO.retrieve(id) != null) {
            bill = billDAO.retrieve(id);
            bill.setTotal(updateBillTotal(bill));
            billDAO.update(bill);
        }

        return bill;
    }

    @Override
    public void update(Bill bill) {
        billDAO.update(bill);
    }

    @Override
    public void delete(Bill bill) {
        billDAO.delete(bill);
    }

    @Override
    public List<Bill> retrieveAll(int limit, int offset) {
        List<Bill> billList =  billDAO.retrieveAll(limit, offset);

        for (Bill bill : billList) {
            updateBillTotal(bill);
        }

        return billList;
    }

    @Override
    public Bill retrieveByReservationId(Long reservationId) {
        Bill bill = billDAO.retrieveByReservationId(reservationId);
        bill.setTotal(updateBillTotal(bill));
        billDAO.update(bill);

        return bill;
    }

    public BigDecimal updateBillTotal(Bill bill) {

        List<AddOnBillDetail> addOnBillDetails = new ArrayList<>();
        List<RoomBillDetail> roomBillDetails = new ArrayList<>();

        if (addOnBillDetailDAO.retrieveByBillId(bill.getId()) != null) {
            addOnBillDetails = addOnBillDetailDAO.retrieveByBillId(bill.getId());
        }
        if (roomBillDetailDAO.retrieveByBillId(bill.getId()) != null) {
            roomBillDetails = roomBillDetailDAO.retrieveByBillId(bill.getId());
        }

        BigDecimal total = BigDecimal.valueOf(0);

        for (AddOnBillDetail thisAddOnBillDetail : addOnBillDetails) {
            total = total.add(thisAddOnBillDetail.getPrice().add(thisAddOnBillDetail.getTaxAmount()));
        }

        for (RoomBillDetail thisRoomBillDetail : roomBillDetails) {
            total = total.add(thisRoomBillDetail.getPrice().add(thisRoomBillDetail.getTaxAmount()));
        }
         return total;
    }
}
