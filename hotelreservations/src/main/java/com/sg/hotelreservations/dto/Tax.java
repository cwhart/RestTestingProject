package com.sg.hotelreservations.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Tax {

    Long id;
    String type;
    LocalDate startDate;
    LocalDate endDate;
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
        Tax tax = (Tax) o;
        return id == tax.id &&
                Objects.equals(type, tax.type) &&
                Objects.equals(startDate, tax.startDate) &&
                Objects.equals(endDate, tax.endDate) &&
                Objects.equals(rate, tax.rate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, startDate, endDate, rate);
    }
}
