package com.sg.hotelreservations.webservice.impl;

import com.sg.TestHelper;
import com.sg.hotelreservations.config.UnitTestConfiguration;
import com.sg.hotelreservations.dto.Promo;
import com.sg.hotelreservations.dto.PromoRate;
import com.sg.hotelreservations.dto.PromoType;
import com.sg.hotelreservations.service.serviceinterface.PromoRateService;
import com.sg.hotelreservations.service.serviceinterface.PromoTypeService;
import com.sg.hotelreservations.viewmodels.promo.CreatePromoCommandModel;
import com.sg.hotelreservations.viewmodels.promo.CreatePromoViewModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UnitTestConfiguration.class})
@Rollback
@Transactional
@SpringBootTest(classes = {PromoWebServiceImpl.class, TestHelper.class})
public class PromoWebServiceImplTest {

    @Autowired
    TestHelper testHelper;

    @Autowired
    PromoWebServiceImpl promoWebService;

    @Autowired
    PromoTypeService promoTypeService;

    @Autowired
    PromoRateService promoRateService;

    @Test
    public void getCreatePromoViewModel() {

        testHelper.createMultiplePromoRates(5);
        testHelper.createMultiplePromoTypes(4);

        CreatePromoViewModel createPromoViewModel = promoWebService.getCreatePromoViewModel();

        assertEquals(9, createPromoViewModel.getPromoRates().size());
        assertEquals(4, createPromoViewModel.getPromoTypes().size());
        assertEquals(null, createPromoViewModel.getCommandModel().getStartDate());
        assertEquals(null, createPromoViewModel.getCommandModel().getEndDate());
        assertEquals(null, createPromoViewModel.getCommandModel().getPromoCodeDescription());

    }

    @Test
    public void saveCreatePromoCommandModel() {

        CreatePromoCommandModel commandModel = testHelper.createTestPromoCommandModel();
        PromoType promoType = promoTypeService.retrieve(commandModel.getPromoTypeId());
        PromoRate promoRate = promoRateService.retrieve(promoType.getPromoRate().getId());

        Promo promo = promoWebService.saveCreatePromoCommandModel(commandModel);

        assertEquals(commandModel.getStartDate(), promo.getStartDate().toString());
        assertEquals(commandModel.getEndDate(), promo.getEndDate().toString());
        assertEquals(commandModel.getPromoTypeId(), promo.getPromoType().getId());
        assertEquals(promoType.getPromoCode() + " - " + promoType.getDescription(),
                commandModel.getPromoCodeDescription());
        assertEquals(promoRate.getId(), commandModel.getRateId());

    }

    @Test
    public void getPromoListViewModel() {
    }

    @Test
    public void getEditPromoViewModel() {
    }

    @Test
    public void saveEditPromoCommandModel() {
    }
}