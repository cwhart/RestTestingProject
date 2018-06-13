package com.sg.superhero.webservice;

import com.sg.superhero.dto.Location;
import com.sg.superhero.dto.Sighting;
import com.sg.superhero.dto.Super;
import com.sg.superhero.dto.SuperSighting;
import com.sg.superhero.service.interfaces.LocationService;
import com.sg.superhero.service.interfaces.SightingService;
import com.sg.superhero.service.interfaces.SuperService;
import com.sg.superhero.service.interfaces.SuperSightingService;
import com.sg.superhero.util.PagingUtils;
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

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SightingWebServiceImpl implements SightingWebService {

    @Inject
    SightingService sightingService;

    @Inject
    LocationService locationService;

    @Inject
    SuperService superService;

    @Inject
    SuperSightingService superSightingService;

    @Override
    public ListSightingViewModel getListSightingViewModel(Integer offset) {
        Integer limit = 10;
        ListSightingViewModel viewModel = new ListSightingViewModel();

        int selectedPage = PagingUtils.getSelectedPage(offset, limit);
        int[] pageNumbers = PagingUtils.getPageNumbers(5, selectedPage);

        //Translate from domain to view models
        List<Sighting> sightingList = sightingService.retrieveAll(limit, offset);
        List<SightingViewModel> sightings = translate(sightingList);

        viewModel.setSelectedPage(selectedPage);
        //viewModel.setSelectedPage(selectedPage);
        viewModel.setPageNumbers(pageNumbers);
        viewModel.setLocations(sightings);

        return viewModel;
    }

    private List<SightingViewModel> translate(List<Sighting> sightingList) {

        List<SightingViewModel> viewModels = new ArrayList<>();

        for (Sighting sighting: sightingList) {
            SightingViewModel viewModel = new SightingViewModel();
            viewModel.setDate(sighting.getDate());
            //viewModel.setLocation(sighting.getLocation().getName());
            viewModel.setLocationId(sighting.getLocation().getId());


            if(sighting.getLocation() != null) {
                String locationName = locationService.retrieve(viewModel.getLocationId()).getName();
                viewModel.setLocation(locationName);
                //viewModels.add(viewModel);
            }
            viewModels.add(viewModel);
        }

        return viewModels;
    }

    @Override
    public ProfileSightingViewModel getProfileSightingViewModel(Long id) {
        //Instantiate stuff
        ProfileSightingViewModel viewModel = new ProfileSightingViewModel();

        //Look up stuff
        Sighting sighting = sightingService.retrieve(id);

        List<Super> supers = superService.retrieveSupersBySighting(sighting, Integer.MAX_VALUE, 0);


        //Set stuff
        Long locationId = sighting.getLocation().getId();
        Location location = locationService.retrieve(locationId);


        viewModel.setDate(sighting.getDate());
        viewModel.setLocation(location.getName());
        viewModel.setLocationId(locationId);

        //Lazy loaded - can't rely on the queries to return, hence additional logic above to instantiate Team and
        //check that it's not null.
        if (supers != null && supers.size() > 0) {
            List <ProfileSuperViewModel> superViewModels = translateSupers(supers);
            viewModel.setSuperList(superViewModels);
        }

        //Return stuff
        return viewModel;
    }

    private List<ProfileSuperViewModel> translateSupers(List<Super> supers) {
        List<ProfileSuperViewModel> positionViewModels = new ArrayList<>();

        for (Super superPerson : supers) {
            ProfileSuperViewModel vm = new ProfileSuperViewModel();
            vm.setId(superPerson.getId());
            vm.setSuperPersonName(superPerson.getName());
            positionViewModels.add(vm);
        }

        return positionViewModels;
    }

    @Override
    public CreateSightingViewModel getCreateSightingViewModel() {
        CreateSightingViewModel result = new CreateSightingViewModel();

        List<Super> supers = this.superService.retrieveAll(Integer.MAX_VALUE,0);
        List<DropdownViewModel> dropdownSupers = new ArrayList<>();
        for(Super superPerson: supers){
            DropdownViewModel ddi = new DropdownViewModel();
            ddi.setId(superPerson.getId());
            ddi.setName(superPerson.getName());
            dropdownSupers.add(ddi);
        }
        result.setSuperPeople(dropdownSupers);

        List<Location> locations = this.locationService.retrieveAll(Integer.MAX_VALUE,0);
        List<DropdownViewModel> dropdownLocations = new ArrayList<>();
        for(Location location: locations){
            DropdownViewModel ddi = new DropdownViewModel();
            ddi.setId(location.getId());
            ddi.setName(location.getName());
            dropdownLocations.add(ddi);
        }
        result.setLocation(dropdownLocations);

        result.setCommandModel(new CreateSightingCommandModel());

        return result;    }

    @Override
    public Sighting saveCreateSightingCommandModel(CreateSightingCommandModel commandModel) {
        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.parse(commandModel.getDate()));
        Location location = locationService.retrieve(commandModel.getLocationId());
        sighting.setLocation(location);

        sighting = sightingService.create(sighting);
        //List<PlayerPosition> playerPositions = new ArrayList<>();
        for (Long superId : commandModel.getSuperId()) {
            SuperSighting superSighting = new SuperSighting();
            superSighting.setSighting(sighting);
            Super superPerson = superService.retrieve(superId);
            superSighting.setSuperPerson(superPerson);
            superSighting = superSightingService.create(superSighting);
            //playerPositions.add(playerPosition);
        }

        return sighting;
    }

    @Override
    public EditSightingViewModel getEditSightingViewMode(Long id) {
        EditSightingViewModel model = new EditSightingViewModel();
        Sighting sighting = this.sightingService.retrieve(id);
        List<Super> supers = this.superService.retrieveAll(Integer.MAX_VALUE,0);
        EditSightingCommandModel commandModel = new EditSightingCommandModel();
        model.setSuperPeople(new ArrayList<DropdownViewModel>());
        for (Super superPerson:supers) {
            DropdownViewModel ddl = new DropdownViewModel();
            ddl.setId(superPerson.getId());
            ddl.setName(superPerson.getName());
            model.getSuperPeople().add(ddl);
        }
        List<Super> sightingSupers = superService.retrieveSupersBySighting(sighting,Integer.MAX_VALUE,0);
        List<Long> superIds = sightingSupers
                .stream()
                .map(x -> x.getId())
                .collect(Collectors.toList());

        commandModel.setSightingId(sighting.getId());
        commandModel.setDate(sighting.getDate());
        commandModel.setLocationId(sighting.getLocation().getId());
        commandModel.setSuperIds(superIds.toArray(new Long[superIds.size()]));
        model.setCommandModel(commandModel);
        return model;
    }

    @Override
    public void saveEditSightingCommandModel(EditSightingCommandModel commandModel) {

        Sighting sighting = sightingService.retrieve(commandModel.getSightingId());
        if(sighting == null) return;
        sighting.setLocation(this.locationService.retrieve(commandModel.getLocationId()));
        sighting.setDate(commandModel.getDate());

        sightingService.update(sighting);
        //update the positions
        List<Super> sightingSupers = this.superService.retrieveSupersBySighting(sighting,Integer.MAX_VALUE,0);
        for (Super superPerson : sightingSupers) {
            SuperSighting ss = new SuperSighting();
            ss.setSuperPerson(superPerson);
            ss.setSighting(sighting);
            this.superSightingService.delete(ss);

        }
        for (Long superId :
                commandModel.getSuperIds()) {
            SuperSighting ss = new SuperSighting();
            ss.setSuperPerson(superService.retrieve(superId));
            ss.setSighting(sighting);
            this.superSightingService.create(ss);
        }

    }

    @Override
    public void deleteSighting(Long id) {

        Sighting sighting = sightingService.retrieve(id);
        // if the player doesn't exist, no need to continue
        if(sighting == null) return;

        // must remove any references of player id from other tables.
        List<Super> superPowers = superService.retrieveSupersBySighting(sighting, Integer.MAX_VALUE,0);
        for (Super superPerson: superPowers) {
            SuperSighting ss = new SuperSighting();
            ss.setSuperPerson(superPerson);
            ss.setSighting(sighting);
            this.superSightingService.delete(ss);
        }

        // when all foreign key are deleted, we are now allowed to delete the player
        sightingService.delete(sighting);

    }
}
