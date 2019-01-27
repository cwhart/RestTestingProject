package com.sg.hotelreservations.service.serviceimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoInterface.PromoRateDAO;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.PromoRate;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;
import com.sg.hotelreservations.service.serviceinterface.PromoRateService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class PromoRateServiceImpl implements PromoRateService {

    @Inject
    PromoRateDAO promoRateDAO;

    @Override
    public PromoRate create(PromoRate promoRate) {
        return promoRateDAO.create(promoRate);
    }

    @Override
    public PromoRate retrieve(Long id) {
        return promoRateDAO.retrieve(id);
    }

    @Override
    public void update(PromoRate promoRate) {
        promoRateDAO.update(promoRate);
    }

    @Override
    public void delete(PromoRate promoRate) {
        promoRateDAO.delete(promoRate);
    }

    @Override
    public List<PromoRate> retrieveAll(int limit, int offset) {
        return promoRateDAO.retrieveAll(limit, offset);
    }
}
