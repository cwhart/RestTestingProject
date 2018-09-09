package com.sg.hotelreservations.dao.daoimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.dao.daoInterface.AddOnDAO;
import com.sg.hotelreservations.dao.daoInterface.PromoRateDAO;
import com.sg.hotelreservations.dto.AddOn;
import com.sg.hotelreservations.dto.PromoRate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class PromoRateDAOImplTest {

    @Inject
    PromoRateDAO promoRateDAO;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {

        //Arrange
        PromoRate promoRate = testHelper.createTestPromoRate();
        promoRateDAO.create(promoRate);

        //Act
        PromoRate createdPromoRate = promoRateDAO.create(promoRate);


        //Assert
        assert (createdPromoRate.getId() != null);
        assertEquals(createdPromoRate, promoRate);
    }

    @Test
    public void retrieve() {

        //Arrange
        PromoRate promoRate = testHelper.createTestPromoRate();
        promoRateDAO.create(promoRate);

        //Act
        PromoRate createdPromoRate = promoRateDAO.retrieve(promoRate.getId());

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
        promoRateDAO.create(promoRate);

        //Change some stuff
        promoRate.setType("$");
        promoRate.setRate(new BigDecimal("12"));

        //Act
        promoRateDAO.update(promoRate);

        //Assert
        PromoRate readPromoRate = promoRateDAO.retrieve(promoRate.getId());
        assert "$".equals(readPromoRate.getType());
        assert ("12.00".equals(readPromoRate.getRate().toString()));
    }

    @Test
    public void delete() {

        //Arrange
        PromoRate promoRate = testHelper.createTestPromoRate();
        promoRateDAO.create(promoRate);

        //Act
        promoRateDAO.delete(promoRate);

        PromoRate readPromoRate = promoRateDAO.retrieve(promoRate.getId());

        //Assert
        assert (null == readPromoRate);
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultiplePromoRates(25);

        //Act
        List<PromoRate> promoRateList = promoRateDAO.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert promoRateList.size() == 25;
    }

}