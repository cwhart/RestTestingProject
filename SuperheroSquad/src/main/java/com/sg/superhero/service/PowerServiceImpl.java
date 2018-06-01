package com.sg.superhero.service;

import com.sg.superhero.dao.interfaces.PowerDao;
import com.sg.superhero.dto.Power;
import com.sg.superhero.dto.Super;
import com.sg.superhero.service.interfaces.PowerService;

import javax.inject.Inject;
import java.util.List;

public class PowerServiceImpl implements PowerService {

    @Inject
    PowerDao powerDao;

    private PowerServiceImpl (PowerDao powerDao) {
        this.powerDao = powerDao;
    }

    @Override
    public Power create(Power power) {
        return powerDao.create(power);
    }

    @Override
    public Power retrieve(Long id) {
        return powerDao.retrieve(id);
    }

    @Override
    public void update(Power power) {
        powerDao.update(power);
    }

    @Override
    public void delete(Power power) {
        powerDao.delete(power);//
    }

    @Override
    public List<Power> retrieveAll(int limit, int offset) {
        return powerDao.retrieveAll(limit, offset);
    }

    @Override
    public List<Power> retrievePowersBySuper(Super superPerson, int limit, int offset) {
        return powerDao.retrievePowersBySuper(superPerson, limit, offset);
    }
}
