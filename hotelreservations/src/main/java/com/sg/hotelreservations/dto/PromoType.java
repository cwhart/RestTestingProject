package com.sg.hotelreservations.dto;

import java.util.Objects;

public class PromoType {

    Long id;
    PromoRate promoRate;
    String promoCode;
    String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PromoRate getPromoRate() {
        return promoRate;
    }

    public void setPromoRate(PromoRate promoRate) {
        this.promoRate = promoRate;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromoType promoType = (PromoType) o;
        return Objects.equals(id, promoType.id) &&
                Objects.equals(promoRate, promoType.promoRate) &&
                Objects.equals(promoCode, promoType.promoCode) &&
                Objects.equals(description, promoType.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, promoRate, promoCode, description);
    }
}
