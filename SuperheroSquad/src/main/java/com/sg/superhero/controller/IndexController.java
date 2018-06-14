package com.sg.superhero.controller;

import com.sg.superhero.viewmodels.sighting.list.ListSightingViewModel;
import com.sg.superhero.webservice.interfaces.SightingWebService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;

@Controller
public class IndexController {

    private SightingWebService sightingWebService;

    public IndexController(SightingWebService sightingWebService) {
        this.sightingWebService = sightingWebService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(@RequestParam(required = false) Integer offset, Model model) {

        if(offset == null) {
            offset = 0;
        }

        ListSightingViewModel viewModel = sightingWebService.getListSightingViewModel(offset);
        model.addAttribute("viewModel", viewModel);
        return "index";
    }
}
