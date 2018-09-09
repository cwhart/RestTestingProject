package com.sg.hotelreservations.dao.daoimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.dao.daoInterface.AddOnDAO;
import com.sg.hotelreservations.dto.AddOn;
import org.junit.Before;
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
public class AddOnDAOImplTest {

    @Inject
    AddOnDAO addOnDAO;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {

        //Arrange
        AddOn addOn = testHelper.createTestAddOn();
        addOnDAO.create(addOn);

        //Act
        AddOn createdAddOn = addOnDAO.create(addOn);


        //Assert
        assert (createdAddOn.getId() != null);
        assertEquals(createdAddOn, addOn);
    }

    @Test
    public void retrieve() {

        //Arrange
        AddOn addOn = testHelper.createTestAddOn();
        addOnDAO.create(addOn);

        //Act
        AddOn readAddOn = addOnDAO.retrieve(addOn.getId());

        //Assert
        assert (readAddOn.getId() != null);
        assertEquals(addOn, readAddOn);
    }

    @Test
    public void update() {

        //Arrange
        AddOn addOn = testHelper.createTestAddOn();
        addOnDAO.create(addOn);

        //Change some stuff
        addOn.setType("Movie Rental");

        //Act
        addOnDAO.update(addOn);

        //Assert
        AddOn readAddOn = addOnDAO.retrieve(addOn.getId());
        assert "Movie Rental".equals(readAddOn.getType());
    }

    @Test
    public void delete() {

        //Arrange
        AddOn addOn = testHelper.createTestAddOn();
        addOnDAO.create(addOn);

        //Act
        addOnDAO.delete(addOn);

        AddOn readAddOn = addOnDAO.retrieve(addOn.getId());

        //Assert
        assert (null == readAddOn);
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultipleAddOns(25);

        //Act
        List<AddOn> addOnList = addOnDAO.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert addOnList.size() == 25;
    }
}