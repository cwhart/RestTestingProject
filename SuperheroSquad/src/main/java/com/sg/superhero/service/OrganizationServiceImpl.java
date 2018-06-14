package com.sg.superhero.service;

import com.sg.superhero.dao.interfaces.OrganizationDao;
import com.sg.superhero.dto.Location;
import com.sg.superhero.dto.Organization;
import com.sg.superhero.dto.Super;
import com.sg.superhero.service.interfaces.OrganizationService;

import javax.inject.Inject;
import java.util.List;

public class OrganizationServiceImpl implements OrganizationService {

    @Inject
    private OrganizationDao organizationDao;

    private OrganizationServiceImpl(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    @Override
    public Organization create(Organization organization) {
        return organizationDao.create(organization);
    }

    @Override
    public Organization retrieve(Long aLong) {
        return organizationDao.retrieve(aLong);
    }//

    @Override
    public void update(Organization organization) {
        organizationDao.update(organization);
    }

    @Override
    public void delete(Organization organization) {
        organizationDao.delete(organization);
    }

    @Override
    public List<Organization> retrieveAll(int limit, int offset) {
        return organizationDao.retrieveAll(limit, offset);
    }

    @Override
    public List<Organization> retrieveOrganizationsBySuper(Super aSuper, int limit, int offset) {
        return organizationDao.retrieveOrganizationsBySuper(aSuper, limit, offset);
    }

    @Override
    public List<Organization> retrieveOrganizationsByLocation(Location location, int limit, int offset) {
        return organizationDao.retrieveOrganizationsByLocation(location, limit, offset);
    }
}
