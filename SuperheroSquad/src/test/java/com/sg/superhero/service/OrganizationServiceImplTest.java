package com.sg.superhero.service;

import com.sg.superhero.TestHelper;
import com.sg.superhero.dao.interfaces.OrganizationDao;
import com.sg.superhero.dto.Organization;
import com.sg.superhero.dto.Super;
import com.sg.superhero.service.interfaces.OrganizationService;
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
public class OrganizationServiceImplTest {

    @Inject
    OrganizationService organizationService;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {

        //Arrange
        Organization organization = testHelper.createTestOrganization();
        organizationService.create(organization);

        //Act
        Organization createdOrganization = organizationService.create(organization);


        //Assert
        assert (createdOrganization.getId() != null);
        assertOrganizationsEqual(createdOrganization, organization);
    }

    @Test
    public void retrieve() {

        //Arrange
        Organization organization = testHelper.createTestOrganization();
        organizationService.create(organization);

        //Act
        Organization readOrganization = organizationService.retrieve(organization.getId());

        //Assert
        assert (readOrganization.getId() != null);
        assertOrganizationsEqual(organization, readOrganization);
    }

    @Test
    public void update() {

        //Arrange
        Organization organization = testHelper.createTestOrganization();
        organizationService.create(organization);

        //Change some stuff
        organization.setName("The Anti Justice League");
        organization.setDescription("Supervillain Organization");

        //Act
        organizationService.update(organization);

        //Assert
        Organization readOrganization = organizationService.retrieve(organization.getId());
        assert "The Anti Justice League".equals(readOrganization.getName());
        assert "Supervillain Organization".equals(readOrganization.getDescription());
    }

    @Test
    public void delete() {

        //Arrange
        Organization organization = testHelper.createTestOrganization();
        organizationService.create(organization);

        //Act
        assert (organizationService.retrieve(organization.getId()) != null);
        organizationService.delete(organization);

        Organization readOrganization = organizationService.retrieve(organization.getId());

        //Assert
        assert (null == readOrganization);//
    }

    @Test
    public void retrieveAll() {

        //Arrange
        List<Organization> orgList = testHelper.createMultipleOrganizations(25);

        //Act
        List<Organization> readOrgList = organizationService.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert orgList.size() == 25;
        assertOrganizationListEqual(readOrgList, orgList);
    }

    @Test
    public void getOrganizationsBySuper() {

        //Arrange
        List<Organization> organizationList = testHelper.createMultipleOrganizations(15);

        Super superPerson = testHelper.createTestSuper();
        for (int i = 0; i < 10; i++) {
            Organization organization = testHelper.createTestOrganization();
            testHelper.createTestSuperOrganization(superPerson, organization);
        }

        //Act
        List<Organization> orgList = organizationService.retrieveOrganizationsBySuper(superPerson, Integer.MAX_VALUE, 0);

        assert orgList.size() == 10;

//        for (Organization organization : organizationList) {
//            List<Super> superList = superDao.retrieveSupersByOrganization(organization, Integer.MAX_VALUE, 0);
//
//            boolean exists = false;
//
//            for (Super sup : superList) {
//                if (sup.getId().equals(superPerson.getId()) && sup.getId().equals(superPerson2.getId())) {
//                    exists = true;
//                }
//            }
//            assert exists;
//
//        }
    }

    public void assertOrganizationsEqual(Organization org1, Organization org2) {
        assert (org1.getName().equals(org2.getName()));
        assert (org1.getDescription().equals(org2.getDescription()));
        assert (org1.getLocation().getId().equals(org2.getLocation().getId()));
        assert (org1.getEmail().equals(org2.getEmail()));
        assert (org1.getPhone().equals(org2.getPhone()));

    }

    private void assertOrganizationListEqual(List<Organization> organization1, List<Organization> organization2) {
        assert organization1.size() == organization2.size();
        for (Organization organization : organization1) {
            boolean exists = false;
            for (Organization originalOrganization : organization2) {
                if (organization.getId().equals(originalOrganization.getId())) {
                    exists = true;
                }
            }
            assert exists == true;
        }
    }
}