package com.sg.hotelreservations.service.serviceimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.config.UnitTestConfiguration;
import com.sg.hotelreservations.dao.daoInterface.RoomBillDetailDAO;
import com.sg.hotelreservations.dao.daoimpl.AddOnBillDetailDAOImpl;
import com.sg.hotelreservations.dto.PromoRate;
import com.sg.hotelreservations.dto.RoomBillDetail;
import com.sg.hotelreservations.service.serviceinterface.PromoRateService;
import com.sg.hotelreservations.service.serviceinterface.RoomBillDetailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UnitTestConfiguration.class})
@Rollback
@Transactional
@SpringBootTest(classes = {RoomBillDetailServiceImpl.class, TestHelper.class})
public class RoomBillDetailServiceImplTest {

    @Inject
    RoomBillDetailService roomBillDetailService;

    @Inject
    TestHelper testHelper;

    @MockBean
    PromoRateService promoRateService;

    PromoRate promoRate;

    @Before
    public void setUp() throws Exception {

        promoRate = testHelper.createTestPromoRate();
    }

    @Test
    public void createPromoRatePercent() {
        //Arrange
        RoomBillDetail roomBillDetail = testHelper.createTestRoomBillDetail();
        promoRate.setType("%");
        promoRate.setRate(BigDecimal.valueOf(15));
        when(promoRateService.retrieve(anyLong())).thenReturn(promoRate);
        RoomBillDetail createdRoomBillDetail = roomBillDetailService.create(roomBillDetail);

        RoomBillDetail retrieved = roomBillDetailService.retrieve(createdRoomBillDetail.getId());

        //Assert
        assert (retrieved.getId() != null);
        assertEquals(retrieved.getTax().getId(), roomBillDetail.getTax().getId());
        assertEquals(retrieved.getPrice(), roomBillDetail.getPrice());
    }

    @Test
    public void createPromoRateFlat() {
        //Arrange
        RoomBillDetail roomBillDetail = testHelper.createTestRoomBillDetail();
        promoRate.setType("$");
        promoRate.setRate(BigDecimal.valueOf(10));
        when(promoRateService.retrieve(anyLong())).thenReturn(promoRate);
        RoomBillDetail createdRoomBillDetail = roomBillDetailService.create(roomBillDetail);

        RoomBillDetail retrieved = roomBillDetailService.retrieve(createdRoomBillDetail.getId());

        //Assert
        assert (retrieved.getId() != null);
        assertEquals(BigDecimal.valueOf(911.11), roomBillDetail.getPrice());
    }

    @Test
    public void retrieve() {

        //Arrange
        RoomBillDetail roomBillDetail = testHelper.createTestRoomBillDetail();
        when(promoRateService.retrieve(anyLong())).thenReturn(promoRate);

        roomBillDetailService.create(roomBillDetail);

        //Act
        RoomBillDetail readRoomBillDetail = roomBillDetailService.retrieve(roomBillDetail.getId());

        //Assert
        assert (readRoomBillDetail.getId() != null);
        assertEquals(roomBillDetail.getId(), readRoomBillDetail.getId());
        assertEquals(roomBillDetail.getTax().getId(), readRoomBillDetail.getTax().getId());
        //assertEquals(roomBillDetail.getPromo().getId(), readRoomBillDetail.getPromo().getId());
        assertEquals(roomBillDetail.getRoomRate().getId(), readRoomBillDetail.getRoomRate().getId());
        assertEquals(roomBillDetail.getBill().getId(), readRoomBillDetail.getBill().getId());
        assert(roomBillDetail.getTaxAmount().compareTo(readRoomBillDetail.getTaxAmount())==0);
        assert(roomBillDetail.getPrice().compareTo(readRoomBillDetail.getPrice())==0);
        assertEquals(roomBillDetail.getTransactionDate(), readRoomBillDetail.getTransactionDate());

    }

    @Test
    public void update() {

        //Arrange
        RoomBillDetail roomBillDetail = testHelper.createTestRoomBillDetail();
        when(promoRateService.retrieve(anyLong())).thenReturn(promoRate);

        roomBillDetailService.create(roomBillDetail);

        //Change some stuff
        roomBillDetail.setPrice(new BigDecimal("515.02"));
        roomBillDetail.setTaxAmount(new BigDecimal("77.23"));
        roomBillDetail.setTransactionDate(LocalDate.parse("2018-09-16"));

        //Act
        roomBillDetailService.update(roomBillDetail);

        //Assert
        RoomBillDetail readRoomBillDetail = roomBillDetailService.retrieve(roomBillDetail.getId());
        assert(readRoomBillDetail.getPrice().compareTo(roomBillDetail.getPrice())==0);
        assert(readRoomBillDetail.getTaxAmount().compareTo(roomBillDetail.getTaxAmount())==0);
        assertEquals(readRoomBillDetail.getTransactionDate(), roomBillDetail.getTransactionDate());
    }

    @Test
    public void delete() {

        //Arrange
        RoomBillDetail roomBillDetail = testHelper.createTestRoomBillDetail();
        when(promoRateService.retrieve(anyLong())).thenReturn(promoRate);

        roomBillDetailService.create(roomBillDetail);

        //Act
        roomBillDetailService.delete(roomBillDetail);

        RoomBillDetail readRoomBillDetail = roomBillDetailService.retrieve(roomBillDetail.getId());

        //Assert
        assert (null == readRoomBillDetail);
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultipleRoomBillDetails(25);
        when(promoRateService.retrieve(anyLong())).thenReturn(promoRate);

        //Act
        List<RoomBillDetail> roomBillDetailList = roomBillDetailService.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert roomBillDetailList.size() == 25;
    }

    @Test
    public void retrieveByBillId() {

        //Arrange
        RoomBillDetail roomBillDetail = testHelper.createTestRoomBillDetail();
        when(promoRateService.retrieve(anyLong())).thenReturn(promoRate);

        roomBillDetailService.create(roomBillDetail);

        //Act
        List<RoomBillDetail> readRoomBillDetails = roomBillDetailService.retrieveByBillId(roomBillDetail.getBill().getId());

        //Assert
        assert (readRoomBillDetails.size() != 0);
        RoomBillDetail testRoomBillDetail = readRoomBillDetails.get(0);
        //assertEquals(roomBillDetail.getId(), testRoomBillDetail.getId());
        assertEquals(roomBillDetail.getTax().getId(), testRoomBillDetail.getTax().getId());
        //assertEquals(roomBillDetail.getPromo().getId(), readRoomBillDetail.getPromo().getId());
        assertEquals(roomBillDetail.getRoomRate().getId(), testRoomBillDetail.getRoomRate().getId());
        assertEquals(roomBillDetail.getBill().getId(), testRoomBillDetail.getBill().getId());
        assert(roomBillDetail.getTaxAmount().compareTo(testRoomBillDetail.getTaxAmount())==0);
        assertEquals(roomBillDetail.getPrice(), testRoomBillDetail.getPrice().multiply(BigDecimal.valueOf(.85)).setScale(2, RoundingMode.HALF_UP));
        assertEquals(roomBillDetail.getTransactionDate(), testRoomBillDetail.getTransactionDate());

    }
}