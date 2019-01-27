package com.sg.hotelreservations.service.serviceimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.config.UnitTestConfiguration;
import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoimpl.AddOnBillDetailDAOImpl;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
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
@SpringBootTest(classes = {AddOnBillDetailServiceImpl.class, TestHelper.class})
public class AddOnBillDetailServiceImplTest {

    @Inject
    AddOnBillDetailService addOnBillDetailService;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void createPromoRatePercent() {
        //Arrange
        AddOnBillDetail addOnBillDetail = testHelper.createTestAddOnBillDetail();
        AddOnBillDetail createdAddOnBillDetail = addOnBillDetailService.create(addOnBillDetail);

        AddOnBillDetail retrieved = addOnBillDetailService.retrieve(createdAddOnBillDetail.getId());

        //Assert
        assert (retrieved.getId() != null);
        assertEquals(retrieved.getTax().getId(), addOnBillDetail.getTax().getId());
        assertEquals(retrieved.getPrice(), addOnBillDetail.getPrice());
    }

    @Test
    public void createPromoRateFlat() {
        //Arrange
        AddOnBillDetail addOnBillDetail = testHelper.createTestAddOnBillDetail();
        addOnBillDetail.getPromo().getPromoType().getPromoRate().setType("$");
        addOnBillDetail.getPromo().getPromoType().getPromoRate().setRate(BigDecimal.valueOf(10));
        AddOnBillDetail createdAddOnBillDetail = addOnBillDetailService.create(addOnBillDetail);

        AddOnBillDetail retrieved = addOnBillDetailService.retrieve(createdAddOnBillDetail.getId());

        //Assert
        assert (retrieved.getId() != null);
        assertEquals(BigDecimal.valueOf(911.11), addOnBillDetail.getPrice());
    }

    @Test
    public void retrieve() {

        //Arrange
        AddOnBillDetail addOnBillDetail = testHelper.createTestAddOnBillDetail();
        addOnBillDetailService.create(addOnBillDetail);

        //Act
        AddOnBillDetail readAddOnBillDetail = addOnBillDetailService.retrieve(addOnBillDetail.getId());

        //Assert
        assert (readAddOnBillDetail.getId() != null);
        assertEquals(addOnBillDetail.getId(), readAddOnBillDetail.getId());
        assertEquals(addOnBillDetail.getTax().getId(), readAddOnBillDetail.getTax().getId());
        //assertEquals(addOnBillDetail.getPromo().getId(), readAddOnBillDetail.getPromo().getId());
        assertEquals(addOnBillDetail.getAddOnRate().getId(), readAddOnBillDetail.getAddOnRate().getId());
        assertEquals(addOnBillDetail.getBill().getId(), readAddOnBillDetail.getBill().getId());
        assert(addOnBillDetail.getTaxAmount().compareTo(readAddOnBillDetail.getTaxAmount())==0);
        assert(addOnBillDetail.getPrice().compareTo(readAddOnBillDetail.getPrice())==0);
        assertEquals(addOnBillDetail.getTransactionDate(), readAddOnBillDetail.getTransactionDate());

    }

    @Test
    public void update() {

        //Arrange
        AddOnBillDetail addOnBillDetail = testHelper.createTestAddOnBillDetail();
        addOnBillDetailService.create(addOnBillDetail);

        //Change some stuff
        addOnBillDetail.setPrice(new BigDecimal("515.02"));
        addOnBillDetail.setTaxAmount(new BigDecimal("77.23"));
        addOnBillDetail.setTransactionDate(LocalDate.parse("2018-09-16"));

        //Act
        addOnBillDetailService.update(addOnBillDetail);

        //Assert
        AddOnBillDetail readAddOnBillDetail = addOnBillDetailService.retrieve(addOnBillDetail.getId());
        assert(readAddOnBillDetail.getPrice().compareTo(addOnBillDetail.getPrice())==0);
        assert(readAddOnBillDetail.getTaxAmount().compareTo(addOnBillDetail.getTaxAmount())==0);
        assertEquals(readAddOnBillDetail.getTransactionDate(), addOnBillDetail.getTransactionDate());
    }

    @Test
    public void delete() {

        //Arrange
        AddOnBillDetail addOnBillDetail = testHelper.createTestAddOnBillDetail();
        addOnBillDetailService.create(addOnBillDetail);

        //Act
        addOnBillDetailService.delete(addOnBillDetail);

        AddOnBillDetail readAddOnBillDetail = addOnBillDetailService.retrieve(addOnBillDetail.getId());

        //Assert
        assert (null == readAddOnBillDetail);
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultipleAddOnBillDetails(25);

        //Act
        List<AddOnBillDetail> addOnBillDetailList = addOnBillDetailService.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert addOnBillDetailList.size() == 25;
    }

    @Test
    public void retrieveByBillId() {
        AddOnBillDetail addOnBillDetail = testHelper.createTestAddOnBillDetail();
        Long billId = addOnBillDetail.getBill().getId();

        List<AddOnBillDetail> retrievedAddOnBillDetail = addOnBillDetailService.retrieveByBillId(billId);

        assertEquals(addOnBillDetail.getId(), retrievedAddOnBillDetail.get(0).getId());
    }
}