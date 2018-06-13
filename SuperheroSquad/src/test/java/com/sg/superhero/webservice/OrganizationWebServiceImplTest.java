package com.sg.superhero.webservice;

import com.sg.superhero.TestHelper;
import com.sg.superhero.dto.Location;
import com.sg.superhero.dto.Organization;
import com.sg.superhero.viewmodels.DropdownViewModel;
import com.sg.superhero.viewmodels.location.profile.ProfileLocationViewModel;
import com.sg.superhero.viewmodels.organization.create.CreateOrganizationCommandModel;
import com.sg.superhero.viewmodels.organization.create.CreateOrganizationViewModel;
import com.sg.superhero.viewmodels.organization.edit.EditOrganizationCommandModel;
import com.sg.superhero.viewmodels.organization.edit.EditOrganizationViewModel;
import com.sg.superhero.viewmodels.organization.list.ListOrganizationViewModel;
import com.sg.superhero.viewmodels.organization.list.OrganizationViewModel;
import com.sg.superhero.viewmodels.organization.profile.ProfileOrganizationViewModel;
import com.sg.superhero.webservice.interfaces.OrganizationWebService;
import org.aspectj.weaver.ast.Or;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)  //says Spring in charge of running unit tests.
@ContextConfiguration(locations = {"/test-applicationContext.xml"}) //indicates the xml file to use
@Rollback //Automatically all tests will be transactional, and when complete, won't be committed; will be rolled back.
//need @Rollback and @Transactional annotations both.
@Transactional
public class OrganizationWebServiceImplTest {

    @Inject
    OrganizationWebService organizationWebService;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getListOrganizationViewModel() {

        //Arrange
        Integer offset = 0;

        List<Organization> orgs = new ArrayList<>();
        //Location location = testHelper.createTestLocation();
        for (int i=0; i<25; i++) {
            Organization organization = testHelper.createTestOrganization();
            orgs.add(organization);
        }

        //Act
        ListOrganizationViewModel viewModel = organizationWebService.getListOrganizationViewModel(0);

        //Assert
        assert viewModel.getSelectedPage() == 1;
        assert viewModel.getPageNumbers().length == 5;
        assert viewModel.getPageNumbers()[0] == 1;
        assert viewModel.getPageNumbers()[4] == 5;

        assert viewModel.getOrganizations() != null;
        assert viewModel.getOrganizations().size() == 10;

        int counter=0;
        for (OrganizationViewModel oViewModel : viewModel.getOrganizations()) {
            Organization organization = orgs.get(counter);
            assert oViewModel.getName().equals((organization.getName()));
            counter++;
        }
    }

    @Test
    public void getOrganizationProfileViewModel() {

        //Arrange
        Location location = testHelper.createTestLocation();
        Organization organization = testHelper.createTestOrganization(location);

        //Act
        ProfileOrganizationViewModel viewModel = organizationWebService.getOrganizationProfileViewModel(organization.getId());

        //Assert
        assert organization.getPhone().equals(viewModel.getPhone());
        assert organization.getEmail().equals(viewModel.getEmail());
        assert organization.getDescription().equals(viewModel.getDescription());
        assert organization.getName().equals(viewModel.getName());
        assert organization.getLocation().getName().equals(viewModel.getLocation());


    }

//    @Test
//    public void getCreateOrganizationViewModel() {
//        //Arrange
//        List<Location> locations = testHelper.createMultipleLocations(15);
//
//        //Act
//        CreateOrganizationViewModel createOrganizationViewModel = organizationWebService.getCreateOrganizationViewModel();
//
//        //Assert
//        assert createOrganizationViewModel.getLocations().size() == locations.size();
//
//        for (DropdownViewModel createLocationViewModel : createOrganizationViewModel.getLocations()) {
//            assert createLocationViewModel.getName() != null;
//            assert createLocationViewModel.getId() != null;
//
//        }
//    }

