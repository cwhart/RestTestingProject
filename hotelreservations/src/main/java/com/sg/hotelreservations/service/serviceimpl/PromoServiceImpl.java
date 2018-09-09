package com.sg.hotelreservations.service.serviceimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoInterface.PromoDAO;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.Promo;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;
import com.sg.hotelreservations.service.serviceinterface.PromoService;

import javax.inject.Inject;
import java.util.List;

public class PromoServiceImpl implements PromoService {

    @Inject
    PromoDAO promoDAO;

    @Override
    public Promo create(Promo promo) {
        return promoDAO.create(promo);
    }

    @Override
    public Promo retrieve(Long id) {
        return promoDAO.retrieve(id);
    }

    @Override
    public void update(Promo promo) {
        promoDAO.update(promo);
    }

    @Override
    public void delete(Promo promo) {
        promoDAO.delete(promo);
    }

    @Override
    public List<Promo> retrieveAll(int limit, int offset) {
        return promoDAO.retrieveAll(limit, offset);
    }
}
