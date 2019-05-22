package com.sg.hotelreservations.webservice.impl;

import com.sg.TestHelper;
import com.sg.hotelreservations.config.UnitTestConfiguration;
import com.sg.hotelreservations.dao.daoimpl.AddOnBillDetailDAOImpl;
import com.sg.hotelreservations.dto.*;
import com.sg.hotelreservations.service.serviceinterface.*;
import com.sg.hotelreservations.viewmodels.addon.AddOnViewModel;
import com.sg.hotelreservations.webservice.webinterface.AddOnWebService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

import javax.inject.Inject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UnitTestConfiguration.class})
@Rollback
@Transactional
@SpringBootTest(classes = {AddOnWebServiceImpl.class, TestHelper.class})
public class AddOnWebServiceImplTest {

    @Inject
    AddOnWebService addOnWebService;

    @MockBean
    AddOnService addOnService;

    @MockBean
    AddOnRateService addOnRateService;

    @MockBean
    BillService billService;

    @MockBean
    TaxService taxService;

    @Autowired
    AddOnBillDetailService addOnBillDetailService;


    @Inject
    TestHelper testHelper;

    @Test
    public void getListAddOnViewModel() {

        List<AddOn> addOns = new ArrayList<>();

        for(int i=0; i<8; i++) {
            addOns.add(testHelper.createTestAddOn());
        }

        List<AddOnRate> addOnRateList = new ArrayList<>();

        for (int i=0; i<2; i++) {
            addOnRateList.add(testHelper.createTestAddOnRate());
        }

        when(addOnRateService.retrieveByAddOnId(anyLong())).thenReturn(addOnRateList);
        when(addOnService.retrieveAll(anyInt(), anyInt())).thenReturn(addOns);

        List<AddOnViewModel> addOnList = addOnWebService.getListAddOnViewModel(0);

        assertEquals(8, addOnList.size());
        assertEquals("Pedicure", addOnList.get(0).getType());
    }


    @Test
    public void addAddOnToBill() {

        List<Tax> taxes = testHelper.createMultipleTaxes(3);

        AddOnRate addOnRate = testHelper.createTestAddOnRate();
//
//        when(addOnRateService.retrieveByAddOnId(anyLong())).thenReturn(addOnRateList);
//        when(addOnService.retrieveAll(anyInt(), anyInt())).thenReturn(addOns);

        AddOn addOn = testHelper.createTestAddOn();
        Bill bill = testHelper.createTestBill();

        when(billService.retrieve(anyLong())).thenReturn(bill);
        when(addOnRateService.retrieveCurrentRate(anyLong(), any())).thenReturn(addOnRate);
        when(taxService.retrieveByState(anyString())).thenReturn(taxes);

        AddOnBillDetail addOnBillDetail = addOnWebService.addAddOnToBill(addOn.getId(), bill.getId());

        assertEquals(BigDecimal.valueOf(1.25), addOnBillDetail.getTaxAmount().setScale(2));
        assertEquals(BigDecimal.valueOf(25), addOnBillDetail.getPrice().setScale(0));
        assertEquals(LocalDate.now(), addOnBillDetail.getTransactionDate());
        assertEquals(bill.getId(), addOnBillDetail.getBill().getId());
        assert(addOnBillDetail.getAddOnRate().getId() != null);
        assertEquals("NH Meals Tax", addOnBillDetail.getTax().getType());
    }

    @Test
    public void deleteAddOn() {

    }
}