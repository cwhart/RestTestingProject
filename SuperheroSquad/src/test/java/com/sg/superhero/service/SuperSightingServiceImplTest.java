package com.sg.superhero.service;

import com.sg.superhero.TestHelper;
import com.sg.superhero.dao.interfaces.SuperSightingDao;
import com.sg.superhero.dto.SuperSighting;
import com.sg.superhero.service.interfaces.SuperSightingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class SuperSightingServiceImplTest {

    @Inject
    SuperSightingService superSightingService;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {

        //Arrange
        SuperSighting superSighting = testHelper.createTestSuperSighting();
        superSightingService.create(superSighting);

        //Act
        SuperSighting createdSuperSighting = superSightingService.create(superSighting);


        //Assert
        assert (createdSuperSighting.getId() != null);
        assertSuperSightingEquals(createdSuperSighting, superSighting);
    }

    @Test
    public void retrieve() {

        //Arrange
        SuperSighting superSighting = testHelper.createTestSuperSighting();
        superSightingService.create(superSighting);

        //Act
        SuperSighting readSuperSighting = superSightingService.retrieve(superSighting.getId());

        //Assert
        assert (readSuperSighting.getId() != null);
        assertSuperSightingEquals(superSighting, readSuperSighting);
    }

    @Test
    public void delete() {

        SuperSighting superSighting = testHelper.createTestSuperSighting();
        superSightingService.create(superSighting);

        //Act
        assert (superSightingService.retrieve(superSighting.getId()) != null);
        superSightingService.delete(superSighting);

        SuperSighting readSuperSighting = superSightingService.retrieve(superSighting.getId());

        //Assert
        assert (null == readSuperSighting);//
    }

    private void assertSuperSightingEquals(SuperSighting superSighting, SuperSighting superSighting2) {
        assert superSighting.getId().equals(superSighting2.getId());
        assert superSighting.getSuperPerson().getId().equals(superSighting2.getSuperPerson().getId());
        assert superSighting.getSighting().getId().equals(superSighting2.getSighting().getId());
    }
}