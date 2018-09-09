package com.sg.hotelreservations.dto;

import java.util.Objects;

public class ReservationRoom {

    Reservation reservation;
    Room room;

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationRoom that = (ReservationRoom) o;
        return Objects.equals(reservation, that.reservation) &&
                Objects.equals(room, that.room);
    }

    @Override
    public int hashCode() {

        return Objects.hash(reservation, room);
    }
}
