package com.sg.superhero.webservice;

import com.sg.superhero.TestHelper;
import com.sg.superhero.dto.Location;
import com.sg.superhero.dto.Sighting;
import com.sg.superhero.dto.Super;
import com.sg.superhero.service.interfaces.SightingService;
import com.sg.superhero.service.interfaces.SuperService;
import com.sg.superhero.viewmodels.DropdownViewModel;
import com.sg.superhero.viewmodels.sighting.create.CreateSightingCommandModel;
import com.sg.superhero.viewmodels.sighting.create.CreateSightingViewModel;
import com.sg.superhero.viewmodels.sighting.edit.EditSightingCommandModel;
import com.sg.superhero.viewmodels.sighting.edit.EditSightingViewModel;
import com.sg.superhero.viewmodels.sighting.list.ListSightingViewModel;
import com.sg.superhero.viewmodels.sighting.list.SightingViewModel;
import com.sg.superhero.viewmodels.sighting.profile.ProfileSightingViewModel;
import com.sg.superhero.viewmodels.sighting.profile.ProfileSuperViewModel;
import com.sg.superhero.webservice.interfaces.SightingWebService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)  //says Spring in charge of running unit tests.
@ContextConfiguration(locations = {"/test-applicationContext.xml"}) //indicates the xml file to use
@Rollback //Automatically all tests will be transactional, and when complete, won't be committed; will be rolled back.
//need @Rollback and @Transactional annotations both.
@Transactional
public class SightingWebServiceImplTest {

    @Inject
    SightingWebService sightingWebService;

    @Inject
    SightingService sightingService;

    @Inject
    TestHelper testHelper;

    @Inject
    SuperService superService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getListSightingViewModel() {

        //Arrange
        int offset = 0;

        List<Sighting> sightingList = new ArrayList<>();
        for (int i=0; i<25; i++) {
            sightingList.add(testHelper.createTestSighting());
        }

        //Act
        ListSightingViewModel viewModel = sightingWebService.getListSightingViewModel(0);

        //Assert
        assert viewModel.getSelectedPage() == 1;
        assert viewModel.getPageNumbers().length == 5;
        assert viewModel.getPageNumbers()[0] == 1;
        assert viewModel.getPageNumbers()[4] == 5;

        assert viewModel.getLocations() != null;
        assert viewModel.getLocations().size() == 10;

        int counter=0;
        for (SightingViewModel sViewModel : viewModel.getLocations()) {
            Sighting sighting = sightingList.get(counter);
            assert sViewModel.getLocation().equals((sighting.getLocation().getName()));
            counter++;
        }
    }

    @Test
    public void getProfileSightingViewModel() {

        //Arrange
        Sighting sighting = testHelper.createTestSighting();
        List<Super> supersAtSighting = testHelper.createMultipleSupers(3);

        testHelper.createTestSuperSighting(supersAtSighting.get(0), sighting);
        testHelper.createTestSuperSighting(supersAtSighting.get(1), sighting);

        //Act
        ProfileSightingViewModel viewModel = sightingWebService.getProfileSightingViewModel(sighting.getId());

        //Assert
        assert viewModel.getDate().equals(sighting.getDate());
        assert viewModel.getLocation().equals(sighting.getLocation().getName());
        assert viewModel.getLocationId().equals(sighting.getLocation().getId());


        assert viewModel.getSuperList().get(0).getId().equals(supersAtSighting.get(0).getId());
        assert viewModel.getSuperList().get(1).getId().equals(supersAtSighting.get(1).getId());
    }

//    @Test
//    public void getCreateSightingViewModel() {
//
//        //Arrange
//        List<Super> supers = testHelper.createMultipleSupers(15);
//        List <Location> locations = testHelper.createMultipleLocations(15);
//
//        //Act
//        CreateSightingViewModel createSightingViewModel = sightingWebService.getCreateSightingViewModel();
//
//        //Assert
//        assert createSightingViewModel.getSuperPeople().size() == supers.size();
//        assert createSightingViewModel.getLocation().size() == locations.size();
//
//        for (DropdownViewModel sightingViewModel : createSightingViewModel.getSuperPeople()) {
//            assert sightingViewModel.getName() != null;
//            assert sightingViewModel.getId() != null;
//        }
//
//        for (DropdownViewModel superDropDown : createSightingViewModel.getSuperPeople()) {
//            assert superDropDown.getName() != null;
//            assert superDropDown.getId() != null;
//        }
//
//        for (DropdownViewModel locationDropDown : createSightingViewModel.getLocation()) {
//            assert locationDropDown.getName() != null;
//            assert locationDropDown.getId() != null;
//        }
//    }

