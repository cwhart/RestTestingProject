package com.sg.hotelreservations.viewmodels.reservationroom.list;

import com.sg.hotelreservations.dto.Room;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class ReservationRoomViewModel {

    private Long id;
    private Long promoId;
    private Long reservationHolderId;
    //private LocalDate startDate;
    //private LocalDate endDate;
    private int roomNumber;
    private String roomType;
    private int occupancy;

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

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

//    public LocalDate getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(LocalDate startDate) {
//        this.startDate = startDate;
//    }
//
//    public LocalDate getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(LocalDate endDate) {
//        this.endDate = endDate;
//    }
}
