package com.sg.superhero.webservice;

import com.sg.superhero.dto.Power;
import com.sg.superhero.dto.Super;
import com.sg.superhero.dto.SuperPower;
import com.sg.superhero.service.interfaces.PowerService;
import com.sg.superhero.service.interfaces.SuperPowerService;
import com.sg.superhero.service.interfaces.SuperService;
import com.sg.superhero.util.PagingUtils;
import com.sg.superhero.viewmodels.DropdownViewModel;
import com.sg.superhero.viewmodels.power.create.CreatePowerCommandModel;
import com.sg.superhero.viewmodels.power.create.CreatePowerViewModel;
import com.sg.superhero.viewmodels.power.edit.EditPowerCommandModel;
import com.sg.superhero.viewmodels.power.edit.EditPowerViewModel;
import com.sg.superhero.viewmodels.power.list.ListPowerViewModel;
import com.sg.superhero.viewmodels.power.list.PowerViewModel;
import com.sg.superhero.viewmodels.power.profile.ProfilePowerViewModel;
import com.sg.superhero.viewmodels.power.profile.ProfileSuperViewModel;
import com.sg.superhero.webservice.interfaces.PowerWebService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PowerWebServiceImpl implements PowerWebService {

    @Inject
    PowerService powerService;

    @Inject
    SuperService superService;

    @Inject
    SuperPowerService superPowerService;

    @Override
    public ListPowerViewModel getPowerListViewModel(Integer offset) {
        Integer limit = 10;
        ListPowerViewModel viewModel = new ListPowerViewModel();

        int selectedPage = PagingUtils.getSelectedPage(offset, limit);
        int[] pageNumbers = PagingUtils.getPageNumbers(5, selectedPage);

        //Translate from domain to view models
        List<Power> powerList = powerService.retrieveAll(limit, offset);
        List<PowerViewModel> powers = translate(powerList);

        viewModel.setSelectedPage(selectedPage);
        //viewModel.setSelectedPage(selectedPage);
        viewModel.setPageNumbers(pageNumbers);
        viewModel.setPowers(powers);

        return viewModel;
    }

    private List<PowerViewModel> translate(List<Power> powerList) {

        List<PowerViewModel> viewModels = new ArrayList<>();

        for (Power power: powerList) {
            PowerViewModel viewModel = new PowerViewModel();
            viewModel.setName(power.getName());
            viewModel.setId(power.getId());
            viewModels.add(viewModel);
        }

        return viewModels;
    }

    @Override
    public ProfilePowerViewModel getPowerProfileViewModel(Long id) {
        //Instantiate stuff
        ProfilePowerViewModel viewModel = new ProfilePowerViewModel();

        //Look up stuff
        Power power = powerService.retrieve(id);

        List<Super> supers = superService.retrieveSupersByPower(power, Integer.MAX_VALUE, 0);


        //Set stuff
        viewModel.setName(power.getName());

        //Lazy loaded - can't rely on the queries to return, hence additional logic above to instantiate Team and
        //check that it's not null.
        if (supers != null && supers.size() > 0) {
            List <ProfileSuperViewModel> superViewModels = translateSupers(supers);
            viewModel.setSupers(superViewModels);
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
    public CreatePowerViewModel getCreatePowerViewModel() {
        CreatePowerViewModel result = new CreatePowerViewModel();

        List<Super> supers = this.superService.retrieveAll(Integer.MAX_VALUE,0);
        List<DropdownViewModel> dropdownSupers = new ArrayList<>();
        for(Super superPerson: supers){
            DropdownViewModel ddi = new DropdownViewModel();
            ddi.setId(superPerson.getId());
            ddi.setName(superPerson.getName());
            dropdownSupers.add(ddi);
        }
        result.setSuperPeople(dropdownSupers);

        result.setCommandModel(new CreatePowerCommandModel());

        return result;
    }

    @Override
    public Power saveCreatePowerCommandModel(CreatePowerCommandModel commandModel) {
        Power power = new Power();
        power.setName(commandModel.getName());

        power = powerService.create(power);
        //List<PlayerPosition> playerPositions = new ArrayList<>();
        for (Long superId : commandModel.getSuperId()) {
            SuperPower superPower = new SuperPower();
            superPower.setPower(power);
            Super superPerson = superService.retrieve(superId);
            superPower.setSuperPerson(superPerson);
            superPower = superPowerService.create(superPower);
            //playerPositions.add(playerPosition);
        }

        return power;
    }

    @Override
    public EditPowerViewModel getEditPowerViewModel(Long id) {
        EditPowerViewModel model = new EditPowerViewModel();
        Power power = this.powerService.retrieve(id);
        List<Super> supers = this.superService.retrieveAll(Integer.MAX_VALUE,0);
        EditPowerCommandModel commandModel = new EditPowerCommandModel();
        model.setSuperPeople(new ArrayList<DropdownViewModel>());
        for (Super superPerson:supers) {
            DropdownViewModel ddl = new DropdownViewModel();
            ddl.setId(superPerson.getId());
            ddl.setName(superPerson.getName());
            model.getSuperPeople().add(ddl);
        }
        List<Super> powerSupers = superService.retrieveSupersByPower(power,Integer.MAX_VALUE,0);
        List<Long> superIds = powerSupers
                .stream()
                .map(x -> x.getId())
                .collect(Collectors.toList());

        commandModel.setId(power.getId());
        commandModel.setName(power.getName());

        commandModel.setSuperId(superIds.toArray(new Long[superIds.size()]));
        model.setCommandModel(commandModel);
        return model;
    }

    @Override
    public void saveEditPowerCommandModel(EditPowerCommandModel commandModel) {

        Power power = powerService.retrieve(commandModel.getId());
        if(power == null) return;
        power.setName(commandModel.getName());

        powerService.update(power);
        //update the supers
        List<Super> superPowers = this.superService.retrieveSupersByPower(power,Integer.MAX_VALUE,0);
        for (Super superPerson : superPowers) {
            SuperPower sp = new SuperPower();
            sp.setSuperPerson(superPerson);
            sp.setPower(power);
            this.superPowerService.delete(sp);

        }
        for (Long superId :
                commandModel.getSuperId()) {
            SuperPower sp = new SuperPower();
            sp.setPower(power);
            sp.setSuperPerson(this.superService.retrieve(superId));
            this.superPowerService.create(sp);
        }
    }

    @Override
    public void deletePower(Long id) {

        Power power = powerService.retrieve(id);
        // if the player doesn't exist, no need to continue
        if(power == null) return;

        // must remove any references of super id from other tables.
        List<Super> superPowers = superService.retrieveSupersByPower(power, Integer.MAX_VALUE,0);
        for (Super superPerson: superPowers) {
            SuperPower sp = new SuperPower();
            sp.setSuperPerson(superPerson);
            sp.setPower(power);
            this.superPowerService.delete(sp);
        }

        // when all foreign key are deleted, we are now allowed to delete the player
        powerService.delete(power);

    }
}
