package com.sg.hotelreservations.service.serviceimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.config.UnitTestConfiguration;
import com.sg.hotelreservations.dao.daoInterface.AddOnDAO;
import com.sg.hotelreservations.dao.daoimpl.AddOnBillDetailDAOImpl;
import com.sg.hotelreservations.dto.AddOn;
import com.sg.hotelreservations.service.serviceinterface.AddOnService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UnitTestConfiguration.class})
@Rollback
@Transactional
@SpringBootTest(classes = {AddOnServiceImpl.class, TestHelper.class})
public class AddOnServiceImplTest {

    @Inject
    AddOnService addOnService;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {

        //Arrange
        AddOn addOn = testHelper.createTestAddOn();
        addOnService.create(addOn);

        //Act
        AddOn createdAddOn = addOnService.create(addOn);


        //Assert
        assert (createdAddOn.getId() != null);
        assertEquals(createdAddOn, addOn);
    }

    @Test
    public void retrieve() {

        //Arrange
        AddOn addOn = testHelper.createTestAddOn();
        addOnService.create(addOn);

        //Act
        AddOn readAddOn = addOnService.retrieve(addOn.getId());

        //Assert
        assert (readAddOn.getId() != null);
        assertEquals(addOn, readAddOn);
    }

    @Test
    public void update() {

        //Arrange
        AddOn addOn = testHelper.createTestAddOn();
        addOnService.create(addOn);

        //Change some stuff
        addOn.setType("Movie Rental");

        //Act
        addOnService.update(addOn);

        //Assert
        AddOn readAddOn = addOnService.retrieve(addOn.getId());
        assert "Movie Rental".equals(readAddOn.getType());
    }

    @Test
    public void delete() {

        //Arrange
        AddOn addOn = testHelper.createTestAddOn();
        addOnService.create(addOn);

        //Act
        addOnService.delete(addOn);

        AddOn readAddOn = addOnService.retrieve(addOn.getId());

        //Assert
        assert (null == readAddOn);
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultipleAddOns(25);

        //Act
        List<AddOn> addOnList = addOnService.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert addOnList.size() == 25;
    }
}