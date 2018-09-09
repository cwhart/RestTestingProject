package com.sg.hotelreservations.viewmodels.room.create;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class CreateRoomCommandModel {


    @Length(max = 2, message = "Floor number must be no more than 2 characters in length.")
    private int floorNumber;
    @Length(max = 3, message = "Room number must be no more than 3 characters in length.")
    @NotEmpty(message = "You must supply a value for room number.")
    private int roomNumber;
    @NotEmpty(message = "You must supply a value for Occupancy.")
    @Length(max = 2, message = "Occupancy must be no more than 2 characters in length.")
    private int occupancy;
    @NotEmpty(message = "You must supply a value for Type.")
    @Length(max = 50, message = "Type must be no more than 50 characters in length.")
    private String type;

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
}
