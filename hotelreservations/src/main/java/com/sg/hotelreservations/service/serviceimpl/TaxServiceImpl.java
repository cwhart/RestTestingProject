package com.sg.hotelreservations.service.serviceimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoInterface.TaxDAO;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.Tax;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;
import com.sg.hotelreservations.service.serviceinterface.TaxService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service(value = "TaxServiceImpl")
public class TaxServiceImpl implements TaxService {

    @Inject
    TaxDAO taxDAO;

    @Override
    public Tax create(Tax tax) {
        return taxDAO.create(tax);
    }

    @Override
    public Tax retrieve(Long id) {
        //if tax retrieval returns null, create a default tax of 0%.
        Tax tax = new Tax();
        if (taxDAO.retrieve(id) == null) {
            tax.setEndDate(LocalDate.parse("9999-12-31"));
            tax.setType("Default tax");
            tax.setRate(BigDecimal.valueOf(0));
            tax.setStartDate(LocalDate.parse("2000-01-01"));
            tax.setId(Long.valueOf(0));
        } else tax = taxDAO.retrieve(id);


        return tax;
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

    @Override
    public List<Tax> retrieveByState(String state) {
        return taxDAO.retrieveByState(state);
    }

    @Override
    public List<Tax> retrieveByType(String type) {
        return taxDAO.retrieveByType(type);
    }
}
