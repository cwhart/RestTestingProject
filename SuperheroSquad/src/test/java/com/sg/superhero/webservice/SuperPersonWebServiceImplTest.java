package com.sg.superhero.webservice;

import com.sg.superhero.TestHelper;
import com.sg.superhero.dto.*;
import com.sg.superhero.service.interfaces.PowerService;
import com.sg.superhero.service.interfaces.SuperService;
import com.sg.superhero.viewmodels.DropdownViewModel;
import com.sg.superhero.viewmodels.superPerson.create.CreateSuperPersonCommandModel;
import com.sg.superhero.viewmodels.superPerson.create.CreateSuperPersonViewModel;
import com.sg.superhero.viewmodels.superPerson.edit.EditSuperPersonCommandModel;
import com.sg.superhero.viewmodels.superPerson.edit.EditSuperPersonViewModel;
import com.sg.superhero.viewmodels.superPerson.list.ListSuperPersonViewModel;
import com.sg.superhero.viewmodels.superPerson.list.SuperPersonViewModel;
import com.sg.superhero.viewmodels.superPerson.profile.ProfileOrganizationViewModel;
import com.sg.superhero.viewmodels.superPerson.profile.ProfilePowerViewModel;
import com.sg.superhero.viewmodels.superPerson.profile.ProfileSuperPersonViewModel;
import com.sg.superhero.webservice.interfaces.SuperPersonWebService;
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
public class SuperPersonWebServiceImplTest {

    @Inject
    SuperPersonWebService superPersonWebService;

    @Inject
    TestHelper testHelper;

    @Inject
    PowerService powerService;

    @Inject
    SuperService superService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getListSuperPersonViewModel() {

        //Arrange
        int offset = 0;

        List<Super> superList = new ArrayList<>();
        for (int i=0; i<25; i++) {
            superList.add(testHelper.createTestSuper());
        }

        //Act
        ListSuperPersonViewModel viewModel = superPersonWebService.getListSuperPersonViewModel(0);

        //Assert
        assert viewModel.getSelectedPage() == 1;
        assert viewModel.getPageNumbers().length == 5;
        assert viewModel.getPageNumbers()[0] == 1;
        assert viewModel.getPageNumbers()[4] == 5;

        assert viewModel.getSuperPersons() != null;
        assert viewModel.getSuperPersons().size() == 10;

        int counter=0;
        for (SuperPersonViewModel sViewModel : viewModel.getSuperPersons()) {
            Super superPerson = superList.get(counter);
            assert sViewModel.getName().equals((superPerson.getName()));
            assert sViewModel.getDescription().equals((superPerson.getDescription()));
            counter++;
        }

    }

    @Test
    public void getProfileSuperPersonViewModel() {

        //Arrange
        //Create the super
        Super superPerson = testHelper.createTestSuper();

        //Create locations for the sightings to happen
        List<Location> locationList = testHelper.createMultipleLocations(3);

        //Create organizations for the super to be a member of
        List<Organization> supersOrganizations = testHelper.createMultipleOrganizations(3);
        //Now add the super to the organizations
        SuperOrganization superOrg1 = testHelper.createTestSuperOrganization(superPerson, supersOrganizations.get(0));
        SuperOrganization superOrg2 = testHelper.createTestSuperOrganization(superPerson, supersOrganizations.get(1));
        SuperOrganization superOrg3 = testHelper.createTestSuperOrganization(superPerson, supersOrganizations.get(2));

        //Create sightings for the super to be seen at
        Sighting sighting1 = testHelper.createTestSighting(locationList.get(0), LocalDate.now());
        Sighting sighting2 = testHelper.createTestSighting(locationList.get(1), LocalDate.now());
        //Now add the super to the sighting
        SuperSighting superSighting1 = testHelper.createTestSuperSighting(superPerson, sighting1);
        SuperSighting superSighting2 = testHelper.createTestSuperSighting(superPerson, sighting2);
        //Create powers for the super to have
        List<Power> supersPowers = testHelper.createMultiplePowers(3);
        //Now add the super to the powers
        SuperPower superPower1 = testHelper.createTestSuperPower(superPerson, supersPowers.get(0));
        SuperPower superPower2 = testHelper.createTestSuperPower(superPerson, supersPowers.get(1));


        //Act
        ProfileSuperPersonViewModel viewModel = superPersonWebService.getProfileSuperPersonViewModel(superPerson.getId());

        //Assert
        assert viewModel.getName().equals(superPerson.getName());
        assert viewModel.getDescription().equals(superPerson.getDescription());


        assert viewModel.getOrganizationList().get(0).getId().equals(superOrg1.getOrganization().getId());
        assert viewModel.getOrganizationList().get(1).getId().equals(superOrg2.getOrganization().getId());
        assert viewModel.getOrganizationList().get(2).getId().equals(superOrg3.getOrganization().getId());

        assert viewModel.getSightingList().get(0).getId().equals(sighting1.getId());
        assert viewModel.getSightingList().get(1).getId().equals(sighting2.getId());

        assert viewModel.getPowerList().get(0).getName().equals(superPower1.getPower().getName());
        assert viewModel.getPowerList().get(1).getName().equals(superPower2.getPower().getName());

    }

