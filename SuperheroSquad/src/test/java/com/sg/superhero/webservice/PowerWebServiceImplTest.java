package com.sg.superhero.webservice;

import com.sg.superhero.TestHelper;
import com.sg.superhero.dto.Power;
import com.sg.superhero.dto.Super;
import com.sg.superhero.service.interfaces.PowerService;
import com.sg.superhero.service.interfaces.SuperService;
import com.sg.superhero.viewmodels.DropdownViewModel;
import com.sg.superhero.viewmodels.power.create.CreatePowerCommandModel;
import com.sg.superhero.viewmodels.power.create.CreatePowerViewModel;
import com.sg.superhero.viewmodels.power.edit.EditPowerCommandModel;
import com.sg.superhero.viewmodels.power.edit.EditPowerViewModel;
import com.sg.superhero.viewmodels.power.list.ListPowerViewModel;
import com.sg.superhero.viewmodels.power.list.PowerViewModel;
import com.sg.superhero.viewmodels.power.profile.ProfilePowerViewModel;
import com.sg.superhero.webservice.interfaces.PowerWebService;
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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)  //says Spring in charge of running unit tests.
@ContextConfiguration(locations = {"/test-applicationContext.xml"}) //indicates the xml file to use
@Rollback //Automatically all tests will be transactional, and when complete, won't be committed; will be rolled back.
//need @Rollback and @Transactional annotations both.
@Transactional
public class PowerWebServiceImplTest {

    @Inject
    PowerWebService powerWebService;

    @Inject
    TestHelper testHelper;

    @Inject
    SuperService superService;

    @Inject
    PowerService powerService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getPowerListViewModel() {

        //Arrange
        Integer offset = 0;

        List<Power> powers = new ArrayList<>();
        //Location location = testHelper.createTestLocation();
        for (int i=0; i<25; i++) {
            Power power = testHelper.createTestPower();
            powers.add(power);
        }

        //Act
        ListPowerViewModel viewModel = powerWebService.getPowerListViewModel(0);

        //Assert
        assert viewModel.getSelectedPage() == 1;
        assert viewModel.getPageNumbers().length == 5;
        assert viewModel.getPageNumbers()[0] == 1;
        assert viewModel.getPageNumbers()[4] == 5;

        assert viewModel.getPowers() != null;
        assert viewModel.getPowers().size() == 10;

        int counter=0;
        for (PowerViewModel pViewModel : viewModel.getPowers()) {
            Power power = powers.get(counter);
            assert pViewModel.getName().equals((power.getName()));
            counter++;
        }
    }

    @Test
    public void getPowerProfileViewModel() {

        //Arrange
        Power power = testHelper.createTestPower();

        List <Super> superList = testHelper.createMultipleSupers(2);

        testHelper.createTestSuperPower(superList.get(0), power);
        testHelper.createTestSuperPower(superList.get(1), power);

        //Act
        ProfilePowerViewModel viewModel = powerWebService.getPowerProfileViewModel(power.getId());

        //Assert
        assert viewModel.getName().equals(power.getName());
        assert viewModel.getSupers().get(0).getId().equals(superList.get(0).getId());
        assert viewModel.getSupers().get(1).getId().equals(superList.get(1).getId());
    }

    @Test
    public void getCreatePowerViewModel() {

        //Arrange
        List<Super> supers = testHelper.createMultipleSupers(15);

        //Act
        CreatePowerViewModel createPowerViewModel = powerWebService.getCreatePowerViewModel();

        //Assert
        assert createPowerViewModel.getSuperPeople().size() == supers.size();

        for (DropdownViewModel createSuperViewModel : createPowerViewModel.getSuperPeople()) {
            assert createSuperViewModel.getName() != null;
            assert createSuperViewModel.getId() != null;

        }
    }

