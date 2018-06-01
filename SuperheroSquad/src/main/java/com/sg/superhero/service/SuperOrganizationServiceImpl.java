package com.sg.superhero.service;

import com.sg.superhero.dao.interfaces.SuperOrganizationDao;
import com.sg.superhero.dto.SuperOrganization;
import com.sg.superhero.service.interfaces.SuperOrganizationService;

import javax.inject.Inject;

public class SuperOrganizationServiceImpl  implements SuperOrganizationService {

    @Inject
    SuperOrganizationDao superOrganizationDao;

    private SuperOrganizationServiceImpl (SuperOrganizationDao superOrganizationDao) {
        this.superOrganizationDao = superOrganizationDao;
    }

    @Override
    public SuperOrganization create(SuperOrganization superOrganization) {
        return superOrganizationDao.create(superOrganization);
    }

    @Override
    public SuperOrganization retrieve(Long id) {
        return superOrganizationDao.retrieve(id);
    }

    @Override
    public void delete(SuperOrganization superOrganization) {
        superOrganizationDao.delete(superOrganization);//
    }
}
