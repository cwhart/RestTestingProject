package com.sg.superhero.dao;

import com.sg.superhero.TestHelper;
import com.sg.superhero.dao.interfaces.SuperOrganizationDao;
import com.sg.superhero.dao.interfaces.SuperPowerDao;
import com.sg.superhero.dto.SuperOrganization;
import com.sg.superhero.dto.SuperPower;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class SuperPowerDaoImplTest {

    @Inject
    SuperPowerDao superPowerDao;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {

        //Arrange
        SuperPower superPower = testHelper.createTestSuperPower();
        superPowerDao.create(superPower);

        //Act
        SuperPower createdSuperPower = superPowerDao.create(superPower);


        //Assert
        assert (createdSuperPower.getId() != null);
        assertSuperPowersEqual(createdSuperPower, superPower);
    }

    @Test
    public void read() {

        //Arrange
        SuperPower superPower = testHelper.createTestSuperPower();
        superPowerDao.create(superPower);

        //Act
        SuperPower readSuperPower = superPowerDao.retrieve(superPower.getId());

        //Assert
        assert (readSuperPower.getId() != null);
        assertSuperPowersEqual(superPower, readSuperPower);
    }

    @Test
    public void delete() {

        SuperPower superPower = testHelper.createTestSuperPower();
        superPowerDao.create(superPower);

        //Act
        assert (superPower.getId() != null);
        superPowerDao.delete(superPower);

        SuperPower readSuperPower = superPowerDao.retrieve(superPower.getId());

        //Assert
        assert (null == readSuperPower);//
    }

    public void assertSuperPowersEqual(SuperPower superPower1, SuperPower superPower2) {
        assert (superPower1.getPower().getId().equals(superPower2.getPower().getId()));
        assert (superPower1.getSuperPerson().getId().equals(superPower2.getSuperPerson().getId()));
    }
}