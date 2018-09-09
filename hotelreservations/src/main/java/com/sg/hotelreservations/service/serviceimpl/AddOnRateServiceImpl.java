package com.sg.hotelreservations.service.serviceimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoInterface.AddOnRateDAO;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.AddOnRate;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;
import com.sg.hotelreservations.service.serviceinterface.AddOnRateService;

import javax.inject.Inject;
import java.util.List;

public class AddOnRateServiceImpl implements AddOnRateService {

    @Inject
    AddOnRateDAO addOnRateDAO;

    @Override
    public AddOnRate create(AddOnRate addOnRate) {
        return addOnRateDAO.create(addOnRate);
    }

    @Override
    public AddOnRate retrieve(Long id) {
        return addOnRateDAO.retrieve(id);
    }

    @Override
    public void update(AddOnRate addOnRate) {
        addOnRateDAO.update(addOnRate);
    }

    @Override
    public void delete(AddOnRate addOnRate) {
        addOnRateDAO.delete(addOnRate);
    }

    @Override
    public List<AddOnRate> retrieveAll(int limit, int offset) {
        return addOnRateDAO.retrieveAll(limit, offset);
    }
}
