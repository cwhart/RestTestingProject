package com.sg.superhero.controller;

import com.sg.superhero.dto.Sighting;
import com.sg.superhero.viewmodels.sighting.create.CreateSightingCommandModel;
import com.sg.superhero.viewmodels.sighting.create.CreateSightingViewModel;
import com.sg.superhero.viewmodels.sighting.edit.EditSightingCommandModel;
import com.sg.superhero.viewmodels.sighting.edit.EditSightingViewModel;
import com.sg.superhero.viewmodels.sighting.list.ListSightingViewModel;
import com.sg.superhero.viewmodels.sighting.profile.ProfileSightingViewModel;
import com.sg.superhero.webservice.interfaces.SightingWebService;
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
@RequestMapping(value = "/sighting")
public class SightingController {

    private SightingWebService sightingWebService;

    @Inject
    public SightingController(SightingWebService sightingWebService) {
        this.sightingWebService = sightingWebService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@RequestParam(required = false) Integer offset, Model model) {
        if (offset == null) {
            offset = 0;
        }

        ListSightingViewModel viewModel = sightingWebService.getListSightingViewModel(offset);

        model.addAttribute("viewModel", viewModel);

        return "sighting/list";
    }

    @RequestMapping(value= "/edit")
    public String edit(@RequestParam Long id, Model model) {

        EditSightingViewModel viewModel = sightingWebService.getEditSightingViewMode(id);

        model.addAttribute("viewModel", viewModel);
        model.addAttribute("commandModel", viewModel.getCommandModel());

        return "sighting/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEdit(@Valid @ModelAttribute("commandModel") EditSightingCommandModel commandModel,
                           BindingResult bindingResult, Model model) { //Take in model in case we need to return
        //to the edit page - i.e. if the user typed in an invalid value, this will save
        //the values they previously input.
        if(bindingResult.hasErrors()) {
            EditSightingViewModel viewModel = sightingWebService.getEditSightingViewMode(commandModel.getSightingId());

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);
            return "sighting/edit";
        }

        sightingWebService.saveEditSightingCommandModel(commandModel);

        return "redirect:/sighting/profile?id=" + commandModel.getSightingId();
    }

    @RequestMapping(value= "/create", method = RequestMethod.GET)
    public String create(Model model) {

        CreateSightingViewModel viewModel = sightingWebService.getCreateSightingViewModel();

        model.addAttribute("viewModel", viewModel);
        model.addAttribute("commandModel", viewModel.getCommandModel());

        return "sighting/create";
    }

    @RequestMapping(value = "/create", method= RequestMethod.POST)
    public String savecreate(@Valid @ModelAttribute("commandModel") CreateSightingCommandModel commandModel,
                             BindingResult bindingResult, Model model) { //Take in model in case we need to return
        //to the edit page - i.e. if the user typed in an invalid value, this will save
        //the values they previously input.
        if(bindingResult.hasErrors()) {
            CreateSightingViewModel viewModel = sightingWebService.getCreateSightingViewModel();

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);
            return "sighting/create";
        }

        Sighting sighting = sightingWebService.saveCreateSightingCommandModel(commandModel);

        return "redirect:/sighting/profile?id=" + sighting.getId();
    }

    @RequestMapping(value = "/profile")
    public String profile(@RequestParam Long id, Model model) {
        ProfileSightingViewModel viewModel = sightingWebService.getProfileSightingViewModel(id);

        model.addAttribute("viewModel",viewModel);

        return "sighting/profile";
    }

    @RequestMapping(value = "/delete")
    public String delete(@RequestParam Long id, Model model) {
        sightingWebService.deleteSighting(id);

        return "redirect:/sighting/list";
    }
}
