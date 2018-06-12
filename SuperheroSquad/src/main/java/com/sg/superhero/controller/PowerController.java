package com.sg.superhero.controller;

import com.sg.superhero.dto.Power;
import com.sg.superhero.viewmodels.power.create.CreatePowerCommandModel;
import com.sg.superhero.viewmodels.power.create.CreatePowerViewModel;
import com.sg.superhero.viewmodels.power.edit.EditPowerCommandModel;
import com.sg.superhero.viewmodels.power.edit.EditPowerViewModel;
import com.sg.superhero.viewmodels.power.list.ListPowerViewModel;
import com.sg.superhero.viewmodels.power.profile.ProfilePowerViewModel;
import com.sg.superhero.webservice.interfaces.PowerWebService;
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
@RequestMapping(value = "/power")
public class PowerController {

    private PowerWebService powerWebService;

    @Inject
    public PowerController(PowerWebService powerWebService) {
        this.powerWebService = powerWebService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@RequestParam(required = false) Integer offset, Model model) {
        if (offset == null) {
            offset = 0;
        }
        ListPowerViewModel viewModel = powerWebService.getPowerListViewModel(offset);

        model.addAttribute("viewModel", viewModel);

        return "power/list";
    }

    @RequestMapping(value = "/edit")
    public String edit(@RequestParam Long id, Model model) {
        EditPowerViewModel viewModel = powerWebService.getEditPowerViewModel(id);

        model.addAttribute("viewModel", viewModel);
        model.addAttribute("commandModel", viewModel.getCommandModel());

        return "power/edit";
    }

    @RequestMapping(value = "/edit", method= RequestMethod.POST)
    public String saveEdit(@Valid @ModelAttribute("commandModel") EditPowerCommandModel commandModel, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            EditPowerViewModel viewModel = powerWebService.getEditPowerViewModel(commandModel.getId());

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);
            return "power/edit";
        }
        powerWebService.saveEditPowerCommandModel(commandModel);

        return "redirect:/power/show?id=" + commandModel.getId();
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        CreatePowerViewModel viewModel = powerWebService.getCreatePowerViewModel();

        model.addAttribute("viewModel", viewModel);
        model.addAttribute("commandModel", viewModel.getCommandModel());

        return "power/create";
    }

    @RequestMapping(value = "/create", method= RequestMethod.POST)
    public String saveCreate(@Valid @ModelAttribute("commandModel") CreatePowerCommandModel commandModel, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            CreatePowerViewModel viewModel = powerWebService.getCreatePowerViewModel();

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);
            return "player/create";
        }
        Power power = powerWebService.saveCreatePowerCommandModel(commandModel);

        return "redirect:/power/show?id=" + power.getId();
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(@RequestParam Long id, Model model) {
        ProfilePowerViewModel viewModel = powerWebService.getPowerProfileViewModel(id);

        model.addAttribute("viewModel", viewModel);

        return "power/profile";
    }
}