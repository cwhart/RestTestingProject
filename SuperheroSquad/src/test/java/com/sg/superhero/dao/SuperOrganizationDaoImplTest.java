package com.sg.superhero.dao;

import com.sg.superhero.TestHelper;
import com.sg.superhero.dao.interfaces.PowerDao;
import com.sg.superhero.dao.interfaces.SuperOrganizationDao;
import com.sg.superhero.dto.Power;
import com.sg.superhero.dto.SuperOrganization;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class SuperOrganizationDaoImplTest {

    @Inject
    SuperOrganizationDao superOrganizationDao;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {

        //Arrange
        SuperOrganization superOrg = testHelper.createTestSuperOrganization();
        superOrganizationDao.create(superOrg);

        //Act
        SuperOrganization createdSuperOrg = superOrganizationDao.create(superOrg);


        //Assert
        assert (createdSuperOrg.getId() != null);
        assertSuperOrgsEqual(createdSuperOrg, superOrg);
    }

    @Test
    public void read() {

        //Arrange
        SuperOrganization superOrg = testHelper.createTestSuperOrganization();
        superOrganizationDao.create(superOrg);

        //Act
        SuperOrganization readSuperOrg = superOrganizationDao.retrieve(superOrg.getId());

        //Assert
        assert (readSuperOrg.getId() != null);
        assertSuperOrgsEqual(superOrg, readSuperOrg);
    }

    @Test
    public void delete() {

        SuperOrganization superOrg = testHelper.createTestSuperOrganization();
        superOrganizationDao.create(superOrg);

        //Act
        assert (superOrg.getId() != null);
        superOrganizationDao.delete(superOrg);//

        SuperOrganization readSuperOrg = superOrganizationDao.retrieve(superOrg.getId());

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
            assert exists == true;
        }
    }
}