    @Test
    public void saveCreateOrganizationCommandModel() {

        //Arrange
        Location loc = testHelper.createTestLocation();
        Organization org = testHelper.createTestOrganization(loc);
        CreateOrganizationCommandModel cmd = new CreateOrganizationCommandModel();
        cmd.setName(org.getName());
        cmd.setDescription(org.getDescription());
        cmd.setEmail(org.getEmail());
        cmd.setPhone(org.getPhone());
        cmd.setLocation(loc.getName());
        cmd.setLocationId(loc.getId());

        //Act
        Organization organization = organizationWebService.saveCreateOrganizationCommandModel(cmd);

        //Assert
        assert organization.getId() != null;
        assert organization.getLocation().getName().equals("The Daily Planet");
        assert organization.getLocation().getId().equals(loc.getId());
        assert organization.getName().equals("Justice League");
        assert organization.getDescription().equals("Superhero Organization");
        assert organization.getEmail().equals("justice@jl.com");
        assert organization.getPhone().equals("867-5309");
    }

//    @Test
//    public void getEditOrganizationViewModel() {
//        //Arrange
//        List<Location> locations = testHelper.createMultipleLocations(15);
//        Organization organization = testHelper.createTestOrganization(locations.get(0));
//
//
//        //Act
//        EditOrganizationViewModel editOrganizationViewModel =
//                organizationWebService.getEditOrganizationViewModel(organization.getId());
//
//        //Assert
//        assert editOrganizationViewModel.getLocations().size() == locations.size();
//        assert editOrganizationViewModel.getCommandModel().getName().equals(organization.getName());
//        assert editOrganizationViewModel.getCommandModel().getDescription().equals(organization.getDescription());
//        assert editOrganizationViewModel.getCommandModel().getEmail().equals(organization.getEmail());
//        assert editOrganizationViewModel.getCommandModel().getPhone().equals(organization.getPhone());
//        assert editOrganizationViewModel.getCommandModel().getLocationId().equals(organization.getLocation().getId());
//
//        for (DropdownViewModel createLocationViewModel : editOrganizationViewModel.getLocations()) {
//            assert createLocationViewModel.getName() != null;
//            assert createLocationViewModel.getId() != null;
//        }
//
//    }

    @Test
    public void saveEditOrganizationCommandModel() {

        //arrange
        //  Set up the Player in the database we are going to edit using the helpers
        List<Location> locations = testHelper.createMultipleLocations(3);

        Organization organization = testHelper.createTestOrganization(locations.get(0));

        CreateOrganizationCommandModel cmd = new CreateOrganizationCommandModel();
        cmd.setLocation(locations.get(0).getName());
        cmd.setLocationId(locations.get(0).getId());
        cmd.setPhone(organization.getPhone());
        cmd.setEmail(organization.getEmail());
        cmd.setDescription(organization.getDescription());
        cmd.setName(organization.getName());

        organizationWebService.saveCreateOrganizationCommandModel(cmd);

        //Retrieve the viewModel (acting like we are going to the edit page ourselves)
        EditOrganizationViewModel viewModel = this.organizationWebService.getEditOrganizationViewModel(organization.getId());
        EditOrganizationCommandModel commandModel = viewModel.getCommandModel();

        // Change the items on the form (commandModel) to what we want
        commandModel.setName("Anti Justice League");
        commandModel.setDescription("Supervillain Organization");
        commandModel.setEmail("clark@dailyplanet.com");
        commandModel.setPhone("999-5555");
        commandModel.setLocationId(viewModel.getLocations().get(1).getId());

        this.organizationWebService.saveEditOrganizationCommandModel(commandModel);

        // once we save the changes, we want to make sure the viewModel of the edit pages if visited again has those
        // changes saved and retrieved from the database.
        ProfileOrganizationViewModel orgViewModel =
                this.organizationWebService.getOrganizationProfileViewModel(organization.getId());
        assert orgViewModel.getName().equals("Anti Justice League");
        assert orgViewModel.getDescription().equals("Supervillain Organization");
        assert orgViewModel.getEmail().equals("clark@dailyplanet.com");
        assert orgViewModel.getPhone().equals("999-5555");
        assert orgViewModel.getLocatonId().equals(viewModel.getLocations().get(1).getId());
    }

    @Test
    public void deleteOrganization() {

        //Arrange
        Location location = testHelper.createTestLocation();
        Organization organization = testHelper.createTestOrganization(location);

        //Verify successful creation
        ProfileOrganizationViewModel vm = organizationWebService.getOrganizationProfileViewModel(organization.getId());
        assert vm != null;

        //Act
        organizationWebService.deleteOrganization(organization.getId());

        //Assert
        ProfileOrganizationViewModel vmDeleted = organizationWebService.getOrganizationProfileViewModel(organization.getId());
        assert vmDeleted == null;

    }

}