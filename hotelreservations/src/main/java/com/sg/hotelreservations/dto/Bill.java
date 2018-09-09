package com.sg.hotelreservations.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Bill {

    Long id;
    Reservation reservation;
    BigDecimal total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return Objects.equals(id, bill.id) &&
                Objects.equals(reservation, bill.reservation) &&
                Objects.equals(total, bill.total);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, reservation, total);
    }
}
