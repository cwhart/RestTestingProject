package com.sg.superhero.controller;

import com.sg.superhero.dto.Organization;
import com.sg.superhero.viewmodels.organization.create.CreateOrganizationCommandModel;
import com.sg.superhero.viewmodels.organization.create.CreateOrganizationViewModel;
import com.sg.superhero.viewmodels.organization.edit.EditOrganizationCommandModel;
import com.sg.superhero.viewmodels.organization.edit.EditOrganizationViewModel;
import com.sg.superhero.viewmodels.organization.list.ListOrganizationViewModel;
import com.sg.superhero.viewmodels.organization.profile.ProfileOrganizationViewModel;
import com.sg.superhero.webservice.interfaces.OrganizationWebService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/organization")
public class OrganizationController {

    private OrganizationWebService organizationWebService;

    @Inject
    public OrganizationController(OrganizationWebService organizationWebService) {
        this.organizationWebService = organizationWebService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@RequestParam(required = false) Integer offset, Model model) {
        if (offset == null) {
            offset = 0;
        }
        ListOrganizationViewModel viewModel = organizationWebService.getListOrganizationViewModel(offset);

        model.addAttribute("viewModel", viewModel);

        return "organization/list";
    }

    @RequestMapping(value = "/edit")
    public String edit(@RequestParam Long id, Model model) {
        EditOrganizationViewModel viewModel = organizationWebService.getEditOrganizationViewModel(id);

        model.addAttribute("viewModel",viewModel);
        model.addAttribute("commandModel",viewModel.getCommandModel());

        return "organization/edit";
    }

    @RequestMapping(value = "/edit", method= RequestMethod.POST)
    public String saveEdit(@Valid @ModelAttribute("commandModel") EditOrganizationCommandModel commandModel, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            EditOrganizationViewModel viewModel =
                    organizationWebService.getEditOrganizationViewModel(commandModel.getId());

            model.addAttribute("viewModel",viewModel);
            model.addAttribute("commandModel",commandModel);
            return "organization/edit";
        }
        organizationWebService.saveEditOrganizationCommandModel(commandModel);

        return "redirect:/organization/profile?id=" + commandModel.getId();
    }


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create( Model model) {
        CreateOrganizationViewModel viewModel = organizationWebService.getCreateOrganizationViewModel();

        model.addAttribute("viewModel",viewModel);
        model.addAttribute("commandModel",viewModel.getCommandModel());

        return "organization/create";
    }

    @RequestMapping(value = "/create", method= RequestMethod.POST)
    public String saveCreate(@Valid @ModelAttribute("commandModel")
                                     CreateOrganizationCommandModel commandModel,
                             BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            CreateOrganizationViewModel viewModel =
                    organizationWebService.getCreateOrganizationViewModel();

            model.addAttribute("viewModel",viewModel);
            model.addAttribute("commandModel",commandModel);
            return "organization/create";
        }
        Organization organization = organizationWebService.saveCreateOrganizationCommandModel(commandModel);

        return "redirect:/organization/profile?id=" + organization.getId();
    }

    @RequestMapping(value = "/profile")
    public String profile(@RequestParam Long id, Model model) {
        ProfileOrganizationViewModel viewModel = organizationWebService.getOrganizationProfileViewModel(id);

        model.addAttribute("viewModel",viewModel);

        return "organization/profile";
    }
}