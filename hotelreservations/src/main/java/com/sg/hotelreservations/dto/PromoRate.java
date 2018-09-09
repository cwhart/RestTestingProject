package com.sg.hotelreservations.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class PromoRate {

    Long id;
    String type;
    BigDecimal rate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromoRate promoRate = (PromoRate) o;
        return id == promoRate.id &&
                Objects.equals(type, promoRate.type) &&
                Objects.equals(rate, promoRate.rate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, rate);
    }
}
