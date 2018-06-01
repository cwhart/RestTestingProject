package com.sg.superhero.service;

import com.sg.superhero.dao.interfaces.SuperPowerDao;
import com.sg.superhero.dto.SuperPower;
import com.sg.superhero.service.interfaces.SuperPowerService;

import javax.inject.Inject;

public class SuperPowerServiceImpl implements SuperPowerService {

    @Inject
    SuperPowerDao superPowerDao;

    private SuperPowerServiceImpl (SuperPowerDao superPowerDao) {
        this.superPowerDao = superPowerDao;
    }

    @Override
    public SuperPower create(SuperPower superPower) {
        return superPowerDao.create(superPower);
    }

    @Override
    public SuperPower retrieve(Long id) {
        return superPowerDao.retrieve(id);
    }

    @Override
    public void delete(SuperPower superPower) {
        superPowerDao.delete(superPower);//
    }
}
