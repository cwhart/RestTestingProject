package com.sg.hotelreservations.dto;

import java.util.Objects;

public class Room {

    Long id;
    int floorNumber;
    int roomNumber;
    int occupancy;
    String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id &&
                floorNumber == room.floorNumber &&
                roomNumber == room.roomNumber &&
                occupancy == room.occupancy &&
                Objects.equals(type, room.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, floorNumber, roomNumber, occupancy, type);
    }
}
