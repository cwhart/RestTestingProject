package com.sg.hotelreservations.service.serviceimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.config.UnitTestConfiguration;
import com.sg.hotelreservations.dao.daoInterface.PromoRateDAO;
import com.sg.hotelreservations.dao.daoimpl.AddOnBillDetailDAOImpl;
import com.sg.hotelreservations.dto.PromoRate;
import com.sg.hotelreservations.service.serviceinterface.PromoRateService;
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
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UnitTestConfiguration.class})
@Rollback
@Transactional
@SpringBootTest(classes = {PromoRateServiceImpl.class, TestHelper.class})
public class PromoRateServiceImplTest {

    @Inject
    PromoRateService promoRateService;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {

        //Arrange
        PromoRate promoRate = testHelper.createTestPromoRate();
        promoRateService.create(promoRate);

        //Act
        PromoRate createdPromoRate = promoRateService.create(promoRate);


        //Assert
        assert (createdPromoRate.getId() != null);
        assertEquals(createdPromoRate, promoRate);
    }

    @Test
    public void retrieve() {

        //Arrange
        PromoRate promoRate = testHelper.createTestPromoRate();
        promoRateService.create(promoRate);

        //Act
        PromoRate createdPromoRate = promoRateService.retrieve(promoRate.getId());

        //Assert
        assert (createdPromoRate.getId() != null);
        assert(promoRate.getRate().compareTo(createdPromoRate.getRate())==0);
        //assertEquals(promoRate.getRate(), createdPromoRate.getRate());
        assertEquals(promoRate.getType(), createdPromoRate.getType());
    }

    @Test
    public void update() {

        //Arrange
        PromoRate promoRate = testHelper.createTestPromoRate();
        promoRateService.create(promoRate);

        //Change some stuff
        promoRate.setType("$");
        promoRate.setRate(new BigDecimal("12"));

        //Act
        promoRateService.update(promoRate);

        //Assert
        PromoRate readPromoRate = promoRateService.retrieve(promoRate.getId());
        assert "$".equals(readPromoRate.getType());
        assert ("12.00".equals(readPromoRate.getRate().toString()));
    }

    @Test
    public void delete() {

        //Arrange
        PromoRate promoRate = testHelper.createTestPromoRate();
        promoRateService.create(promoRate);

        //Act
        promoRateService.delete(promoRate);

        PromoRate readPromoRate = promoRateService.retrieve(promoRate.getId());

        //Assert
        assert (null == readPromoRate);
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultiplePromoRates(25);

        //Act
        List<PromoRate> promoRateList = promoRateService.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert promoRateList.size() == 25;
    }

}