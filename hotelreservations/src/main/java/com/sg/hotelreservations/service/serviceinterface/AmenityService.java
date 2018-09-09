package com.sg.hotelreservations.service.serviceinterface;

import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.Amenity;

import java.util.List;

public interface AmenityService {

    public Amenity create(Amenity amenity);
    public Amenity retrieve(Long id);
    public void update(Amenity amenity);
    public void delete(Amenity amenity);
    public List<Amenity> retrieveAll(int limit, int offset);
    public List<Amenity> retrieveAmenityByRoom(Long id);
}
