package com.sg.superhero.webservice;

import com.sg.superhero.dto.Location;
import com.sg.superhero.service.interfaces.LocationService;
import com.sg.superhero.util.PagingUtils;
import com.sg.superhero.viewmodels.location.create.CreateLocationCommandModel;
import com.sg.superhero.viewmodels.location.create.CreateLocationViewModel;
import com.sg.superhero.viewmodels.location.edit.EditLocationCommandModel;
import com.sg.superhero.viewmodels.location.edit.EditLocationViewModel;
import com.sg.superhero.viewmodels.location.list.ListLocationViewModel;
import com.sg.superhero.viewmodels.location.list.LocationViewModel;
import com.sg.superhero.viewmodels.location.profile.ProfileLocationViewModel;
import com.sg.superhero.webservice.interfaces.LocationWebService;
import org.eclipse.core.internal.dtree.TestHelper;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LocationWebServiceImpl implements LocationWebService {


    LocationService locationService;

    @Inject
    public LocationWebServiceImpl(LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    public ListLocationViewModel getLocationListViewModel(Integer offset) {
        Integer limit = 10;
        ListLocationViewModel viewModel = new ListLocationViewModel();

        int selectedPage = PagingUtils.getSelectedPage(offset, limit);
        int[] pageNumbers = PagingUtils.getPageNumbers(5, selectedPage);

        //Translate from domain to view models
        List<Location> locationList = locationService.retrieveAll(limit, offset);
        List<LocationViewModel> locations = translate(locationList);

        viewModel.setSelectedPage(selectedPage);
        //viewModel.setSelectedPage(selectedPage);
        viewModel.setPageNumbers(pageNumbers);
        viewModel.setLocations(locations);

        return viewModel;
    }

    private List<LocationViewModel> translate(List<Location> locationList) {

        List<LocationViewModel> viewModels = new ArrayList<>();

        for (Location location: locationList) {
            LocationViewModel viewModel = new LocationViewModel();
            viewModel.setName(location.getName());
            viewModels.add(viewModel);
        }

        return viewModels;

    }

    @Override
    public ProfileLocationViewModel getLocationProfileViewModel(Long id) {
        //Instantiate stuff
        ProfileLocationViewModel viewModel = new ProfileLocationViewModel();

        //Look up stuff
        if (locationService.retrieve(id) == null) return null;
        Location location = locationService.retrieve(id);

        //Set stuff
        viewModel.setName(location.getName());
        viewModel.setDescription(location.getDescription());
        viewModel.setStreet(location.getStreet());
        viewModel.setCity(location.getCity());
        viewModel.setState(location.getState());
        viewModel.setZip(location.getZip());
        viewModel.setLatitude(location.getLatitude());
        viewModel.setLongitude(location.getLongitude());

//        //Return stuff
        return viewModel;
    }

    @Override
    public CreateLocationViewModel getCreateLocationViewModel() {
        //No dropdowns on the screen - don't need this method.
        return null;
    }

    @Override
    public Location saveCreateLocationCommandModel(CreateLocationCommandModel createLocationCommandModel) {

        Location location = new Location();
        location.setName(createLocationCommandModel.getName());
        location.setDescription(createLocationCommandModel.getDescription());
        location.setStreet(createLocationCommandModel.getStreet());
        location.setCity(createLocationCommandModel.getCity());
        location.setState(createLocationCommandModel.getState());
        location.setZip(createLocationCommandModel.getZip());
        location.setLatitude(createLocationCommandModel.getLatitude());
        location.setLongitude(createLocationCommandModel.getLongitude());
//        Team team = teamService.read(commandModel.getTeamId());
//        player.setTeam(team);

        location = locationService.create(location);
        return location;
    }

    @Override
    public EditLocationViewModel getEditLocationViewModel(Long id) {
        EditLocationViewModel model = new EditLocationViewModel();
        Location location = this.locationService.retrieve(id);

        EditLocationCommandModel commandModel = new EditLocationCommandModel();

        commandModel.setId(location.getId());
        commandModel.setName(location.getName());
        commandModel.setDescription(location.getDescription());
        commandModel.setStreet(location.getStreet());
        commandModel.setCity(location.getCity());
        commandModel.setState(location.getState());
        commandModel.setZip(location.getZip());
        commandModel.setLatitude(location.getLatitude());
        commandModel.setLongitude(location.getLongitude());
//
        model.setCommandModel(commandModel);
        return model;
    }

    @Override
    public void saveEditLocationCommandModel(EditLocationCommandModel editLocationCommandModel) {

        Location location = locationService.retrieve(editLocationCommandModel.getId());
        if(location == null) return;

        location.setName(editLocationCommandModel.getName());
        location.setDescription(editLocationCommandModel.getDescription());
        location.setStreet(editLocationCommandModel.getStreet());
        location.setCity(editLocationCommandModel.getCity());
        location.setState(editLocationCommandModel.getState());
        location.setZip(editLocationCommandModel.getZip());
        location.setLatitude(editLocationCommandModel.getLatitude());
        location.setLongitude(editLocationCommandModel.getLongitude());

        locationService.update(location);

    }

    @Override
    public void deleteLocation(Long id) {

        //TODO: determine if location needs to be deleted elsewhere.

        Location location = locationService.retrieve(id);

        locationService.delete(location);

    }
}
