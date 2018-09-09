package com.sg.hotelreservations.service.serviceimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoInterface.AddOnDAO;
import com.sg.hotelreservations.dto.AddOn;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;
import com.sg.hotelreservations.service.serviceinterface.AddOnService;

import javax.inject.Inject;
import java.util.List;

public class AddOnServiceImpl implements AddOnService {

    @Inject
    AddOnDAO addOnDAO;

    @Override
    public AddOn create(AddOn addOn) {
        return addOnDAO.create(addOn);
    }

    @Override
    public AddOn retrieve(Long id) {
        return addOnDAO.retrieve(id);
    }

    @Override
    public void update(AddOn addOn) {
        addOnDAO.update(addOn);
    }

    @Override
    public void delete(AddOn addOn) {
        addOnDAO.delete(addOn);
    }

    @Override
    public List<AddOn> retrieveAll(int limit, int offset) {
        return addOnDAO.retrieveAll(limit, offset);
    }
}
