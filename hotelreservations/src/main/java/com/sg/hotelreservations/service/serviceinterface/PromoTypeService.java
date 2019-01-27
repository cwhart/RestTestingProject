package com.sg.hotelreservations.service.serviceinterface;

import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.PromoType;

import java.util.List;

public interface PromoTypeService {

    public PromoType create(PromoType promoType);
    public PromoType retrieve(Long id);
    public PromoType retrieveByPromoCode(String promoCode);
    public void update(PromoType promoType);
    public void delete(PromoType promoType);
    public List<PromoType> retrieveAll(int limit, int offset);
}
