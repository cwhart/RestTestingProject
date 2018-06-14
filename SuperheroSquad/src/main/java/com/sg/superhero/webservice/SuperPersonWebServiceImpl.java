package com.sg.superhero.webservice;

import com.sg.superhero.dto.*;
import com.sg.superhero.service.OrganizationServiceImpl;
import com.sg.superhero.service.interfaces.*;
import com.sg.superhero.util.PagingUtils;
import com.sg.superhero.viewmodels.DropdownViewModel;
import com.sg.superhero.viewmodels.sighting.profile.ProfileSuperViewModel;
import com.sg.superhero.viewmodels.superPerson.create.CreateSuperPersonCommandModel;
import com.sg.superhero.viewmodels.superPerson.create.CreateSuperPersonViewModel;
import com.sg.superhero.viewmodels.superPerson.edit.EditSuperPersonCommandModel;
import com.sg.superhero.viewmodels.superPerson.edit.EditSuperPersonViewModel;
import com.sg.superhero.viewmodels.superPerson.list.ListSuperPersonViewModel;
import com.sg.superhero.viewmodels.superPerson.list.SuperPersonViewModel;
import com.sg.superhero.viewmodels.superPerson.profile.ProfileOrganizationViewModel;
import com.sg.superhero.viewmodels.superPerson.profile.ProfilePowerViewModel;
import com.sg.superhero.viewmodels.superPerson.profile.ProfileSightingViewModel;
import com.sg.superhero.viewmodels.superPerson.profile.ProfileSuperPersonViewModel;
import com.sg.superhero.webservice.interfaces.SuperPersonWebService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SuperPersonWebServiceImpl implements SuperPersonWebService {

    SuperService superService;

    OrganizationService organizationService;

    PowerService powerService;

    SightingService sightingService;

    SuperOrganizationService superOrganizationService;

    SuperPowerService superPowerService;

    LocationService locationService;

    SuperSightingService superSightingService;

    @Inject
    public SuperPersonWebServiceImpl(SuperService superService, OrganizationService organizationService,
                                     PowerService powerService, SightingService sightingService,
                                     SuperOrganizationService superOrganizationService,
                                     SuperPowerService superPowerService, LocationService locationService,
                                     SuperSightingService superSightingService) {
        this.superService = superService;
        this.organizationService = organizationService;
        this.powerService = powerService;
        this.sightingService = sightingService;
        this.superOrganizationService = superOrganizationService;
        this.superPowerService = superPowerService;
        this.locationService = locationService;
        this.superSightingService = superSightingService;
    }



    @Override
    public ListSuperPersonViewModel getListSuperPersonViewModel(Integer offset) {

        Integer limit = 10;
        ListSuperPersonViewModel viewModel = new ListSuperPersonViewModel();

        int selectedPage = PagingUtils.getSelectedPage(offset, limit);
        int[] pageNumbers = PagingUtils.getPageNumbers(5, selectedPage);

        //Translate from domain to view models
        List<Super> superList = superService.retrieveAll(limit, offset);
        List<SuperPersonViewModel> supers = translate(superList);

        viewModel.setSelectedPage(selectedPage);
        //viewModel.setSelectedPage(selectedPage);
        viewModel.setPageNumbers(pageNumbers);
        viewModel.setSuperPersons(supers);

        return viewModel;
    }

    private List<SuperPersonViewModel> translate(List<Super> superList) {

        List<SuperPersonViewModel> viewModels = new ArrayList<>();

        for (Super superPerson: superList) {
            SuperPersonViewModel viewModel = new SuperPersonViewModel();
            viewModel.setName(superPerson.getName());
            viewModel.setDescription(superPerson.getDescription());
            viewModel.setId(superPerson.getId());

            viewModels.add(viewModel);
        }

        return viewModels;
    }

    @Override
    public ProfileSuperPersonViewModel getProfileSuperPersonViewModel(Long id) {
//Instantiate stuff
        ProfileSuperPersonViewModel viewModel = new ProfileSuperPersonViewModel();

        //Look up stuff
        Super superPerson = superService.retrieve(id);

        List<Organization> orgs = organizationService.retrieveOrganizationsBySuper(superPerson, Integer.MAX_VALUE, 0);
        List<Power> powers = powerService.retrievePowersBySuper(superPerson, Integer.MAX_VALUE, 0);
        List<Sighting> sightings = sightingService.retrieveSightingsBySuper(superPerson, Integer.MAX_VALUE, 0);


        //Set stuff
        viewModel.setName(superPerson.getName());
        viewModel.setDescription(superPerson.getDescription());

        //Lazy loaded - can't rely on the queries to return, hence additional logic above to instantiate Team and
        //check that it's not null.
        if (orgs != null && orgs.size() > 0) {
            List <ProfileOrganizationViewModel> orgViewModel = translateOrgs(orgs);
            viewModel.setOrganizationList(orgViewModel);
        }

        if (powers != null && powers.size() > 0) {
            List <ProfilePowerViewModel> powerViewModel = translatePowers(powers);
            viewModel.setPowerList(powerViewModel);
        }

        if (sightings != null && sightings.size() > 0) {
            List <ProfileSightingViewModel> sightingViewModel = translateSightings(sightings);
            viewModel.setSightingList(sightingViewModel);
        }

        //Return stuff
        return viewModel;
    }

    private List<ProfileOrganizationViewModel> translateOrgs(List<Organization> organizations) {
        List<ProfileOrganizationViewModel> organizationViewModel = new ArrayList<>();

        for (Organization org : organizations) {
            ProfileOrganizationViewModel vm = new ProfileOrganizationViewModel();
            vm.setId(org.getId());
            vm.setName(org.getName());
            organizationViewModel.add(vm);
        }

        return organizationViewModel;
    }

    private List<ProfileSightingViewModel> translateSightings(List<Sighting> sightings) {
        List<ProfileSightingViewModel> sightingViewModels = new ArrayList<>();

        for (Sighting sighting : sightings) {
            ProfileSightingViewModel vm = new ProfileSightingViewModel();
            vm.setId(sighting.getId());
            Location location = locationService.retrieve(sighting.getLocation().getId());
            vm.setName(location.getName());
            //vm.setName(sighting.getLocation().getName());
            sightingViewModels.add(vm);
        }

        return sightingViewModels;
    }

    private List<ProfilePowerViewModel> translatePowers(List<Power> powers) {
        List<ProfilePowerViewModel> powerViewModels = new ArrayList<>();

        for (Power power : powers) {
            ProfilePowerViewModel vm = new ProfilePowerViewModel();
            vm.setName(power.getName());
            vm.setId(power.getId());
            powerViewModels.add(vm);
        }

        return powerViewModels;
    }

    @Override
    public CreateSuperPersonViewModel getCreateSuperPersonViewModel() {
        CreateSuperPersonViewModel result = new CreateSuperPersonViewModel();

        List<Organization> organizationList = this.organizationService.retrieveAll(Integer.MAX_VALUE,0);
        List<DropdownViewModel> dropdownOrgs = new ArrayList<>();
        for(Organization org: organizationList){
            DropdownViewModel ddi = new DropdownViewModel();
            ddi.setId(org.getId());
            ddi.setName(org.getName());
            dropdownOrgs.add(ddi);
        }
        result.setOrganizations(dropdownOrgs);

        List<Power> powers = this.powerService.retrieveAll(Integer.MAX_VALUE,0);
        List<DropdownViewModel> dropdownpowers = new ArrayList<>();
        for(Power power: powers){
            DropdownViewModel ddi = new DropdownViewModel();
            ddi.setId(power.getId());
            ddi.setName(power.getName());
            dropdownpowers.add(ddi);
        }
        result.setPowers(dropdownpowers);

        result.setCommandModel(new CreateSuperPersonCommandModel());

        return result;
    }

    @Override
    public Super saveCreateSuperPersonCommandModel(CreateSuperPersonCommandModel commandModel) {
        Super superPerson = new Super();
        superPerson.setName(commandModel.getName());
        superPerson.setDescription(commandModel.getDescription());

        superPerson = superService.create(superPerson);
        //List<PlayerPosition> playerPositions = new ArrayList<>();
        for (Long orgId : commandModel.getOrganizationId()) {
            SuperOrganization superOrganization = new SuperOrganization();
            superOrganization.setSuperPerson(superPerson);
            Organization org = organizationService.retrieve(orgId);
            superOrganization.setOrganization(org);
            superOrganization = superOrganizationService.create(superOrganization);
            //playerPositions.add(playerPosition);
        }

        for (Long powerId : commandModel.getPowerId()) {
            SuperPower superPower = new SuperPower();
            superPower.setSuperPerson(superPerson);
            Power power = powerService.retrieve(powerId);
            superPower.setPower(power);
            superPower = superPowerService.create(superPower);
        }

        return superPerson;
    }

    @Override
    public EditSuperPersonViewModel getEditSuperPersonViewMode(Long id) {
        EditSuperPersonViewModel model = new EditSuperPersonViewModel();
        Super superPerson = this.superService.retrieve(id);
        List<Organization> orgs = this.organizationService.retrieveAll(Integer.MAX_VALUE,0);
        List<Power> powers = this.powerService.retrieveAll(Integer.MAX_VALUE, 0);
        EditSuperPersonCommandModel commandModel = new EditSuperPersonCommandModel();
        model.setOrganizations(new ArrayList<DropdownViewModel>());
        model.setPowers(new ArrayList<DropdownViewModel>());
        for (Organization currentOrg:orgs) {
            DropdownViewModel ddl = new DropdownViewModel();
            ddl.setId(currentOrg.getId());
            ddl.setName(currentOrg.getName());
            model.getOrganizations().add(ddl);
        }

        for (Power currentPower : powers) {
            DropdownViewModel ddvm = new DropdownViewModel();
            ddvm.setId(currentPower.getId());
            ddvm.setName(currentPower.getName());
            model.getPowers().add(ddvm);
        }

        List<Power> superPowers = powerService.retrievePowersBySuper(superPerson,Integer.MAX_VALUE,0);
        List<Long> powerIds = superPowers
                .stream()
                .map(x -> x.getId())
                .collect(Collectors.toList());

        List<Organization> superOrgs = organizationService.retrieveOrganizationsBySuper(superPerson,Integer.MAX_VALUE,0);
        List<Long> orgIds = superOrgs
                .stream()
                .map(x -> x.getId())
                .collect(Collectors.toList());

        commandModel.setId(superPerson.getId());
        commandModel.setName(superPerson.getName());
        commandModel.setDescription(superPerson.getDescription());

        commandModel.setOrganizationId(orgIds.toArray(new Long[orgIds.size()]));
        commandModel.setPowerId(powerIds.toArray(new Long[powerIds.size()]));
        model.setCommandModel(commandModel);
        return model;
    }

    @Override
    public void saveEditSuperPersonCommandModel(EditSuperPersonCommandModel commandModel) {

        Super superPerson = superService.retrieve(commandModel.getId());
        if(superPerson == null) return;
        superPerson.setName(commandModel.getName());
        superPerson.setDescription(commandModel.getDescription());

        superService.update(superPerson);
        //update the supers
        List<Organization> superOrgs = this.organizationService.retrieveOrganizationsBySuper(superPerson,Integer.MAX_VALUE,0);
        for (Organization currentOrg : superOrgs) {
            SuperOrganization so = new SuperOrganization();
            so.setSuperPerson(superPerson);
            so.setOrganization(currentOrg);
            this.superOrganizationService.delete(so);

        }
        for (Long orgId :
                commandModel.getOrganizationId()) {
            SuperOrganization so = new SuperOrganization();
            so.setSuperPerson(superPerson);
            so.setOrganization(this.organizationService.retrieve(orgId));
            this.superOrganizationService.create(so);
        }

        List<Power> superPowers = this.powerService.retrievePowersBySuper(superPerson,Integer.MAX_VALUE,0);
        for (Power currentPower : superPowers) {
            SuperPower sp = new SuperPower();
            sp.setSuperPerson(superPerson);
            sp.setPower(currentPower);
            this.superPowerService.delete(sp);

        }
        for (Long powerId :
                commandModel.getPowerId()) {
            SuperPower sp = new SuperPower();
            sp.setSuperPerson(superPerson);
            sp.setPower(this.powerService.retrieve(powerId));
            this.superPowerService.create(sp);
        }

    }

    @Override
    public void deleteSuperPerson(Long id) {

        Super superPerson = superService.retrieve(id);
        // if the player doesn't exist, no need to continue
        if(superPerson == null) return;

        // must remove any references of player id from other tables.
        List<Power> superPowers = powerService.retrievePowersBySuper(superPerson, Integer.MAX_VALUE,0);
        for (Power currentPower: superPowers) {
            SuperPower sp = new SuperPower();
            sp.setSuperPerson(superPerson);
            sp.setPower(currentPower);
            this.superPowerService.delete(sp);
        }

        List<Organization> superOrgs = organizationService.retrieveOrganizationsBySuper(superPerson, Integer.MAX_VALUE,0);
        for (Organization currentOrg: superOrgs) {
            SuperOrganization so = new SuperOrganization();
            so.setSuperPerson(superPerson);
            so.setOrganization(currentOrg);
            this.superOrganizationService.delete(so);
        }

        List<Sighting> superSightings = sightingService.retrieveSightingsBySuper(superPerson, Integer.MAX_VALUE,0);
        for (Sighting currentSighting: superSightings) {
            SuperSighting ss = new SuperSighting();
            ss.setSuperPerson(superPerson);
            ss.setSighting(currentSighting);
            this.superSightingService.delete(ss);
        }

        // when all foreign key are deleted, we are now allowed to delete the player
        superService.delete(superPerson);

    }
}
