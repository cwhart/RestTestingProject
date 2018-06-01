package com.sg.superhero.service;

import com.sg.superhero.TestHelper;
import com.sg.superhero.dao.interfaces.SuperOrganizationDao;
import com.sg.superhero.dto.SuperOrganization;
import com.sg.superhero.service.interfaces.SuperOrganizationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class SuperOrganizationServiceImplTest {

    @Inject
    SuperOrganizationService superOrganizationService;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {

        //Arrange
        SuperOrganization superOrg = testHelper.createTestSuperOrganization();
        superOrganizationService.create(superOrg);

        //Act
        SuperOrganization createdSuperOrg = superOrganizationService.create(superOrg);


        //Assert
        assert (createdSuperOrg.getId() != null);
        assertSuperOrgsEqual(createdSuperOrg, superOrg);
    }

    @Test
    public void read() {

        //Arrange
        SuperOrganization superOrg = testHelper.createTestSuperOrganization();
        superOrganizationService.create(superOrg);

        //Act
        SuperOrganization readSuperOrg = superOrganizationService.retrieve(superOrg.getId());

        //Assert
        assert (readSuperOrg.getId() != null);
        assertSuperOrgsEqual(superOrg, readSuperOrg);
    }

    @Test
    public void delete() {

        SuperOrganization superOrg = testHelper.createTestSuperOrganization();
        superOrganizationService.create(superOrg);

        //Act
        assert (superOrganizationService.retrieve(superOrg.getId()) != null);
        superOrganizationService.delete(superOrg);

        SuperOrganization readSuperOrg = superOrganizationService.retrieve(superOrg.getId());

        //Assert
        assert (null == readSuperOrg);
    }

    public void assertSuperOrgsEqual(SuperOrganization superOrg1, SuperOrganization superOrg2) {
        assert (superOrg1.getOrganization().getId().equals(superOrg2.getOrganization().getId()));
        assert (superOrg1.getSuperPerson().getId().equals(superOrg2.getSuperPerson().getId()));
    }

    private void assertSuperOrgListsEqual(List<SuperOrganization> superOrg1, List<SuperOrganization> superOrg2) {
        assert superOrg1.size() == superOrg2.size();
        for (SuperOrganization superOrg : superOrg1) {
            boolean exists = false;
            for (SuperOrganization originalSuperOrg : superOrg2) {
                if (superOrg.getId().equals(originalSuperOrg.getId())) {
                    exists = true;
                }
            }
            assert exists == true;//
        }
    }
}