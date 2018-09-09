package com.sg.hotelreservations.dao.daoInterface;

import com.sg.hotelreservations.dto.PromoRate;

import java.util.List;

public interface PromoRateDAO {

    public PromoRate create(PromoRate promoRate);
    public PromoRate retrieve(Long id);
    public void update(PromoRate promoRate);
    public void delete(PromoRate promoRate);
    public List<PromoRate> retrieveAll(int limit, int offset);
}
