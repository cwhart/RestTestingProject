package com.sg.hotelreservations.controller;


import com.sg.hotelreservations.viewmodels.bill.BillViewModel;
import com.sg.hotelreservations.webservice.webinterface.BillWebService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping(value = "/bill")
public class BillController {

    BillWebService billWebService;

    public BillController (BillWebService billWebService) {
        this.billWebService = billWebService;
    }

    @GetMapping(value = "/displayBill/{id}")
    public @ResponseBody BillViewModel display(@PathVariable(value = "id", required = true) Long id) {

        BillViewModel billViewModel = billWebService.getBillViewModel(id);

        return billViewModel;

    }

}
