package com.sg.hotelreservations.controller;


import com.sg.hotelreservations.viewmodels.bill.BillViewModel;
import com.sg.hotelreservations.webservice.webinterface.BillWebService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/bill")
public class BillController {

    BillWebService billWebService;

    public BillController (BillWebService billWebService) {
        this.billWebService = billWebService;
    }

    @RequestMapping(value = "/displayBill", method = RequestMethod.GET)
    public String display(@RequestParam(required = true) Long billId, Model model) {

        BillViewModel billViewModel = billWebService.getBillViewModel(billId);

        model.addAttribute("viewModel", billViewModel);
        model.addAttribute("roomViewModels", billViewModel.getRoomBillDetailViewModels());
        model.addAttribute("addOnViewModels", billViewModel.getAddOnBillDetailViewModels());

        return "bill/list";

    }

}
