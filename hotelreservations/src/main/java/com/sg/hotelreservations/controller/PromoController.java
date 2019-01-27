package com.sg.hotelreservations.controller;

import com.sg.hotelreservations.dto.Promo;
import com.sg.hotelreservations.viewmodels.promo.*;
import com.sg.hotelreservations.webservice.webinterface.PromoWebService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/promo")
public class PromoController {

    PromoWebService promoWebService;

    public PromoController (PromoWebService promoWebService) {
        this.promoWebService = promoWebService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String display( Model model, String message) {

        List<ListPromoViewModel> listPromoViewModels = promoWebService.getPromoListViewModel();

        model.addAttribute("viewModel", listPromoViewModels);

        return "promo/list";

    }

    @RequestMapping(value= "/create", method = RequestMethod.GET)
    public String create(Model model){

        CreatePromoViewModel viewModel = promoWebService.getCreatePromoViewModel();
        model.addAttribute("viewModel", viewModel);
        model.addAttribute("commandModel", viewModel.getCommandModel());

        return "promo/create";
    }

    @RequestMapping(value = "/create", method= RequestMethod.POST)
    public String saveCreate(@Valid @ModelAttribute("commandModel") CreatePromoCommandModel commandModel, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            CreatePromoViewModel viewModel = promoWebService.getCreatePromoViewModel();

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);
            return "promo/create";
        }
        Promo promo = promoWebService.saveCreatePromoCommandModel(commandModel);

        return "redirect:/promo/list";
    }

    @RequestMapping (value= "/edit")
    public String edit(@RequestParam Long id, Model model){

        EditPromoViewModel viewModel = promoWebService.getEditPromoViewModel(id);
        model.addAttribute("viewModel", viewModel);
        model.addAttribute("commandModel", viewModel.getCommandModel());

        return "promo/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEdit(@Valid @ModelAttribute("commandModel") EditPromoCommandModel commandModel, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            //EditPromoCommandModel commandModel1 = promoWebService.getEditPromoCommandModel(commandModel.getId());
            //model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);
            return "promo/edit";
        }
        promoWebService.saveEditPromoCommandModel(commandModel);
        return "redirect:/promo/list";
    }

}