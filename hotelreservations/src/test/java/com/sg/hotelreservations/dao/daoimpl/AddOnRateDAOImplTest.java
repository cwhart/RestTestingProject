package com.sg.hotelreservations.dao.daoimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.dao.daoInterface.AddOnRateDAO;
import com.sg.hotelreservations.dao.daoInterface.RoomRateDAO;
import com.sg.hotelreservations.dto.AddOnRate;
import com.sg.hotelreservations.dto.RoomRate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class AddOnRateDAOImplTest {

    @Inject
    AddOnRateDAO addOnRateDAO;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {
        //Arrange
        AddOnRate addOnRate = testHelper.createTestAddOnRate();
        addOnRateDAO.create(addOnRate);

        //Act
        AddOnRate createdAddOnRate = addOnRateDAO.create(addOnRate);

        //Assert
        assert (createdAddOnRate.getId() != null);
        assertEquals(createdAddOnRate, addOnRate);
    }

    @Test
    public void retrieve() {

        //Arrange
        AddOnRate addOnRate = testHelper.createTestAddOnRate();
        addOnRateDAO.create(addOnRate);

        //Act
        AddOnRate readAddOnRate = addOnRateDAO.retrieve(addOnRate.getId());

        //Assert
        assert (readAddOnRate.getId() != null);
        assertEquals(addOnRate.getId(), readAddOnRate.getId());
        assertEquals(addOnRate.getAddOn().getId(), readAddOnRate.getAddOn().getId());
        assertEquals(addOnRate.getStartDate(), readAddOnRate.getStartDate());
        assertEquals(addOnRate.getEndDate(), readAddOnRate.getEndDate());
        assert(addOnRate.getPrice().compareTo(readAddOnRate.getPrice())==0);

    }

    @Test
    public void update() {

        //Arrange
        AddOnRate addOnRate = testHelper.createTestAddOnRate();
        addOnRateDAO.create(addOnRate);

        //Change some stuff
        addOnRate.setPrice(new BigDecimal("55"));
        addOnRate.setStartDate(LocalDate.parse("2018-07-15"));
        addOnRate.setEndDate(LocalDate.parse("2018-09-15"));
        addOnRate.setAddOn(testHelper.createTestAddOn());

        //Act
        addOnRateDAO.update(addOnRate);

        //Assert
        AddOnRate readAddOn = addOnRateDAO.retrieve(addOnRate.getId());
        assert(readAddOn.getPrice().compareTo(addOnRate.getPrice())==0);
        assertEquals(readAddOn.getEndDate(), addOnRate.getEndDate());
        assertEquals(readAddOn.getStartDate(), addOnRate.getStartDate());
        assertEquals(readAddOn.getAddOn().getId(), addOnRate.getAddOn().getId());
    }

    @Test
    public void delete() {

        //Arrange
        AddOnRate addOnRate = testHelper.createTestAddOnRate();
        addOnRateDAO.create(addOnRate);

        //Act
        addOnRateDAO.delete(addOnRate);

        AddOnRate readRoom = addOnRateDAO.retrieve(addOnRate.getId());

        //Assert
        assert (null == readRoom);
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultipleAddOnRates(25);

        //Act
        List<AddOnRate> roomRateList = addOnRateDAO.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert roomRateList.size() == 25;
    }
}