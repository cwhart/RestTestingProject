package com.sg.hotelreservations.webservice.impl;

import com.sg.TestHelper;
import com.sg.hotelreservations.config.UnitTestConfiguration;
import com.sg.hotelreservations.dao.daoimpl.AddOnBillDetailDAOImpl;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.AddOnRate;
import com.sg.hotelreservations.dto.Bill;
import com.sg.hotelreservations.dto.RoomBillDetail;
import com.sg.hotelreservations.service.serviceinterface.*;
import com.sg.hotelreservations.viewmodels.bill.AddOnBillDetailViewModel;
import com.sg.hotelreservations.viewmodels.bill.BillViewModel;
import com.sg.hotelreservations.viewmodels.bill.RoomBillDetailViewModel;
import com.sg.hotelreservations.webservice.webinterface.BillWebService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

import javax.inject.Inject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UnitTestConfiguration.class})
@Rollback
@Transactional
@SpringBootTest(classes = {BillWebServiceImpl.class, TestHelper.class})
public class BillWebServiceImplTest {

    @MockBean
    AddOnBillDetailService addOnBillDetailService;

    @MockBean
    RoomBillDetailService roomBillDetailService;

    @MockBean
    BillService billService;

    @MockBean
    RoomRateService roomRateService;

    @MockBean
    RoomService roomService;

    @MockBean
    AddOnRateService addOnRateService;

    @MockBean
    AddOnService addOnService;

    @Inject
    TestHelper testHelper;

    @Inject
    BillWebService billWebService;

    AddOnBillDetail addOnBillDetail1;
    List<AddOnBillDetail> addOnBillDetails = new ArrayList<>();
    Bill bill;
    List<RoomBillDetail> roomBillDetails = new ArrayList<>();

    @Before
    public void setUp() throws Exception {

        bill = testHelper.createTestBill();

        for(int i=0; i<4; i++) {
            AddOnBillDetail addOnBillDetail1 = testHelper.createTestAddOnBillDetailSpecifyBill(bill.getId());
            addOnBillDetails.add(addOnBillDetail1);

        }

        for(int i=0; i<4; i++) {
            RoomBillDetail roomBillDetail = testHelper.createTestRoomBillDetailSpecifyBill(bill.getId());
            roomBillDetails.add(roomBillDetail);

        }
    }

    @Test
    public void getAddOnBillDetailViewModels() {

        //Arrange

        addOnBillDetail1 = addOnBillDetails.get(0);

        when(addOnBillDetailService.retrieveByBillId(anyLong())).thenReturn(addOnBillDetails);
        when(addOnService.retrieve(anyLong())).thenReturn(testHelper.createTestAddOn());
        AddOnRate addOnRate = testHelper.createTestAddOnRate();
        when(addOnRateService.retrieve(anyLong())).thenReturn(addOnRate);

        //Act
        List<AddOnBillDetailViewModel> viewModel = billWebService.getAddOnBillDetailViewModels(bill.getId());

        //Assert
        assertEquals(4, viewModel.size());
        AddOnBillDetailViewModel addOnBillDetailViewModel = viewModel.get(0);
        assertEquals(addOnBillDetailViewModel.getBasePrice(), addOnRate.getPrice().toString());
        assertEquals(addOnBillDetailViewModel.getDiscountedPrice(), addOnBillDetail1.getPrice().toString());
        assertEquals(addOnBillDetailViewModel.getDescription(), addOnBillDetail1.getAddOnRate().getAddOn().getType());
        assertEquals(addOnBillDetailViewModel.getTaxAmount(), addOnBillDetail1.getTaxAmount().toString());
        assertEquals(addOnBillDetailViewModel.getTransactionDate(), addOnBillDetail1.getTransactionDate().toString());

    }

    @Test
    public void getRoomBillDetailViewModels() {

        //Arrange

        RoomBillDetail roomBillDetail1 = roomBillDetails.get(0);

        when(roomBillDetailService.retrieveByBillId(anyLong())).thenReturn(roomBillDetails);
        when(roomService.retrieve(anyLong())).thenReturn(testHelper.createTestRoom());
        when(roomRateService.retrieve(anyLong())).thenReturn(testHelper.createTestRoomRate());

        //Act
        List<RoomBillDetailViewModel> viewModel = billWebService.getRoomBillDetailViewModels(bill.getId());

        //Assert
        assertEquals(4, viewModel.size());
        RoomBillDetailViewModel roomBillDetailViewModel = viewModel.get(0);
        assertEquals(roomBillDetailViewModel.getBasePrice(), roomBillDetail1.getRoomRate().getPrice().toString());
        assertEquals(roomBillDetail1.getPrice(), roomBillDetail1.getPrice());
        assertEquals(roomBillDetailViewModel.getDescription(), roomBillDetail1.getRoomRate().getRoom().getType());
        assertEquals(roomBillDetailViewModel.getTaxAmount(), roomBillDetail1.getTaxAmount().toString());
        assertEquals(roomBillDetailViewModel.getTransactionDate(), roomBillDetail1.getTransactionDate().toString());
    }

    @Test
    public void getBillViewModel() {

        //Arrange
        RoomBillDetail roomBillDetail1 = testHelper.createTestRoomBillDetailSpecifyBill(bill.getId());
        AddOnBillDetail addOnBillDetail1 = testHelper.createTestAddOnBillDetailSpecifyBill(bill.getId());

        when(addOnBillDetailService.retrieveByBillId(anyLong())).thenReturn(addOnBillDetails);
        //when(billService.retrieveByReservationId(anyLong())).thenReturn(bill);
        when(billService.retrieve(any())).thenReturn(bill);
        when(addOnService.retrieve(anyLong())).thenReturn(testHelper.createTestAddOn());
        when(addOnRateService.retrieve(anyLong())).thenReturn(testHelper.createTestAddOnRate());
        when(roomBillDetailService.retrieveByBillId(anyLong())).thenReturn(roomBillDetails);
        when(roomService.retrieve(anyLong())).thenReturn(testHelper.createTestRoom());
        when(roomRateService.retrieve(anyLong())).thenReturn(testHelper.createTestRoomRate());

        //Act
        BillViewModel billViewModel = billWebService.getBillViewModel(bill.getId());

        //Assert
        assertEquals(billViewModel.getAddOnBillDetailViewModels().size(), 4);
        assertEquals(billViewModel.getRoomBillDetailViewModels().size(), 4);
        assertEquals(billViewModel.getReservationHolderName(), "Joe Schmoe");
        BigDecimal roomTotal = roomBillDetail1.getPrice().add(roomBillDetail1.getTaxAmount());
        BigDecimal addOnTotal = addOnBillDetail1.getPrice().add(addOnBillDetail1.getTaxAmount());
        //assertEquals(billViewModel.getTotal(), (roomTotal.add(addOnTotal).multiply(BigDecimal.valueOf(4))).toString());
    }
}