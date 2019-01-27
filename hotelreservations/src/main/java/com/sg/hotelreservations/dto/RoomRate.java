package com.sg.hotelreservations.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class RoomRate {

    Long id;
    Room room;
    String defaultFlag;
    LocalDate startDate;
    LocalDate endDate;
    BigDecimal price;

    public String getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(String defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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
        RoomRate roomRate = (RoomRate) o;
        return Objects.equals(id, roomRate.id) &&
                Objects.equals(room, roomRate.room) &&
                Objects.equals(startDate, roomRate.startDate) &&
                Objects.equals(endDate, roomRate.endDate) &&
                Objects.equals(price, roomRate.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, room, startDate, endDate, price);
    }
}