    @Test
    public void saveCreateSightingCommandModel() {

        //Arrange
        //When testing command model, the 'arrange' arranges the data that the user would enter from the UI.
        List<Super> createdSupers = testHelper.createMultipleSupers(2);
        Location location = testHelper.createTestLocation();


        //In the web side of things spring is going to handle this part for us.
        CreateSightingCommandModel createSightingCommandModel = new CreateSightingCommandModel();
        createSightingCommandModel.setDate("2018-06-14");
        createSightingCommandModel.setLocationId(location.getId());

        Long[] superIds = new Long[2];
        superIds[0] = createdSupers.get(0).getId();
        superIds[1] = createdSupers.get(1).getId();

        createSightingCommandModel.setSuperId(superIds);

        //Act
        Sighting sighting = sightingWebService.saveCreateSightingCommandModel(createSightingCommandModel);

        //Assert
        assert sighting.getId() != null;
        assert sighting.getDate().equals(LocalDate.parse("2018-06-14"));
        assert sighting.getLocation().getName().equals(location.getName());

        List<Super> supers = superService.retrieveSupersBySighting(sighting, Integer.MAX_VALUE, 0);

        boolean savedFirstSuper = false;
        boolean savedSecondSuper = false;

        for (Super superPerson : supers) {
            if (superPerson.getId().equals(createdSupers.get(0).getId())) savedFirstSuper = true;
            if (superPerson.getId().equals(createdSupers.get(1).getId())) savedSecondSuper = true;
        }

        assert savedFirstSuper == true;
        assert savedSecondSuper == true;
    }

    @Test
    public void getEditSightingViewMode() {

        //arrange
        List<Super> supers = testHelper.createMultipleSupers(5);

        Sighting sighting = testHelper.createTestSighting();
        testHelper.createTestSuperSighting(supers.get(0),sighting);
        testHelper.createTestSuperSighting(supers.get(1),sighting);
        testHelper.createTestSuperSighting(supers.get(2),sighting);
        testHelper.createTestSuperSighting(supers.get(3),sighting);
        testHelper.createTestSuperSighting(supers.get(4),sighting);

        //act
        EditSightingViewModel vm = this.sightingWebService.getEditSightingViewMode(sighting.getId());

        //assert
        assert vm.getSuperPeople().size() == supers.size();

        // check list for ids

        assert sighting.getLocation().getId().equals(vm.getCommandModel().getLocationId());
        assert sighting.getDate().equals(vm.getCommandModel().getDate());

        int counter = 0;
        List<DropdownViewModel> vmList = vm.getSuperPeople();
        for (DropdownViewModel ddvm : vmList) {
            assert (ddvm.getName().equals(supers.get(counter).getName()));
            counter++;
        }
    }

//    @Test
//    public void testSaveEditSightingCommandModel() {
//        //Arrange - Set up the Player in the database we are going to edit using the helpers
//        List<Location> locations = testHelper.createMultipleLocations(3);
//        List<Super> supers = testHelper.createMultipleSupers(5);
//        Sighting sighting1 = testHelper.createTestSighting(locations.get(0), LocalDate.now());
//        Sighting sighting2 = testHelper.createTestSighting(locations.get(1), LocalDate.now());
//        testHelper.createTestSuperSighting(supers.get(0), sighting1);
//        testHelper.createTestSuperSighting(supers.get(1), sighting2);
//
//        //Retrieve the viewModel (acting like we are going to the edit page ourselves)
//        EditSightingViewModel viewModel = this.sightingWebService.getEditSightingViewMode(sighting1.getId());
//        EditSightingCommandModel commandModel = viewModel.getCommandModel();
//
//        // Change the items on the form (commandModel) to what we want
//        commandModel.setDate(LocalDate.parse("2018-02-22"));
//        commandModel.setLocationId(viewModel.getLocation().get(1).getId());
//
//        // Here we are selecting two items in the drop down on the page.
//        Long[] superIds = new Long[2];
//        superIds[0] = viewModel.getSuperPeople().get(0).getId();
//        superIds[1] = viewModel.getSuperPeople().get(2).getId();
//        commandModel.setSuperIds(superIds);
//
//
//        //act
//        this.sightingWebService.saveEditSightingCommandModel(commandModel);
//
//        // once we save the changes, we want to make sure the viewModel of the edit pages if visited again has those changes saved and retrieved from the database.
//        ProfileSightingViewModel sightingViewModel = this.sightingWebService.getProfileSightingViewModel(sighting1.getId());
//        assert sightingViewModel.getDate().equals(LocalDate.parse("2018-02-22"));
//        assert sightingViewModel.getLocationId().equals(viewModel.getLocation().get(1).getId());
//
//        /* //esentially comparing it to itself, doesnt make sense
//        boolean savedFirstSuper = false;
//        boolean savedSecondSuper = false;
//        for (SuperPersonWebServiceImpl superPerson : supers) {
//            if (superPerson.getId().equals(supers.get(0).getId())) savedFirstSuper = true;
//            if (superPerson.getId().equals(supers.get(2).getId())) savedSecondSuper = true;
//        }
//        assert savedFirstSuper;
//        assert savedSecondSuper;
//        */
//
//        int counter = 0;
//        List<DropdownViewModel> vmList = viewModel.getSuperPeople();
//        for (DropdownViewModel ddvm : vmList) {
//            assert (ddvm.getName().equals(supers.get(counter).getName()));
//            counter++;
//        }
//    }

