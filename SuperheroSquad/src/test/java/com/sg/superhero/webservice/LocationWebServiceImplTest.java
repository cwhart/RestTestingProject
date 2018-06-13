package com.sg.superhero.webservice;

import com.sg.superhero.dto.Location;
import com.sg.superhero.viewmodels.location.create.CreateLocationCommandModel;
import com.sg.superhero.viewmodels.location.create.CreateLocationViewModel;
import com.sg.superhero.viewmodels.location.edit.EditLocationCommandModel;
import com.sg.superhero.viewmodels.location.edit.EditLocationViewModel;
import com.sg.superhero.viewmodels.location.list.ListLocationViewModel;
import com.sg.superhero.viewmodels.location.list.LocationViewModel;
import com.sg.superhero.viewmodels.location.profile.ProfileLocationViewModel;
import com.sg.superhero.webservice.interfaces.LocationWebService;
import org.eclipse.core.internal.dtree.TestHelper;
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
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)  //says Spring in charge of running unit tests.
@ContextConfiguration(locations = {"/test-applicationContext.xml"}) //indicates the xml file to use
@Rollback //Automatically all tests will be transactional, and when complete, won't be committed; will be rolled back.
//need @Rollback and @Transactional annotations both.
@Transactional
public class LocationWebServiceImplTest {

    @Inject
    LocationWebService locationWebService;

    @Inject
    com.sg.superhero.TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

//    @Test
//    public void getLocationListViewModel() {
//
//        //Arrange
//        Integer offset = 0;
//
//        List<Location> locations = new ArrayList<>();
//
//        for (int i = 0; i < 25; i++) {
//            Location location = testHelper.createTestLocation();
//            locations.add(location);
//        }
//
//        //Act
//        ListLocationViewModel viewModel = locationWebService.getLocationListViewModel(offset);
//
//        //Assert
//        assert viewModel.getSelectedPage() == 1;
//        assert viewModel.getPageNumbers().length == 5;
//        assert viewModel.getPageNumbers()[0] == 1;
//        assert viewModel.getPageNumbers()[4] == 5;
//
//        assert viewModel.getLocations() != null;
//        assert viewModel.getLocations().size() == 10;
//
//        int counter = 0;
//        for (LocationViewModel lViewModel : viewModel.getLocations()) {
//            Location location = locations.get(counter);
//            assert lViewModel.getName().equals((location.getName()));
//            counter++;
//        }
//    }

        @Test
        public void getLocationProfileViewModel () {

            //Arrange
            Location location = testHelper.createTestLocation();

            //Act
            ProfileLocationViewModel viewModel = locationWebService.getLocationProfileViewModel(location.getId());

            //Assert
            assert viewModel.getName().equals(location.getName());
            assert viewModel.getDescription().equals(location.getDescription());
            assert viewModel.getStreet().equals(location.getStreet());
            assert viewModel.getCity().equals(location.getCity());
            assert viewModel.getState().equals(location.getState());
            assert viewModel.getZip().equals(location.getZip());
            assert viewModel.getLatitude().equals(location.getLatitude());
            assert viewModel.getLongitude().equals(location.getLongitude());

        }

        @Test
        public void getCreateLocationViewModel () {

            //No dropdowns on the screen - don't need this method.
        }

        @Test
        public void saveCreateLocationCommandModel () {

            //In the web side of things spring is going to handle this part for us.
            //Arrange
            CreateLocationCommandModel createLocationCommandModel = new CreateLocationCommandModel();
            createLocationCommandModel.setName("The Daily Planet");
            createLocationCommandModel.setDescription("Daily Planet main offices");
            createLocationCommandModel.setStreet("557 Clark Kent Blvd.");
            createLocationCommandModel.setCity("Metropolis");
            createLocationCommandModel.setState("NY");
            createLocationCommandModel.setZip("44556");
            createLocationCommandModel.setLatitude((long)40.71);
            createLocationCommandModel.setLongitude((long)74.00);

            //Act
            Location location = locationWebService.saveCreateLocationCommandModel(createLocationCommandModel);

            //Assert
            assert location.getId() != null;
            assert "The Daily Planet".equals(location.getName());
            assert "Daily Planet main offices".equals(location.getDescription());
            assert "557 Clark Kent Blvd.".equals(location.getStreet());
            assert "Metropolis".equals(location.getCity());
            assert "NY".equals(location.getState());
            assert "44556".equals(location.getZip());
            assert location.getLatitude() != null;
            assert location.getLongitude() != null;


        }

    @Test
    public void testGetEditPlayerViewModel(){
        //arrange

        Location location = testHelper.createTestLocation();

        //act
        EditLocationViewModel vm = this.locationWebService.getEditLocationViewModel(location.getId());

        //assert

        // check list for ids

        assert location.getName().equals(vm.getCommandModel().getName());
        assert location.getDescription().equals(vm.getCommandModel().getDescription());
        assert location.getStreet().equals(vm.getCommandModel().getStreet());
        assert location.getCity().equals(vm.getCommandModel().getCity());
        assert location.getState().equals(vm.getCommandModel().getState());
        assert location.getZip().equals(vm.getCommandModel().getZip());
        assert location.getLatitude().equals(vm.getCommandModel().getLatitude());
        assert location.getLongitude().equals(vm.getCommandModel().getLongitude());


    }

    @Test
    public void testSaveEditLocationViewModel(){
        //arrange

        //  Set up the Location in the database we are going to edit using the helpers

        Location location = testHelper.createTestLocation();

        //Retrieve the viewModel (acting like we are going to the edit page ourselves)
        EditLocationViewModel viewModel = this.locationWebService.getEditLocationViewModel(location.getId());
        EditLocationCommandModel commandModel = viewModel.getCommandModel();

        // Change the items on the form (commandModel) to what we want
        commandModel.setName("The Daily Planet1");
        commandModel.setDescription("Daily Planet main offices1");
        commandModel.setStreet("557 Clark Kent Blvd1.");
        commandModel.setCity("Metropolis1");
        commandModel.setState("MA");
        commandModel.setZip("11223");
        commandModel.setLatitude((long)40.72);
        commandModel.setLongitude((long)74.01);

        //act
        this.locationWebService.saveEditLocationCommandModel(commandModel);

        // once we save the changes, we want to make sure the viewModel of the edit pages if visited again has those
        // changes saved and retrieved from the database.
        ProfileLocationViewModel locationViewModel = this.locationWebService.getLocationProfileViewModel(location.getId());
        assert locationViewModel.getName().equals("The Daily Planet1");
        assert locationViewModel.getDescription().equals("Daily Planet main offices1");
        assert locationViewModel.getStreet().equals("557 Clark Kent Blvd1.");
        assert locationViewModel.getCity().equals("Metropolis1");
        assert locationViewModel.getState().equals("MA");
        assert locationViewModel.getZip().equals("11223");
        assert locationViewModel.getLongitude() != null;
        assert locationViewModel.getLatitude() != null;

    }

        @Test
        public void deleteLocation () {

        //Arrange
        Location location = testHelper.createTestLocation();

        //Verify successful creation
            ProfileLocationViewModel vm = locationWebService.getLocationProfileViewModel(location.getId());
            assert vm != null;

        //Act
        locationWebService.deleteLocation(location.getId());

        //Assert
            ProfileLocationViewModel vmDeleted = locationWebService.getLocationProfileViewModel(location.getId());
            assert vmDeleted == null;

        }

}