package com.sg.superhero.dao;

import com.sg.superhero.TestHelper;
import com.sg.superhero.dao.interfaces.OrganizationDao;
import com.sg.superhero.dao.interfaces.PowerDao;
import com.sg.superhero.dto.Organization;
import com.sg.superhero.dto.Power;
import com.sg.superhero.dto.Super;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class PowerDaoImplTest {

    @Inject
    PowerDao powerDao;

    @Inject
    TestHelper testHelper;

    @Test
    public void addPower() {

        //Arrange
        Power power = testHelper.createTestPower();
        powerDao.create(power);

        //Act
        Power createdPower = powerDao.create(power);


        //Assert
        assert (createdPower.getId() != null);
        assertPowersEqual(createdPower, power);
    }

//    @Test
//    public void retrieveAllPowers() {
//
//        //Arrange
//        List<Power> powerList = testHelper.createMultiplePowers(25);
//
//        //Act
//        List<Power> readPowerList = powerDao.retrieveAll(Integer.MAX_VALUE, 0);
//
//        //Assert
//        assert readPowerList.size() == 25;
//        assertPowerListsEqual(readPowerList, powerList);
//    }

    @Test
    public void retrievePowerById() {

        //Arrange
        Power power = testHelper.createTestPower();
        powerDao.create(power);

        //Act
        Power readPower = powerDao.retrieve(power.getId());

        //Assert
        assert (readPower.getId() != null);
        assertPowersEqual(power, readPower);
    }

    @Test
    public void updatePower() {

        //Arrange
        Power power = testHelper.createTestPower();
        powerDao.create(power);

        //Change some stuff
        power.setName("Super Strength");

        //Act
        powerDao.update(power);

        //Assert
        Power readPower = powerDao.retrieve(power.getId());
        assert "Super Strength".equals(readPower.getName());

    }

    @Test
    public void deletePower() {

        //Arrange
        Power power = testHelper.createTestPower();
        powerDao.create(power);

        //Act
        assert (power.getId() != null);
        powerDao.delete(power);

        Power readPower = powerDao.retrieve(power.getId());

        //Assert
        assert (null == readPower);//
    }

    @Test
    public void retrievePowersBySuper() {

        //Arrange
        List<Power> powerList = testHelper.createMultiplePowers(15);

        Super superPerson = testHelper.createTestSuper();
        for (int i = 0; i < 10; i++) {
            Power power = testHelper.createTestPower();
            testHelper.createTestSuperPower(superPerson, power);
        }

        //Act
        List<Power> powerList1 = powerDao.retrievePowersBySuper(superPerson, Integer.MAX_VALUE, 0);

        assert powerList1.size() == 10;

//        for (Power power : powerList) {
//            List<Super> superList = powerDao.retrieveSupersByPower(power, Integer.MAX_VALUE, 0);
//
//            boolean exists = false;
//
//            for (Super sup : superList) {
//                if (sup.getId().equals(superPerson.getId()) && sup.getId().equals(superPerson2.getId())) {
//                    exists = true;
//                }
//            }
//            assert exists;
//
//        }
    }

    public void assertPowersEqual(Power power1, Power power2) {
        assert (power1.getName().equals(power2.getName()));
        }

    private void assertPowerListsEqual(List<Power> power1, List<Power> power2) {
        assert power1.size() == power2.size();
        for (Power power : power1) {
            boolean exists = false;
            for (Power originalPower : power2) {
                if (power.getId().equals(originalPower.getId())) {
                    exists = true;
                }
            }
            assert exists == true;
        }
    }
}