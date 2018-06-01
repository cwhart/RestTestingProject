package com.sg.superhero.service;

import com.sg.superhero.dao.interfaces.LocationDao;
import com.sg.superhero.dto.Location;
import com.sg.superhero.dto.Super;
import com.sg.superhero.service.interfaces.LocationService;

import javax.inject.Inject;
import java.util.List;

public class LocationServiceImpl implements LocationService {

    @Inject
    private LocationDao locationDao;

    private LocationServiceImpl (LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    public Location create(Location location) {
        return locationDao.create(location);
    }//

    @Override
    public Location retrieve(Long locationId) {
        return locationDao.retrieve(locationId);
    }

    @Override
    public void update(Location location) {
        locationDao.update(location);
    }

    @Override
    public void delete(Location location) {
        locationDao.delete(location);
    }

    @Override
    public List<Location> retrieveAll(int limit, int offset) {
        return locationDao.retrieveAll(limit, offset);
    }

    @Override
    public List<Location> retrieveLocationBySuper(Super superPerson, int limit, int offset) {
        return locationDao.retrieveLocationBySuper(superPerson, limit, offset);
    }
}
