package com.sg.hotelreservations.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Promo {

    Long id;
    PromoType promoType;
    LocalDate startDate;
    LocalDate endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PromoType getPromoType() {
        return promoType;
    }

    public void setPromoType(PromoType promoType) {
        this.promoType = promoType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Promo promo = (Promo) o;
        return Objects.equals(id, promo.id) &&
                Objects.equals(promoType, promo.promoType) &&
                Objects.equals(startDate, promo.startDate) &&
                Objects.equals(endDate, promo.endDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, promoType, startDate, endDate);
    }
}
