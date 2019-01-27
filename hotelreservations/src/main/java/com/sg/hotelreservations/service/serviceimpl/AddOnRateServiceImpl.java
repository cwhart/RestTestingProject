package com.sg.hotelreservations.service.serviceimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoInterface.AddOnRateDAO;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.AddOnRate;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;
import com.sg.hotelreservations.service.serviceinterface.AddOnRateService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

@Service(value = "AddOnRateServiceImpl")
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

    @Override
    public List<AddOnRate> retrieveByAddOnId(Long addOnId) {
        return addOnRateDAO.retrieveByAddOnId(addOnId);
    }

    @Override
    public AddOnRate retrieveDefaultRate(Long addOnId) {
        List <AddOnRate> addOnRateList = addOnRateDAO.retrieveByAddOnId(addOnId);
        AddOnRate addOnRate = new AddOnRate();

        for (AddOnRate currentAddOnRate : addOnRateList) {
            if (currentAddOnRate.getDefaultFlag().equals("D")) {
                addOnRate = currentAddOnRate;
            }
        }
        return addOnRate;
    }

    @Override
    public AddOnRate retrieveCurrentRate(Long addOnId, LocalDate date) {
        List <AddOnRate> addOnRateList = addOnRateDAO.retrieveByAddOnId(addOnId);
        AddOnRate addOnRate = new AddOnRate();

        for (AddOnRate currentAddOnRate : addOnRateList) {
            if(currentAddOnRate.getEndDate() != null) {
                if (currentAddOnRate.getDefaultFlag().equals("S")) {
                    if (date.isBefore(currentAddOnRate.getEndDate()) && date.isAfter(currentAddOnRate.getStartDate())) {
                        addOnRate =  currentAddOnRate;
                        break;
                    }
                }
            }
        }
        return addOnRate;
    }
}