    @Test
    public void getCreateSuperPersonViewModel() {

        //Arrange
        List<Organization> orgs = testHelper.createMultipleOrganizations(15);
        List <Power> powers = testHelper.createMultiplePowers(15);

        //Act
        CreateSuperPersonViewModel createSuperPersonViewModel = superPersonWebService.getCreateSuperPersonViewModel();

        //Assert
        assert createSuperPersonViewModel.getOrganizations().size() == orgs.size();
        assert createSuperPersonViewModel.getPowers().size() == powers.size();

        for (DropdownViewModel orgViewModel : createSuperPersonViewModel.getOrganizations()) {
            assert orgViewModel.getName() != null;
            assert orgViewModel.getId() != null;
        }

        for (DropdownViewModel powerDropDown : createSuperPersonViewModel.getPowers()) {
            assert powerDropDown.getName() != null;
            assert powerDropDown.getId() != null;
        }

    }

    @Test
    public void saveCreateSuperPersonCommandModel() {

        //Arrange
        //When testing command model, the 'arrange' arranges the data that the user would enter from the UI.
        List<Organization> createdOrganizations = testHelper.createMultipleOrganizations(2);
        List<Power> createdPowers = testHelper.createMultiplePowers(2);
        Super superPerson = testHelper.createTestSuper();
        SuperOrganization superOrganization1 =
                testHelper.createTestSuperOrganization(superPerson, createdOrganizations.get(0));
        SuperOrganization superOrganization2 =
                testHelper.createTestSuperOrganization(superPerson, createdOrganizations.get(1));
        SuperPower superPower1 = testHelper.createTestSuperPower(superPerson, createdPowers.get(0));
        SuperPower superPower2 = testHelper.createTestSuperPower(superPerson, createdPowers.get(1));

        Long[] orgIds = new Long[2];
        orgIds[0] = createdOrganizations.get(0).getId();
        orgIds[1] = createdOrganizations.get(1).getId();

        Long[] powerIds = new Long[2];
        powerIds[0] = createdPowers.get(0).getId();
        powerIds[1] = createdPowers.get(1).getId();

        CreateSuperPersonCommandModel cmd = new CreateSuperPersonCommandModel();
        cmd.setName(superPerson.getName());
        cmd.setDescription(superPerson.getDescription());
        cmd.setOrganizationId(orgIds);
        cmd.setPowerId(powerIds);

        //Act
        Super createdSuperPerson = superPersonWebService.saveCreateSuperPersonCommandModel(cmd);

        //Assert
        assert createdSuperPerson.getId() != null;
        assert createdSuperPerson.getDescription().equals(superPerson.getDescription());

        List<Power> powers = powerService.retrievePowersBySuper(createdSuperPerson, Integer.MAX_VALUE, 0);

        boolean savedFirstPower = false;
        boolean savedSecondPower = false;

        for (Power currentPower : powers) {
            if (currentPower.getId().equals(powers.get(0).getId())) savedFirstPower = true;
            if (currentPower.getId().equals(powers.get(1).getId())) savedSecondPower = true;
        }
    }

