package com.sg.hotelreservations.controller;

import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.viewmodels.addon.AddOnViewModel;
import com.sg.hotelreservations.webservice.webinterface.AddOnWebService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(value = "/addon")
public class AddOnController {

    AddOnWebService addOnWebService;

    public AddOnController (AddOnWebService addOnWebService) {
        this.addOnWebService = addOnWebService;
    }

    @RequestMapping(value = "/listAddOns", method = RequestMethod.GET)
    public @ResponseBody List<AddOnViewModel> display(@RequestParam(required = false) Integer offset) {

        if(offset == null) {
            offset = 0;
        }

        List<AddOnViewModel> addOnViewModels = addOnWebService.getListAddOnViewModel(offset);

        return addOnViewModels;

    }

    @RequestMapping(value = "/selectAddOn/{addOnId}/{billId}", method = RequestMethod.GET)
    public @ResponseBody
    AddOnBillDetail selectAddOn(@PathVariable(value = "addOnId", required = true) Long addOnId,
                                @PathVariable(value = "billId", required = true) Long billId) {

        AddOnBillDetail addOnBillDetail = addOnWebService.addAddOnToBill(addOnId, billId);

        //return "addon/listAddOns";
        return addOnBillDetail;

    }

}