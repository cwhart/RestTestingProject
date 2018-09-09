package com.sg.hotelreservations.service.serviceinterface;

import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.Promo;

import java.util.List;

public interface PromoService {

    public Promo create(Promo promo);
    public Promo retrieve(Long id);
    public void update(Promo promo);
    public void delete(Promo promo);
    public List<Promo> retrieveAll(int limit, int offset);
}
