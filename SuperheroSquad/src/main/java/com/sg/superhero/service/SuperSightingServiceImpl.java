package com.sg.superhero.service;

import com.sg.superhero.dao.interfaces.SuperSightingDao;
import com.sg.superhero.dto.SuperSighting;
import com.sg.superhero.service.interfaces.SuperSightingService;

import javax.inject.Inject;

public class SuperSightingServiceImpl implements SuperSightingService {

    @Inject
    SuperSightingDao superSightingDao;

    private SuperSightingServiceImpl (SuperSightingDao superSightingDao) {
        this.superSightingDao = superSightingDao;
    }

    @Override
    public SuperSighting create(SuperSighting superSighting) {
        return superSightingDao.create(superSighting);
    }

    @Override
    public SuperSighting retrieve(Long id) {
        return superSightingDao.retrieve(id);
    }

    @Override
    public void delete(SuperSighting superSighting) {
        superSightingDao.delete(superSighting);//
    }
}
