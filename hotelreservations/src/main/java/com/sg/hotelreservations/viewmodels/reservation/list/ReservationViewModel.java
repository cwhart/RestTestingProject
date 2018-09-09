package com.sg.hotelreservations.viewmodels.reservation.list;

import com.sg.hotelreservations.dto.Amenity;

import java.time.LocalDate;
import java.util.List;

public class ReservationViewModel {

    private Long id;
    private Long promoId;
    private Long reservationHolderId;
    private LocalDate startDate;
    private LocalDate endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPromoId() {
        return promoId;
    }

    public void setPromoId(Long promoId) {
        this.promoId = promoId;
    }

    public Long getReservationHolderId() {
        return reservationHolderId;
    }

    public void setReservationHolderId(Long reservationHolderId) {
        this.reservationHolderId = reservationHolderId;
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
}
