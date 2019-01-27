package com.sg.hotelreservations.dao.daoimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.config.UnitTestConfiguration;
import com.sg.hotelreservations.dao.daoInterface.AddOnDAO;
import com.sg.hotelreservations.dao.daoInterface.PromoDAO;
import com.sg.hotelreservations.dto.AddOn;
import com.sg.hotelreservations.dto.Promo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UnitTestConfiguration.class})
@Rollback
@Transactional
@SpringBootTest(classes = {PromoDAOImpl.class, TestHelper.class})
public class PromoDAOImplTest {

    @Inject
    PromoDAO promoDAO;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {

        //Arrange
        Promo promo = testHelper.createTestPromo();
        promoDAO.create(promo);

        //Act
        Promo createdPromo = promoDAO.create(promo);


        //Assert
        assert (createdPromo.getId() != null);
        assertEquals(createdPromo, promo);
    }

    @Test
    public void retrieve() {

        //Arrange
        Promo promo = testHelper.createTestPromo();
        promoDAO.create(promo);

        //Act
        Promo readPromo = promoDAO.retrieve(promo.getId());

        //Assert
        assert (readPromo.getId() != null);
        assertEquals(promo.getPromoType().getId(), readPromo.getPromoType().getId());
        assertEquals(promo.getStartDate(), readPromo.getStartDate());
        assertEquals(promo.getEndDate(), promo.getEndDate());
    }

    @Test
    public void retrieveByPromoTypeId() {

        //Arrange
        Promo promo = testHelper.createTestPromo();
        //promoDAO.create(promo);

        //Act
        List<Promo> readPromos = promoDAO.retrieveByPromoTypeId(promo.getPromoType().getId());
        Promo readPromo = readPromos.get(0);

        //Assert
        assert (readPromo.getId() != null);
        assertEquals(promo.getPromoType().getId(), readPromo.getPromoType().getId());
        assertEquals(promo.getStartDate(), readPromo.getStartDate());
        assertEquals(promo.getEndDate(), promo.getEndDate());
    }

    @Test
    public void update() {

        //Arrange
        Promo promo = testHelper.createTestPromo();

        //Change some stuff
        promo.setStartDate(LocalDate.parse("2018-07-01"));
        promo.setEndDate(LocalDate.parse("2018-12-31"));

        //Act
        promoDAO.update(promo);

        //Assert
        Promo readPromo = promoDAO.retrieve(promo.getId());
        assert "2018-07-01".equals(readPromo.getStartDate().toString());
        assert "2018-12-31".equals(readPromo.getEndDate().toString());
    }

    @Test
    public void delete() {

        //Arrange
        Promo promo = testHelper.createTestPromo();
        promoDAO.create(promo);

        //Act
        promoDAO.delete(promo);

        Promo readPromo = promoDAO.retrieve(promo.getId());

        //Assert
        assert (null == readPromo);
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultiplePromos(25);

        //Act
        List<Promo> promoList = promoDAO.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert promoList.size() == 25;
    }
}