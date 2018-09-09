package com.sg.hotelreservations.service.serviceimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoInterface.BillDAO;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.Bill;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;
import com.sg.hotelreservations.service.serviceinterface.BillService;

import javax.inject.Inject;
import java.util.List;

public class BillServiceImpl implements BillService {

    @Inject
    BillDAO billDAO;

    @Override
    public Bill create(Bill bill) {
        return billDAO.create(bill);
    }

    @Override
    public Bill retrieve(Long id) {
        return billDAO.retrieve(id);
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
        return billDAO.retrieveAll(limit, offset);
    }
}
