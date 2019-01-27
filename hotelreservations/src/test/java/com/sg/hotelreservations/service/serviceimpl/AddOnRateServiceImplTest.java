package com.sg.hotelreservations.service.serviceimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.config.UnitTestConfiguration;
import com.sg.hotelreservations.dao.daoimpl.AddOnBillDetailDAOImpl;
import com.sg.hotelreservations.dto.AddOn;
import com.sg.hotelreservations.dto.AddOnRate;
import com.sg.hotelreservations.service.serviceinterface.AddOnRateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
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
@ContextConfiguration(classes = {UnitTestConfiguration.class})
@Rollback
@Transactional
@SpringBootTest(classes = {AddOnRateServiceImpl.class, TestHelper.class})
public class AddOnRateServiceImplTest {

    @Inject
    AddOnRateService addOnRateService;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {
        //Arrange
        AddOnRate addOnRate = testHelper.createTestAddOnRate();
        addOnRateService.create(addOnRate);

        //Act
        AddOnRate createdAddOnRate = addOnRateService.create(addOnRate);

        //Assert
        assert (createdAddOnRate.getId() != null);
        assertEquals(createdAddOnRate, addOnRate);
    }

    @Test
    public void retrieve() {

        //Arrange
        AddOnRate addOnRate = testHelper.createTestAddOnRate();
        addOnRateService.create(addOnRate);

        //Act
        AddOnRate readAddOnRate = addOnRateService.retrieve(addOnRate.getId());

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
        addOnRateService.create(addOnRate);

        //Change some stuff
        addOnRate.setPrice(new BigDecimal("55"));
        addOnRate.setStartDate(LocalDate.parse("2018-07-15"));
        addOnRate.setEndDate(LocalDate.parse("2018-09-15"));
        addOnRate.setAddOn(testHelper.createTestAddOn());

        //Act
        addOnRateService.update(addOnRate);

        //Assert
        AddOnRate readAddOn = addOnRateService.retrieve(addOnRate.getId());
        assert(readAddOn.getPrice().compareTo(addOnRate.getPrice())==0);
        assertEquals(readAddOn.getEndDate(), addOnRate.getEndDate());
        assertEquals(readAddOn.getStartDate(), addOnRate.getStartDate());
        assertEquals(readAddOn.getAddOn().getId(), addOnRate.getAddOn().getId());
    }

    @Test
    public void delete() {

        //Arrange
        AddOnRate addOnRate = testHelper.createTestAddOnRate();
        addOnRateService.create(addOnRate);

        //Act
        addOnRateService.delete(addOnRate);

        AddOnRate readRoom = addOnRateService.retrieve(addOnRate.getId());

        //Assert
        assert (null == readRoom);
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultipleAddOnRates(25);

        //Act
        List<AddOnRate> roomRateList = addOnRateService.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert roomRateList.size() == 25;
    }

    @Test
    public void retrieveByAddOnId() {

        AddOn addOn1 = testHelper.createTestAddOn();
        AddOn addOn2 = testHelper.createTestAddOn();
        AddOnRate addOnRate1 = testHelper.createTestAddOnRateSpecifyAddOn(addOn1.getId());
        AddOnRate addOnRate2 = testHelper.createTestAddOnRateSpecifyAddOn(addOn2.getId());
        AddOnRate addOnRate3 = testHelper.createTestAddOnRateSpecifyAddOn(addOn2.getId());

        List<AddOnRate> addOnRateList = addOnRateService.retrieveByAddOnId(addOn2.getId());

        assertEquals(2, addOnRateList.size());

    }

    @Test
    public void retrieveCurrentRate() {

        AddOn addOn = testHelper.createTestAddOn();
        Long addOnId = addOn.getId();
        AddOnRate addOnRate1 = testHelper.createTestAddOnRateSpecifyDefaultFlagRoomAndDates("D", addOnId
                , LocalDate.parse("2018-01-01"), LocalDate.parse("2018-12-31"));
        AddOnRate addOnRate2 = testHelper.createTestAddOnRateSpecifyDefaultFlagRoomAndDates("S", addOnId
                , LocalDate.parse("2018-09-01"), LocalDate.parse("2018-09-30"));
        AddOnRate addOnRate3 = testHelper.createTestAddOnRateSpecifyDefaultFlagRoomAndDates("S", addOnId
                ,LocalDate.parse("2018-10-01"), LocalDate.parse("2018-10-31"));
        AddOnRate addOnRate4 = testHelper.createTestAddOnRateSpecifyDefaultFlagRoomAndDates("S", addOnId
                , LocalDate.parse("2018-12-01"), LocalDate.parse("2018-12-31"));
        Long defaultRateId = addOnRate1.getId();

        AddOnRate currentRate = addOnRateService.retrieveCurrentRate(addOnId, LocalDate.parse("2018-12-02"));

        assertEquals(addOnRate4.getId(), currentRate.getId());

    }

    @Test
    public void retrieveDefaultRate() {

        AddOn addOn = testHelper.createTestAddOn();
        Long addOnId = addOn.getId();
        AddOnRate addOnRate1 = testHelper.createTestAddOnRateSpecifyDefaultFlagRoomAndDates("D", addOnId
                , LocalDate.parse("2018-01-01"), LocalDate.parse("2018-12-31"));
        AddOnRate addOnRate2 = testHelper.createTestAddOnRateSpecifyDefaultFlagRoomAndDates("S", addOnId
                , LocalDate.parse("2018-09-01"), LocalDate.parse("2018-09-30"));
        AddOnRate addOnRate3 = testHelper.createTestAddOnRateSpecifyDefaultFlagRoomAndDates("S", addOnId
                ,LocalDate.parse("2018-10-01"), LocalDate.parse("2018-10-31"));
        AddOnRate addOnRate4 = testHelper.createTestAddOnRateSpecifyDefaultFlagRoomAndDates("S", addOnId
                , LocalDate.parse("2018-12-01"), LocalDate.parse("2018-12-31"));
        Long defaultRateId = addOnRate1.getId();

        AddOnRate currentRate = addOnRateService.retrieveDefaultRate(addOnId);

        assertEquals(addOnRate1.getId(), currentRate.getId());

    }
}