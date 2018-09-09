package com.sg.hotelreservations.service.serviceimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.dao.daoInterface.RoomBillDetailDAO;
import com.sg.hotelreservations.dto.RoomBillDetail;
import com.sg.hotelreservations.service.serviceinterface.RoomBillDetailService;
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
public class RoomBillDetailServiceImplTest {

    @Inject
    RoomBillDetailService roomBillDetailService;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {
        //Arrange
        RoomBillDetail roomBillDetail = testHelper.createTestRoomBillDetail();
        roomBillDetailService.create(roomBillDetail);

        //Act
        RoomBillDetail createdRoomBillDetail = roomBillDetailService.create(roomBillDetail);

        //Assert
        assert (createdRoomBillDetail.getId() != null);
        assertEquals(createdRoomBillDetail, roomBillDetail);
    }

    @Test
    public void retrieve() {

        //Arrange
        RoomBillDetail roomBillDetail = testHelper.createTestRoomBillDetail();
        roomBillDetailService.create(roomBillDetail);

        //Act
        RoomBillDetail readRoomBillDetail = roomBillDetailService.retrieve(roomBillDetail.getId());

        //Assert
        assert (readRoomBillDetail.getId() != null);
        assertEquals(roomBillDetail.getId(), readRoomBillDetail.getId());
        assertEquals(roomBillDetail.getTax().getId(), readRoomBillDetail.getTax().getId());
        assertEquals(roomBillDetail.getPromo().getId(), readRoomBillDetail.getPromo().getId());
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

        //Act
        List<RoomBillDetail> roomBillDetailList = roomBillDetailService.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert roomBillDetailList.size() == 25;
    }
}