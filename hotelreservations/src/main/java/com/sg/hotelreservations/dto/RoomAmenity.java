package com.sg.hotelreservations.dto;

import java.util.Objects;

public class RoomAmenity {

    Room room;
    Amenity amenity;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Amenity getAmenity() {
        return amenity;
    }

    public void setAmenity(Amenity amenity) {
        this.amenity = amenity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomAmenity that = (RoomAmenity) o;
        return Objects.equals(room, that.room) &&
                Objects.equals(amenity, that.amenity);
    }

    @Override
    public int hashCode() {

        return Objects.hash(room, amenity);
    }
}