    @Test
    public void saveCreatePowerCommandModel() {

        //Arrange
        //When testing command model, the 'arrange' arranges the data that the user would enter from the UI.
        List<Super> createdSupers = testHelper.createMultipleSupers(2);


        //In the web side of things spring is going to handle this part for us.
        CreatePowerCommandModel createPowerCommandModel = new CreatePowerCommandModel();
        createPowerCommandModel.setName("Super Strength");

        Long[] superIds = new Long[2];
        superIds[0] = createdSupers.get(0).getId();
        superIds[1] = createdSupers.get(1).getId();

        createPowerCommandModel.setSuperId(superIds);

        //Act
        Power power = powerWebService.saveCreatePowerCommandModel(createPowerCommandModel);

        //Assert
        assert power.getId() != null;
        assert "Super Strength".equals(power.getName());

        List<Super> supers = superService.retrieveSupersByPower(power, Integer.MAX_VALUE, 0);

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
    public void getEditPowerViewModel() {

        //arrange
        List<Super> supers = testHelper.createMultipleSupers(5);

        Power power = testHelper.createTestPower();
        testHelper.createTestSuperPower(supers.get(0),power);
        testHelper.createTestSuperPower(supers.get(1),power);
        testHelper.createTestSuperPower(supers.get(2),power);
        testHelper.createTestSuperPower(supers.get(3),power);
        testHelper.createTestSuperPower(supers.get(4),power);

        //act
        EditPowerViewModel vm = this.powerWebService.getEditPowerViewModel(power.getId());

        //assert
         assert vm.getSuperPeople().size() == supers.size();

        // check list for ids

        assert power.getName().equals(vm.getCommandModel().getName());
        assert power.getId().equals(vm.getCommandModel().getId());

        int counter = 0;
        List<DropdownViewModel> vmList = vm.getSuperPeople();
        for (DropdownViewModel ddvm : vmList) {
            assert (ddvm.getName().equals(supers.get(counter).getName()));
            counter++;
            }

    }

    @Test
    public void saveEditPowerCommandModel() {

        //arrange

        //  Set up the Player in the database we are going to edit using the helpers
        List<Super> supers = testHelper.createMultipleSupers(5);
        Power power = testHelper.createTestPower();
        testHelper.createTestSuperPower(supers.get(0),power);
        testHelper.createTestSuperPower(supers.get(1),power);

        //Retrieve the viewModel (acting like we are going to the edit page ourselves)
        EditPowerViewModel viewModel = this.powerWebService.getEditPowerViewModel(power.getId());
        EditPowerCommandModel commandModel = viewModel.getCommandModel();

        // Change the items on the form (commandModel) to what we want
        commandModel.setName("X-Ray Vision");

        // Here we are selecting to items in the drop down on the page.
        Long[] superIds = new Long[2];
        superIds[0] = viewModel.getSuperPeople().get(0).getId();
        superIds[1] = viewModel.getSuperPeople().get(1).getId();
        commandModel.setSuperId(superIds);
        //act
        this.powerWebService.saveEditPowerCommandModel(commandModel);

        // once we save the changes, we want to make sure the viewModel of the edit pages if visited again has those
        // changes saved and retrieved from the database.
        ProfilePowerViewModel powerViewModel = this.powerWebService.getPowerProfileViewModel(power.getId());
        assert powerViewModel.getName().equals("X-Ray Vision");
        boolean savedFirstPower = false;
        boolean savedSecondPower = false;
        for (Super superPerson : supers) {
            if (superPerson.getId().equals(supers.get(0).getId())) savedFirstPower = true;
            if (superPerson.getId().equals(supers.get(1).getId())) savedSecondPower = true;
        }
        assert savedFirstPower;
        assert savedSecondPower;
    }

    @Test
    public void deletePower() {

        //arrange
        List<Super> supers = testHelper.createMultipleSupers(5);
        Power power = testHelper.createTestPower();
        testHelper.createTestSuperPower(supers.get(0),power);
        testHelper.createTestSuperPower(supers.get(1),power);

        ProfilePowerViewModel vm = powerWebService.getPowerProfileViewModel(power.getId());

        assert vm != null;
        //act
        this.powerWebService.deletePower(power.getId());

        Power deletedPower = this.powerService.retrieve(power.getId());

        assert deletedPower == null;
    }
}