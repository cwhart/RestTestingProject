package com.sg.superhero.webservice;

import com.sg.superhero.dto.Location;
import com.sg.superhero.dto.Organization;
import com.sg.superhero.dto.Super;
import com.sg.superhero.dto.SuperOrganization;
import com.sg.superhero.service.interfaces.LocationService;
import com.sg.superhero.service.interfaces.OrganizationService;
import com.sg.superhero.service.interfaces.SuperOrganizationService;
import com.sg.superhero.service.interfaces.SuperService;
import com.sg.superhero.util.PagingUtils;
import com.sg.superhero.viewmodels.DropdownViewModel;
import com.sg.superhero.viewmodels.location.edit.EditLocationCommandModel;
import com.sg.superhero.viewmodels.location.edit.EditLocationViewModel;
import com.sg.superhero.viewmodels.organization.create.CreateOrganizationCommandModel;
import com.sg.superhero.viewmodels.organization.create.CreateOrganizationViewModel;
import com.sg.superhero.viewmodels.organization.edit.EditOrganizationCommandModel;
import com.sg.superhero.viewmodels.organization.edit.EditOrganizationViewModel;
import com.sg.superhero.viewmodels.organization.list.ListOrganizationViewModel;
import com.sg.superhero.viewmodels.organization.list.OrganizationViewModel;
import com.sg.superhero.viewmodels.organization.profile.ProfileOrganizationViewModel;
import com.sg.superhero.webservice.interfaces.OrganizationWebService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrganizationWebServiceImpl implements OrganizationWebService {

    @Inject
    OrganizationService organizationService;

    @Inject
    LocationService locationService;

    @Inject
    SuperOrganizationService superOrganizationService;

    @Inject
    SuperService superService;

    @Override
    public ListOrganizationViewModel getListOrganizationViewModel(Integer offset) {
        Integer limit = 10;
        ListOrganizationViewModel viewModel = new ListOrganizationViewModel();

        int selectedPage = PagingUtils.getSelectedPage(offset, limit);
        int[] pageNumbers = PagingUtils.getPageNumbers(5, selectedPage);

        //Translate from domain to view models
        List<Organization> organizationList = organizationService.retrieveAll(limit, offset);
        List<OrganizationViewModel> orgs = translate(organizationList);

        viewModel.setSelectedPage(selectedPage);
        //viewModel.setSelectedPage(selectedPage);
        viewModel.setPageNumbers(pageNumbers);
        viewModel.setOrganizations(orgs);

        return viewModel;
    }

    private List<OrganizationViewModel> translate(List<Organization> orgList) {

        List<OrganizationViewModel> viewModels = new ArrayList<>();

        for (Organization organization: orgList) {
            OrganizationViewModel viewModel = new OrganizationViewModel();
            viewModel.setName(organization.getName());
            viewModel.setId(organization.getId());
            viewModels.add(viewModel);


            //viewModel.setLast(player.getLast());

//            if(player.getTeam() != null) {
//                viewModel.setTeamName(player.getTeam().getCity() + " " + player.getTeam().getNickname());
//                viewModel.setPlayerId(player.getId());
//                viewModel.setTeamId(player.getTeam().getId());
//                viewModels.add(viewModel);
//            }
        }

        return viewModels;

    }

    @Override
    public ProfileOrganizationViewModel getOrganizationProfileViewModel(Long id) {
        ProfileOrganizationViewModel viewModel = new ProfileOrganizationViewModel();

        if (organizationService.retrieve(id) == null) return null;
        Organization org = organizationService.retrieve(id);
        Location location = null;
        if (org.getLocation() != null) {
            location = locationService.retrieve(org.getLocation().getId());
        }

        viewModel.setName(org.getName());
        viewModel.setDescription(org.getDescription());
        viewModel.setEmail(org.getEmail());
        viewModel.setPhone(org.getPhone());
        viewModel.setLocatonId(location.getId());
        viewModel.setLocation(location.getName());

        return viewModel;
    }

    @Override
    public CreateOrganizationViewModel getCreateOrganizationViewModel() {
        CreateOrganizationViewModel result = new CreateOrganizationViewModel();

        List<Location> locations = this.locationService.retrieveAll(Integer.MAX_VALUE,0);
        List<DropdownViewModel> dropdownLocations = new ArrayList<>();
        for(Location location: locations){
            DropdownViewModel ddi = new DropdownViewModel();
            ddi.setId(location.getId());
            ddi.setName(location.getName());

            dropdownLocations.add(ddi);
        }
        result.setLocations(dropdownLocations);

        result.setCommandModel(new CreateOrganizationCommandModel());

        return result;
    }

    @Override
    public Organization saveCreateOrganizationCommandModel(CreateOrganizationCommandModel commandModel) {
        Organization organization = new Organization();
        organization.setName(commandModel.getName());
        organization.setEmail(commandModel.getEmail());
        organization.setPhone(commandModel.getPhone());
        organization.setDescription(commandModel.getDescription());

        Location location = locationService.retrieve(commandModel.getLocationId());
        organization.setLocation(location);

        organization = organizationService.create(organization);

        return organization;
    }

    @Override
    public EditOrganizationViewModel getEditOrganizationViewModel(Long id) {
        EditOrganizationViewModel model = new EditOrganizationViewModel();
        Organization organization = this.organizationService.retrieve(id);
        List<Location> locations = this.locationService.retrieveAll(Integer.MAX_VALUE,0);

        EditOrganizationCommandModel commandModel = new EditOrganizationCommandModel();
        model.setLocations(new ArrayList<DropdownViewModel>());

        for (Location location:locations) {
            DropdownViewModel ddl = new DropdownViewModel();
            ddl.setId(location.getId());
            ddl.setName(location.getName());
            model.getLocations().add(ddl);
        }

        commandModel.setId(organization.getId());
        commandModel.setName(organization.getName());
        commandModel.setDescription(organization.getDescription());
        commandModel.setLocationId(organization.getLocation().getId());
        commandModel.setPhone(organization.getPhone());
        commandModel.setEmail(organization.getEmail());

        model.setCommandModel(commandModel);
        return model;
    }

    @Override
    public void saveEditOrganizationCommandModel(EditOrganizationCommandModel commandModel) {

        Organization organization = organizationService.retrieve(commandModel.getId());
        if(organization == null) return;
        organization.setLocation(this.locationService.retrieve(commandModel.getLocationId()));
        organization.setName(commandModel.getName());
        organization.setDescription(commandModel.getDescription());
        organization.setPhone(commandModel.getPhone());
        organization.setEmail(commandModel.getEmail());

        organizationService.update(organization);

    }

    @Override
    public void deleteOrganization(Long id) {
        Organization organization = organizationService.retrieve(id);
        List<Super> supers = superService.retrieveSupersByOrganization(organization, Integer.MAX_VALUE, 0);

        for (Super superPerson: supers) {
            SuperOrganization superOrganization = new SuperOrganization();
            superOrganization.setOrganization(organization);
            superOrganization.setSuperPerson(superPerson);
            superOrganizationService.delete(superOrganization);
        }

        organizationService.delete(organization);
    }
}
