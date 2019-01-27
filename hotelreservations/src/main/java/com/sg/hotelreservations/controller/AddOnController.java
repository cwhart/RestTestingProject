package com.sg.hotelreservations.controller;

import com.sg.hotelreservations.viewmodels.addon.ListAddOnViewModel;
import com.sg.hotelreservations.webservice.webinterface.AddOnWebService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/addon")
public class AddOnController {

    AddOnWebService addOnWebService;

    public AddOnController (AddOnWebService addOnWebService) {
        this.addOnWebService = addOnWebService;
    }

    @RequestMapping(value = "/listAddOns", method = RequestMethod.GET)
    public String display(@RequestParam(required = false) Integer offset, Long billId, Model model) {

        if(offset == null) {
            offset = 0;
        }

        ListAddOnViewModel addOnViewModel = addOnWebService.getListAddOnViewModel(offset, billId);

        model.addAttribute("viewModel", addOnViewModel);
        model.addAttribute("billId", billId);

        return "addon/listAddOns";

    }

    @RequestMapping(value = "/selectAddOn", method = RequestMethod.GET)
    public String selectAddOn(Long addOnId, Long billId, Model model) {

        addOnWebService.addAddOnToBill(addOnId, billId);

        //return "addon/listAddOns";
        return "redirect:/addon/listAddOns?billId=" + billId;

    }

}