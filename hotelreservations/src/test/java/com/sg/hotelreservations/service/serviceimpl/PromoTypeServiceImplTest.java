package com.sg.hotelreservations.service.serviceimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.dao.daoInterface.PromoTypeDAO;
import com.sg.hotelreservations.dto.PromoType;
import com.sg.hotelreservations.service.serviceinterface.PromoTypeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class PromoTypeServiceImplTest {

    @Inject
    PromoTypeService promoTypeService;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {
        //Arrange
        PromoType promoType = testHelper.createTestPromoType();
        promoTypeService.create(promoType);

        //Act
        PromoType createdPromoType = promoTypeService.create(promoType);

        //Assert
        assert (createdPromoType.getId() != null);
        assertEquals(createdPromoType, promoType);
    }

    @Test
    public void retrieve() {

        //Arrange
        PromoType promoType = testHelper.createTestPromoType();
        promoTypeService.create(promoType);

        //Act
        PromoType readPromoType = promoTypeService.retrieve(promoType.getId());

        //Assert
        assert (readPromoType.getId() != null);
        assertEquals(promoType.getId(), readPromoType.getId());
        assertEquals(promoType.getPromoRate().getId(), readPromoType.getPromoRate().getId());
        assertEquals(promoType.getPromoCode(), readPromoType.getPromoCode());
        assertEquals(promoType.getDescription(), readPromoType.getDescription());

    }

    @Test
    public void update() {

        //Arrange
        PromoType promoType = testHelper.createTestPromoType();
        promoTypeService.create(promoType);

        //Change some stuff
        promoType.setPromoCode("AARPDISC");
        promoType.setDescription("Discount for AARP Members");

        //Act
        promoTypeService.update(promoType);

        //Assert
        PromoType readPromoType = promoTypeService.retrieve(promoType.getId());
        assert "AARPDISC".equals(readPromoType.getPromoCode());
        assert "Discount for AARP Members".equals(readPromoType.getDescription());
    }

    @Test
    public void delete() {

        //Arrange
        PromoType promoType = testHelper.createTestPromoType();
        promoTypeService.create(promoType);

        //Act
        promoTypeService.delete(promoType);

        PromoType readPromoType = promoTypeService.retrieve(promoType.getId());

        //Assert
        assert (null == readPromoType);
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultiplePromoTypes(25);

        //Act
        List<PromoType> promoTypeList = promoTypeService.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert promoTypeList.size() == 25;
    }
}