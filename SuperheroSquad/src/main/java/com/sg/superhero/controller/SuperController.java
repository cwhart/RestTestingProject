package com.sg.superhero.controller;

import com.sg.superhero.dto.Super;
import com.sg.superhero.viewmodels.superPerson.create.CreateSuperPersonCommandModel;
import com.sg.superhero.viewmodels.superPerson.create.CreateSuperPersonViewModel;
import com.sg.superhero.viewmodels.superPerson.edit.EditSuperPersonCommandModel;
import com.sg.superhero.viewmodels.superPerson.edit.EditSuperPersonViewModel;
import com.sg.superhero.viewmodels.superPerson.list.ListSuperPersonViewModel;
import com.sg.superhero.viewmodels.superPerson.profile.ProfileSuperPersonViewModel;
import com.sg.superhero.webservice.interfaces.SuperPersonWebService;
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
@RequestMapping(value = "/super")
public class SuperController {
    private SuperPersonWebService superPersonWebService;

    @Inject
    public SuperController(SuperPersonWebService superPersonWebService) {
        this.superPersonWebService = superPersonWebService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@RequestParam(required = false) Integer offset, Model model) {
        if(offset ==null) {
            offset = 0;
        }
        ListSuperPersonViewModel viewModel = superPersonWebService.getListSuperPersonViewModel(offset);
        model.addAttribute("viewModel", viewModel);
        return "super/list";
    }

    @RequestMapping(value = "/profile")
    public String profile(@RequestParam (required = false) Long id, Model model) {
        ProfileSuperPersonViewModel viewModel = superPersonWebService.getProfileSuperPersonViewModel(id);
        model.addAttribute("viewModel", viewModel);
        return "super/profile";
    }

    @RequestMapping(value = "/edit")
    public String edit(@RequestParam Long id, Model model) {
        EditSuperPersonViewModel viewModel = superPersonWebService.getEditSuperPersonViewMode(id);
        model.addAttribute("viewModel", viewModel);
        model.addAttribute("commandModel", viewModel.getCommandModel());
        return "super/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEdit(@Valid @ModelAttribute("commandModel") EditSuperPersonCommandModel commandModel, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            EditSuperPersonViewModel viewModel = superPersonWebService.getEditSuperPersonViewMode(commandModel.getId());
            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);
            return "super/edit";
        }
        superPersonWebService.saveEditSuperPersonCommandModel(commandModel);
        return "redirect:/super/profile?id=" + commandModel.getId();
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        CreateSuperPersonViewModel viewModel = superPersonWebService.getCreateSuperPersonViewModel();
        model.addAttribute("viewModel", viewModel);
        model.addAttribute("commandModel", viewModel.getCommandModel());
        return "super/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveCreate(@Valid @ModelAttribute("commandModel") CreateSuperPersonCommandModel commandModel, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            CreateSuperPersonViewModel viewModel = superPersonWebService.getCreateSuperPersonViewModel();
            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);
            return "super/create";
        }
        Super superPerson = superPersonWebService.saveCreateSuperPersonCommandModel(commandModel);
        return "redirect:/super/profile?id=" + superPerson.getId();
    }
}