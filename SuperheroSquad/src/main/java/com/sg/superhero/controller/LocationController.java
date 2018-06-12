package com.sg.superhero.controller;

import com.sg.superhero.dto.Location;
import com.sg.superhero.viewmodels.location.create.CreateLocationCommandModel;
import com.sg.superhero.viewmodels.location.create.CreateLocationViewModel;
import com.sg.superhero.viewmodels.location.edit.EditLocationCommandModel;
import com.sg.superhero.viewmodels.location.edit.EditLocationViewModel;
import com.sg.superhero.viewmodels.location.list.ListLocationViewModel;
import com.sg.superhero.webservice.interfaces.LocationWebService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/location")
public class LocationController {

    LocationWebService locationWebService;


    @Inject
    public LocationController(LocationWebService locationWebService) {
        this.locationWebService = locationWebService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@RequestParam(required = false) Integer offset, Model model) {
        ListLocationViewModel viewModel = locationWebService.getLocationListViewModel(offset);

        model.addAttribute("viewModel", viewModel);

        return "location/list";
    }//*


    //@RequestMapping(value = "/create", method = RequestMethod.POST)
    @RequestMapping(value = "/create")
    public String create( Model model) {
    //public String create() {
        CreateLocationViewModel viewModel = locationWebService.getCreateLocationViewModel();

        model.addAttribute("viewModel",viewModel);
        model.addAttribute("commandModel",viewModel.getCommandModel());

        return "location/create";
    }

    @RequestMapping(value = "/create", method= RequestMethod.POST)
    public String saveCreate(@Valid @ModelAttribute("commandModel")
                                     CreateLocationCommandModel commandModel,
                             BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            CreateLocationViewModel viewModel =
                    locationWebService.getCreateLocationViewModel();

            model.addAttribute("viewModel",viewModel);
            model.addAttribute("commandModel",commandModel);
            return "location/create";
        }
        Location location = locationWebService.saveCreateLocationCommandModel(commandModel);

        return "redirect:/location/show?id=" + location.getId();
    }

    @RequestMapping (value= "/edit")
    public String edit(@RequestParam Long id, Model model){

        EditLocationViewModel viewModel = locationWebService.getEditLocationViewModel(id);
        model.addAttribute("commandModel", viewModel.getCommandModel());
        return "location/edit";
    }

    public String saveEdit(@Valid @ModelAttribute("commandModel") EditLocationCommandModel commandModel, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            EditLocationViewModel viewModel = locationWebService.getEditLocationViewModel(commandModel.getId());

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);
            return "location/edit";
        }
        locationWebService.saveEditLocationCommandModel(commandModel);

        return "redirect:/location/show?id=" + commandModel.getId();
    }





}

