package com.sg.hotelreservations.service.serviceimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoInterface.TaxDAO;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.Tax;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;
import com.sg.hotelreservations.service.serviceinterface.TaxService;

import javax.inject.Inject;
import java.util.List;

public class TaxServiceImpl implements TaxService {

    @Inject
    TaxDAO taxDAO;

    @Override
    public Tax create(Tax tax) {
        return taxDAO.create(tax);
    }

    @Override
    public Tax retrieve(Long id) {
        return taxDAO.retrieve(id);
    }

    @Override
    public void update(Tax tax) {
        taxDAO.update(tax);
    }

    @Override
    public void delete(Tax tax) {
        taxDAO.delete(tax);
    }

    @Override
    public List<Tax> retrieveAll(int limit, int offset) {
        return taxDAO.retrieveAll(limit, offset);
    }
}
