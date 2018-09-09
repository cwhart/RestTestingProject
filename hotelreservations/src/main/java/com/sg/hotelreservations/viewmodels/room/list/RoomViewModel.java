package com.sg.hotelreservations.viewmodels.room.list;

import com.sg.hotelreservations.dto.Amenity;
import com.sg.hotelreservations.viewmodels.amenity.AmenityViewModel;

import java.util.List;

public class RoomViewModel {

    private String type;
    private int roomnumber;
    private int occupancy;
    private int floornumber;
    private Long id;
    private List<Amenity> amenities;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(int roomnumber) {
        this.roomnumber = roomnumber;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public int getFloornumber() {
        return floornumber;
    }

    public void setFloornumber(int floornumber) {
        this.floornumber = floornumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }
}
