package com.sg.hotelreservations.service.serviceimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;

import javax.inject.Inject;
import java.util.List;

public class AddOnBillDetailServiceImpl implements AddOnBillDetailService {

    @Inject
    AddOnBillDetailDAO addOnBillDetailDAO;

    @Override
    public AddOnBillDetail create(AddOnBillDetail addOnBillDetail) {
        return addOnBillDetailDAO.create(addOnBillDetail);
    }

    @Override
    public AddOnBillDetail retrieve(Long id) {
        return addOnBillDetailDAO.retrieve(id);
    }

    @Override
    public void update(AddOnBillDetail addOnBillDetail) {
        addOnBillDetailDAO.update(addOnBillDetail);
    }

    @Override
    public void delete(AddOnBillDetail addOnBillDetail) {
        addOnBillDetailDAO.delete(addOnBillDetail);
    }

    @Override
    public List<AddOnBillDetail> retrieveAll(int limit, int offset) {
        return addOnBillDetailDAO.retrieveAll(limit, offset);
    }
}
