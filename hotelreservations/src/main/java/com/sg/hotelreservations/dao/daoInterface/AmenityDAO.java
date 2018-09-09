package com.sg.hotelreservations.dao.daoInterface;

import com.sg.hotelreservations.dto.Amenity;

import java.util.List;

public interface AmenityDAO {

    public Amenity create(Amenity amenity);
    public Amenity retrieve(Long id);
    public void update(Amenity amenity);
    public void delete(Amenity amenity);
    public List<Amenity> retrieveAll(int limit, int offset);
    public List<Amenity> retrieveAmenityByRoom(Long id);
}
