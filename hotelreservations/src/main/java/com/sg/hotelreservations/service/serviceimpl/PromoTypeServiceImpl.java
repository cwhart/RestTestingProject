package com.sg.hotelreservations.service.serviceimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoInterface.PromoTypeDAO;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.PromoType;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;
import com.sg.hotelreservations.service.serviceinterface.PromoTypeService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class PromoTypeServiceImpl implements PromoTypeService {

    @Inject
    PromoTypeDAO promoTypeDAO;

    @Override
    public PromoType create(PromoType promoType) {
        return promoTypeDAO.create(promoType);
    }

    @Override
    public PromoType retrieve(Long id) {
        return promoTypeDAO.retrieve(id);
    }

    @Override
    public PromoType retrieveByPromoCode(String promoCode) {
        return promoTypeDAO.retrieveByPromoCode(promoCode);
    }

    @Override
    public void update(PromoType promoType) {
        promoTypeDAO.update(promoType);
    }

    @Override
    public void delete(PromoType promoType) {
        promoTypeDAO.delete(promoType);
    }

    @Override
    public List<PromoType> retrieveAll(int limit, int offset) {
        return promoTypeDAO.retrieveAll(limit, offset);
    }
}
