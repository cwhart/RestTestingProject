package com.sg.superhero;

import com.sg.superhero.dao.interfaces.*;
import com.sg.superhero.dto.*;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestHelper {

    @Inject
    LocationDao locationDao;

    @Inject
    SuperDao superDao;

    @Inject
    OrganizationDao organizationDao;

    @Inject
    PowerDao powerDao;

    @Inject
    SightingDao sightingDao;

    @Inject
    SuperPowerDao superPowerDao;

    @Inject
    SuperOrganizationDao superOrganizationDao;

    @Inject
    SuperSightingDao superSightingDao;

    public Location createTestLocation() {
        Location location = new Location();
        location.setName("The Daily Planet");
        location.setStreet("123 Main St.");
        location.setCity("Metropolis");
        location.setState("NY");
        location.setZip("12345");
        location.setDescription("Daily Planet main offices");
        location.setLatitude((long)40.71);
        location.setLongitude((long)74.00);
        locationDao.create(location);
        return location;
    }


    public List<Location> createMultipleLocations(int numToCreate) {
        List<Location> locationList = new ArrayList<>();

        for (int i=0; i<numToCreate; i++) {
            Location location = new Location();
            location.setName("The Daily Planet" + i);
            location.setStreet("123 Main St." + i);
            location.setCity("Metropolis" + i);
            location.setState("NY");
            location.setZip("12345");
            location.setDescription("Daily Planet main offices" + i);
            location.setLatitude((long)40.71);
            location.setLongitude((long)74.00);
            //locationDao.create(location);
            locationList.add(locationDao.create(location));
        }

        return locationList;
    }

    public Super createTestSuper() {
        Super superPerson = new Super();
        superPerson.setName("Superman");
        superPerson.setDescription("The Man of Steel");
        superDao.create(superPerson);
        return superPerson;
    }

    public List<Super> createMultipleSupers(int numToCreate) {
        List<Super> superList = new ArrayList<>();

        for (int i=0; i<numToCreate; i++) {
            Super superPerson = new Super();
            superPerson.setName("Superman" + i);
            superPerson.setDescription("The Man of Steel" + i);
            //locationDao.create(location);
            superList.add(superDao.create(superPerson));
        }

        return superList;
    }

    public Organization createTestOrganization() {
        Organization organization = new Organization();
        organization.setName("Justice League");
        organization.setDescription("Superhero Organization");
        organization.setEmail("justice@jl.com");
        organization.setPhone("867-5309");
        organization.setLocation(createTestLocation());
        organizationDao.create(organization);
        return organization;
    }

    public List<Organization> createMultipleOrganizations(int numToCreate) {
        List<Organization> orgList = new ArrayList<>();

        for (int i=0; i<numToCreate; i++) {
            Organization organization = new Organization();
            organization.setName("Justice League" + i);
            organization.setDescription("Superhero Organization" + i);
            organization.setEmail("justice@jl.com" + i);
            organization.setPhone("867-5309");
            organization.setLocation(createTestLocation());

            orgList.add(organizationDao.create(organization));
        }

        return orgList;
    }

    public Power createTestPower() {
        Power power = new Power();
        power.setName("Invisibility");
        powerDao.create(power);
        return power;
    }

    public List<Power> createMultiplePowers(int numToCreate) {
        List<Power> powerList = new ArrayList<>();

        for (int i=0; i<numToCreate; i++) {
            Power power = new Power();
            power.setName("Invisibility" + i);
            powerList.add(powerDao.create(power));
        }

        return powerList;
    }

    public Sighting createTestSighting() {
        Sighting sighting = new Sighting();
        sighting.setLocation(createTestLocation());
        sighting.setDate(LocalDate.parse("2018-05-01"));
        sightingDao.create(sighting);
        return sighting;
    }

    public Sighting createTestSighting(Location location, LocalDate date) {

        Sighting sighting = new Sighting();
        sighting.setLocation(location);
        sighting.setDate(date);
        sightingDao.create(sighting);
        return sighting;

    }

    public List<Sighting> createMultipleSightings(int numToCreate) {
        List<Sighting> sightingList = new ArrayList<>();

        for (int i=0; i<numToCreate; i++) {
            Sighting sighting = new Sighting();
            sighting.setLocation(createTestLocation());
            sighting.setDate(LocalDate.now().plusDays((long)i));
            sightingList.add(sightingDao.create(sighting));
        }

        return sightingList;
    }

    public SuperOrganization createTestSuperOrganization () {
        Super superPerson = createTestSuper();
        Organization org = createTestOrganization();
        SuperOrganization superOrganization = new SuperOrganization();

        superOrganization.setOrganization(org);
        superOrganization.setSuperPerson(superPerson);

        return superOrganizationDao.create(superOrganization);
    }

    public SuperOrganization createTestSuperOrganization (Super superPerson, Organization organization) {
        //Super superPerson = createTestSuper();
        //Organization org = createTestOrganization();
        SuperOrganization superOrganization = new SuperOrganization();

        superOrganization.setOrganization(organization);
        superOrganization.setSuperPerson(superPerson);

        return superOrganizationDao.create(superOrganization);
    }

    public List<SuperOrganization> createMultipleSuperOrgs(int numToCreate) {
        List<SuperOrganization> superOrganizationList = new ArrayList<>();

        for (int i=0; i<numToCreate; i++) {
            SuperOrganization superOrg = new SuperOrganization();
            superOrg.setOrganization(createTestOrganization());
            superOrg.setSuperPerson(createTestSuper());
            superOrganizationList.add(superOrg);
        }

        return superOrganizationList;//
    }

    public SuperPower createTestSuperPower () {
        Super superPerson = createTestSuper();
        Power power = createTestPower();
        SuperPower superPower = new SuperPower();

        superPower.setPower(power);
        superPower.setSuperPerson(superPerson);

        return superPowerDao.create(superPower);
    }

    public SuperPower createTestSuperPower (Super superPerson, Power power) {

        SuperPower superPower = new SuperPower();

        superPower.setPower(power);
        superPower.setSuperPerson(superPerson);

        return superPowerDao.create(superPower);
    }

    public SuperSighting createTestSuperSighting () {
        Super superPerson = createTestSuper();
        Sighting sighting = createTestSighting();
        SuperSighting superSighting = new SuperSighting();

        superSighting.setSighting(sighting);
        superSighting.setSuperPerson(superPerson);

        return superSightingDao.create(superSighting);
    }

    public SuperSighting createTestSuperSighting(Super superPerson, Sighting sighting) {
        SuperSighting superSighting = new SuperSighting();

        superSighting.setSighting(sighting);
        superSighting.setSuperPerson(superPerson);

        return superSightingDao.create(superSighting);
    }

}
