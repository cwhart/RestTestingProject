package com.sg.hotelreservations.dao.daoInterface;

import com.sg.hotelreservations.dto.PromoType;

import java.util.List;

public interface PromoTypeDAO {

    public PromoType create(PromoType promoType);
    public PromoType retrieve(Long id);
    public void update(PromoType promoType);
    public void delete(PromoType promoType);
    public List<PromoType> retrieveAll(int limit, int offset);
}
