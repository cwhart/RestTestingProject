package com.sg.hotelreservations.viewmodels.reservationroom.search;

import com.sg.hotelreservations.dto.Reservation;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import java.time.LocalDate;
import java.util.List;

public class InputReservationDatesCommandModel {

    @NotEmpty(message = "You must supply a value for Start Date.")
    private String startDate;
    @NotEmpty(message = "You must supply a value for End Date.")
    private String endDate;

    private int numPersons;

    public int getNumPersons() {
        return numPersons;
    }

    public void setNumPersons(int numPersons) {
        this.numPersons = numPersons;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
