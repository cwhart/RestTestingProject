package com.sg.hotelreservations.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Reservation {

    Long id;
    Promo promo;
    ReservationHolder reservationHolder;
    LocalDate startDate;
    LocalDate endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Promo getPromo() {
        return promo;
    }

    public void setPromo(Promo promo) {
        this.promo = promo;
    }

    public ReservationHolder getReservationHolder() {
        return reservationHolder;
    }

    public void setReservationHolder(ReservationHolder reservationHolder) {
        this.reservationHolder = reservationHolder;
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
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(promo, that.promo) &&
                Objects.equals(reservationHolder, that.reservationHolder) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, promo, reservationHolder, startDate, endDate);
    }
}