    @Test
    public void saveEditSightingCommandModel() {

        //arrange

        //  Set up the Player in the database we are going to edit using the helpers
        List<Super> supers = testHelper.createMultipleSupers(5);
        Sighting sighting = testHelper.createTestSighting();
        Location newLocation = testHelper.createTestLocation();
        testHelper.createTestSuperSighting(supers.get(0),sighting);
        testHelper.createTestSuperSighting(supers.get(1),sighting);

        //Retrieve the viewModel (acting like we are going to the edit page ourselves)
        EditSightingViewModel viewModel = this.sightingWebService.getEditSightingViewMode(sighting.getId());
        EditSightingCommandModel commandModel = viewModel.getCommandModel();

        // Change the items on the form (commandModel) to what we want
        commandModel.setDate(("2018-06-14"));
        commandModel.setLocationId(newLocation.getId());
        //testHelper.createTestSuperSighting(supers.get(2), sighting);

        // Here we are selecting to items in the drop down on the page.
        Long[] superIds = new Long[2];
        superIds[0] = viewModel.getSuperPeople().get(0).getId();
        superIds[1] = viewModel.getSuperPeople().get(1).getId();
        commandModel.setSuperIds(superIds);
        //act
        this.sightingWebService.saveEditSightingCommandModel(commandModel);

        // once we save the changes, we want to make sure the viewModel of the edit pages if visited again has those
        // changes saved and retrieved from the database.
        ProfileSightingViewModel sightingViewModel = this.sightingWebService.getProfileSightingViewModel(sighting.getId());
        assert sightingViewModel.getDate().equals(LocalDate.parse("2018-06-14"));
        assert sightingViewModel.getLocationId().equals(newLocation.getId());

//
        int counter = 0;
        List<ProfileSuperViewModel> vmList = sightingViewModel.getSuperList();
        for (ProfileSuperViewModel ddvm : vmList) {
            assert (ddvm.getSuperPersonName().equals(supers.get(counter).getName()));
            counter++;
        }
    }

    @Test
    public void deleteSighting() {

        List<Super> supers = testHelper.createMultipleSupers(5);
        Location location = testHelper.createTestLocation();
        Sighting sighting = testHelper.createTestSighting(location, LocalDate.now());
        testHelper.createTestSuperSighting(supers.get(0),sighting);
        testHelper.createTestSuperSighting(supers.get(1), sighting);

        ProfileSightingViewModel vm = sightingWebService.getProfileSightingViewModel(sighting.getId());
        assert vm != null;

        //act
        this.sightingWebService.deleteSighting(sighting.getId());

        Sighting deletedSighting = this.sightingService.retrieve(sighting.getId());

        assert deletedSighting == null;
    }
}