package com.sg.superhero.service;

import com.sg.superhero.dao.interfaces.SightingDao;
import com.sg.superhero.dto.Location;
import com.sg.superhero.dto.Sighting;
import com.sg.superhero.dto.Super;
import com.sg.superhero.service.interfaces.SightingService;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

public class SightingServiceImpl implements SightingService {

    @Inject
    SightingDao sightingDao;

    private SightingServiceImpl (SightingDao sightingDao) {
        this.sightingDao = sightingDao;
    }

    @Override
    public Sighting create(Sighting sighting) {
        return sightingDao.create(sighting);
    }

    @Override
    public Sighting retrieve(Long id) {
        return sightingDao.retrieve(id);
    }

    @Override
    public void update(Sighting sighting) {
        sightingDao.update(sighting);//
    }

    @Override
    public void delete(Sighting sighting) {
        sightingDao.delete(sighting);
    }

    @Override
    public List<Sighting> retrieveAll(int limit, int offset) {
        return sightingDao.retrieveAll(limit, offset);
    }

    @Override
    public List<Sighting> retrieveSightingsBySuper(Super superPerson, int limit, int offset) {
        return sightingDao.retrieveSightingsBySuper( superPerson, limit, offset);
    }

    @Override
    public List<Sighting> retrieveSightingsByLocationAndSuperAndDate(Location location,
                    Super superPerson, LocalDate localDate, int limit, int offset) {
        return sightingDao.retrieveSightingsByLocationAndSuperAndDate(location, superPerson, localDate, limit, offset);
    }
}
