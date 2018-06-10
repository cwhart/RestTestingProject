package com.sg.superhero.service;

import com.sg.superhero.dao.interfaces.SuperDao;
import com.sg.superhero.dto.*;
import com.sg.superhero.service.interfaces.SuperService;

import javax.inject.Inject;
import java.util.List;

public class SuperServiceImpl implements SuperService {


    SuperDao superDao;

    @Inject
    private SuperServiceImpl (SuperDao superDao) {
        this.superDao = superDao;
    }

    @Override
    public Super create(Super superPerson) {
        return superDao.create(superPerson);
    }

    @Override
    public Super retrieve(Long id) {
        return superDao.retrieve(id);
    }

    @Override
    public void update(Super superPerson) {
        superDao.update(superPerson);
    }

    @Override
    public void delete(Super superPerson) {
        superDao.delete(superPerson);
    }

    @Override
    public List<Super> retrieveAll(int limit, int offset) {
        return superDao.retrieveAll(limit, offset);
    }

    @Override
    public List<Super> retrieveSupersByOrganization(Organization organization, int limit, int offset) {
        return superDao.retrieveSupersByOrganization(organization, limit, offset);
    }

    @Override
    public List<Super> retrieveSupersByLocation(Location location, int limit, int offset) {
        return superDao.retrieveSupersByLocation(location, limit, offset);
    }

    @Override
    public List<Super> retrieveSupersBySighting(Sighting sighting, int limit, int offset) {
        return superDao.retrieveSupersBySighting(sighting, limit, offset);//
    }

    @Override
    public List<Super> retrieveSupersByPower(Power power, int limit, int offset) {
        return superDao.retrieveSupersByPower(power, limit, offset);
    }
}
