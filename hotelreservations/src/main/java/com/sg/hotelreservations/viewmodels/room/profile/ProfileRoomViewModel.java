package com.sg.hotelreservations.viewmodels.room.profile;

import com.sg.hotelreservations.dto.Amenity;
import com.sg.hotelreservations.viewmodels.amenity.AmenityViewModel;

import java.util.List;

public class ProfileRoomViewModel {

    private int floorNumber;
    private int roomNumber;
    private int occupancy;
    private String type;
    private List<AmenityViewModel> amenityList;

    public List<AmenityViewModel> getAmenityList() {
        return amenityList;
    }

    public void setAmenityList(List<AmenityViewModel> amenityList) {
        this.amenityList = amenityList;
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
}
