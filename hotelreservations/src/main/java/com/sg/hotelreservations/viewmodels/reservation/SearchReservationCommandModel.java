package com.sg.hotelreservations.viewmodels.reservation;

import java.time.LocalDate;

public class SearchReservationCommandModel {

    String reservationNumber;
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }
}
