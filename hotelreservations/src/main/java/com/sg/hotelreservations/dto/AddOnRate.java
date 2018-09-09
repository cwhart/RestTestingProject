package com.sg.hotelreservations.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class AddOnRate {

    Long id;
    AddOn addOn;
    LocalDate startDate;
    LocalDate endDate;
    BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AddOn getAddOn() {
        return addOn;
    }

    public void setAddOn(AddOn addOn) {
        this.addOn = addOn;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddOnRate addOnRate = (AddOnRate) o;
        return Objects.equals(id, addOnRate.id) &&
                Objects.equals(addOn, addOnRate.addOn) &&
                Objects.equals(startDate, addOnRate.startDate) &&
                Objects.equals(endDate, addOnRate.endDate) &&
                Objects.equals(price, addOnRate.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, addOn, startDate, endDate, price);
    }
}
