package com.sg.hotelreservations.service.serviceimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoInterface.AmenityDAO;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.Amenity;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;
import com.sg.hotelreservations.service.serviceinterface.AmenityService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class AmenityServiceImpl implements AmenityService {

    @Inject
    AmenityDAO amenityDAO;

    @Override
    public Amenity create(Amenity amenity) {
        return amenityDAO.create(amenity);
    }

    @Override
    public Amenity retrieve(Long id) {
        return amenityDAO.retrieve(id);
    }

    @Override
    public void update(Amenity amenity) {
        amenityDAO.update(amenity);
    }

    @Override
    public void delete(Amenity amenity) {
        amenityDAO.delete(amenity);
    }

    @Override
    public List<Amenity> retrieveAll(int limit, int offset) {
        return amenityDAO.retrieveAll(limit, offset);
    }

    @Override
    public List<Amenity> retrieveAmenityByRoom(Long id) {
        return amenityDAO.retrieveAmenityByRoom(id);
    }
}
