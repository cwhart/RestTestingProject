package com.sg.hotelreservations.dao.daoInterface;

import com.sg.hotelreservations.dto.Promo;

import java.util.List;

public interface PromoDAO {

    public Promo create(Promo promo);
    public Promo retrieve(Long id);
    public void update(Promo promo);
    public void delete(Promo promo);
    public List<Promo> retrieveAll(int limit, int offset);
    public List<Promo> retrieveByPromoTypeId(Long id);
}
