package com.sg.hotelreservations.dto;

import java.util.Objects;

public class GuestReservation {

    Guest guest;
    Reservation reservation;

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuestReservation that = (GuestReservation) o;
        return Objects.equals(guest, that.guest) &&
                Objects.equals(reservation, that.reservation);
    }

    @Override
    public int hashCode() {

        return Objects.hash(guest, reservation);
    }
}