    @Test
    public void getEditSuperPersonViewMode() {

        //arrange
        List<Organization> orgs = testHelper.createMultipleOrganizations(5);
        List<Power> powers = testHelper.createMultiplePowers(5);

        Super superPerson = testHelper.createTestSuper();
        testHelper.createTestSuperOrganization(superPerson,orgs.get(0));
        testHelper.createTestSuperOrganization(superPerson,orgs.get(1));
        testHelper.createTestSuperOrganization(superPerson,orgs.get(2));
        testHelper.createTestSuperPower(superPerson, powers.get(0));
        testHelper.createTestSuperPower(superPerson, powers.get(1));

        //act
        EditSuperPersonViewModel vm = this.superPersonWebService.getEditSuperPersonViewMode(superPerson.getId());

        //assert
        assert vm.getOrganizations().size() == orgs.size();
        assert vm.getPowers().size() == powers.size();


        // check list for ids

        assert superPerson.getName().equals(vm.getCommandModel().getName());
        assert superPerson.getDescription().equals(vm.getCommandModel().getDescription());

        int counter = 0;
        List<DropdownViewModel> vmList = vm.getOrganizations();
        for (DropdownViewModel ddvm : vmList) {
            assert (ddvm.getName().equals(orgs.get(counter).getName()));
            counter++;
        }

        int counter1 = 0;
        List<DropdownViewModel> vmList1 = vm.getPowers();
        for (DropdownViewModel ddvm : vmList1) {
            assert (ddvm.getName().equals(powers.get(counter1).getName()));
            counter1++;
        }
    }

    @Test
    public void saveEditSuperPersonCommandModel() {

        //arrange

        //  Set up the Player in the database we are going to edit using the helpers
        List<Organization> orgs = testHelper.createMultipleOrganizations(5);
        List<Power> powers = testHelper.createMultiplePowers(5);
        Super superPerson = testHelper.createTestSuper();
        testHelper.createTestSuperPower(superPerson,powers.get(0));
        testHelper.createTestSuperPower(superPerson,powers.get(1));
        testHelper.createTestSuperOrganization(superPerson, orgs.get(0));
        testHelper.createTestSuperOrganization(superPerson, orgs.get(1));
        testHelper.createTestSuperOrganization(superPerson, orgs.get(2));

        //Retrieve the viewModel (acting like we are going to the edit page ourselves)
        EditSuperPersonViewModel viewModel = this.superPersonWebService.getEditSuperPersonViewMode(superPerson.getId());
        EditSuperPersonCommandModel commandModel = viewModel.getCommandModel();

        // Change the items on the form (commandModel) to what we want
        commandModel.setName("Clark Kent");
        commandModel.setDescription("Mild-mannered reporter");

        // Here we are selecting to items in the drop down on the page.
        Long[] orgIds = new Long[2];
        orgIds[0] = viewModel.getOrganizations().get(0).getId();
        orgIds[1] = viewModel.getOrganizations().get(1).getId();
        commandModel.setOrganizationId(orgIds);

        Long[] powerIds = new Long[2];
        powerIds[0] = viewModel.getPowers().get(0).getId();
        powerIds[1] = viewModel.getPowers().get(1).getId();
        commandModel.setPowerId(powerIds);
        //act
        this.superPersonWebService.saveEditSuperPersonCommandModel(commandModel);

        // once we save the changes, we want to make sure the viewModel of the edit pages if visited again has those
        // changes saved and retrieved from the database.
        ProfileSuperPersonViewModel superViewModel =
                this.superPersonWebService.getProfileSuperPersonViewModel(superPerson.getId());
        assert superViewModel.getName().equals("Clark Kent");
        assert superViewModel.getDescription().equals("Mild-mannered reporter");

        int counter = 0;
        List<ProfileOrganizationViewModel> vmList = superViewModel.getOrganizationList();
        for (ProfileOrganizationViewModel ovm : vmList) {
            assert (ovm.getName().equals(orgs.get(counter).getName()));
            counter++;
        }

        int counter1 = 0;
        List<ProfilePowerViewModel> vmList1 = superViewModel.getPowerList();
        for (ProfilePowerViewModel pvm : vmList1) {
            assert (pvm.getName().equals(powers.get(counter1).getName()));
            counter1++;
        }
    }

    @Test
    public void deleteSuperPerson() {

        //arrange
        List<Organization> orgs = testHelper.createMultipleOrganizations(5);
        List<Power> powers = testHelper.createMultiplePowers(5);
        Super superPerson = testHelper.createTestSuper();
        testHelper.createTestSuperPower(superPerson, powers.get(0));
        testHelper.createTestSuperPower(superPerson, powers.get(1));
        testHelper.createTestSuperOrganization(superPerson, orgs.get(0));
        testHelper.createTestSuperOrganization(superPerson, orgs.get(0));

        ProfileSuperPersonViewModel vm = superPersonWebService.getProfileSuperPersonViewModel(superPerson.getId());

        assert vm != null;
        //act
        this.superPersonWebService.deleteSuperPerson(superPerson.getId());

        Super deletedSuper = this.superService.retrieve(superPerson.getId());

        assert deletedSuper == null;
    }
}