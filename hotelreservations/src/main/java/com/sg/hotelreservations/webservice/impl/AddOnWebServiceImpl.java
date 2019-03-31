package com.sg.hotelreservations.webservice.impl;

import com.sg.hotelreservations.dto.*;
import com.sg.hotelreservations.service.serviceinterface.*;
import com.sg.hotelreservations.util.PagingUtils;
import com.sg.hotelreservations.viewmodels.addon.AddOnViewModel;
import com.sg.hotelreservations.viewmodels.addon.ListAddOnViewModel;
import com.sg.hotelreservations.webservice.webinterface.AddOnWebService;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddOnWebServiceImpl implements AddOnWebService {

    AddOnService addOnService;

    AddOnRateService addOnRateService;

    BillService billService;

    TaxService taxService;

    AddOnBillDetailService addOnBillDetailService;


    public AddOnWebServiceImpl (AddOnService addOnService, AddOnRateService addOnRateService, BillService billService, TaxService taxService,
                         AddOnBillDetailService addOnBillDetailService) {
        this.addOnService = addOnService;
        this.addOnRateService = addOnRateService;
        this.billService = billService;
        this.taxService = taxService;
        this.addOnBillDetailService = addOnBillDetailService;
    }

    @Override
    public ListAddOnViewModel getListAddOnViewModel(Integer offset, Long billId) {
        Integer limit = 10;
        ListAddOnViewModel viewModel = new ListAddOnViewModel();

        int selectedPage = PagingUtils.getSelectedPage(offset, limit);
        int[] pageNumbers = PagingUtils.getPageNumbers(5, selectedPage);

        //Translate from domain to view models
        List<AddOn> addOns = addOnService.retrieveAll(limit, offset);
        List<AddOnViewModel> addOnViewModels = translate(addOns);

        viewModel.setSelectedPage(selectedPage);
        viewModel.setPageNumbers(pageNumbers);
        viewModel.setAddOns(addOnViewModels);
        viewModel.setBillId(billId);

        return viewModel;
    }

    @Override
    public void deleteAddOn(Long id) {

    }

    @Override
    public AddOnBillDetail addAddOnToBill(Long addOnId, Long billId) {

        Bill bill = billService.retrieve(billId);
        LocalDate date = LocalDate.parse(bill.getReservation().getStartDate());
        AddOnRate addOnRate = addOnRateService.retrieveDefaultRate(addOnId);

        if (addOnRateService.retrieveCurrentRate(addOnId, date).getId() != null) {
            addOnRate = addOnRateService.retrieveCurrentRate(addOnId, date);
        }
        //For now, just get the first one.

        Tax tax = new Tax();
        List<Tax> taxList = taxService.retrieveByState("NH");
        if(taxList.size() != 0) {
            tax = taxList.get(0);
        } else
        {
            tax = new Tax();
            tax.setState("NH");
            tax.setRate(BigDecimal.valueOf(0));
            tax.setStartDate(LocalDate.parse("1900-01-01"));
            tax.setEndDate(null);
            tax.setType("NH Default tax");
            taxService.create(tax);
        }

        AddOnBillDetail addOnBillDetail = new AddOnBillDetail();
        addOnBillDetail.setTransactionDate(LocalDate.now());
        addOnBillDetail.setTax(tax);
        addOnBillDetail.setBill(bill);
        //For now, just get the first one.
        addOnBillDetail.setAddOnRate(addOnRate);
        addOnBillDetail.setPrice(addOnRate.getPrice());
        BigDecimal taxRate = tax.getRate();
        BigDecimal taxAmount = taxRate.multiply(BigDecimal.valueOf(.01).multiply(addOnBillDetail.getPrice()));
        addOnBillDetail.setTaxAmount(taxAmount);
        addOnBillDetailService.create(addOnBillDetail);

        return addOnBillDetail;
    }

    private List<AddOnViewModel> translate(List<AddOn> addOns) {

        List<AddOnViewModel> viewModels = new ArrayList<>();

        for (AddOn addOn : addOns) {
            AddOnViewModel viewModel = new AddOnViewModel();
            viewModel.setType(addOn.getType());
            viewModel.setId(addOn.getId());
            List<AddOnRate> addOnRate = addOnRateService.retrieveByAddOnId(addOn.getId());
            viewModel.setRate(addOnRate.get(0).getPrice());
            viewModels.add(viewModel);
        }

        return viewModels;
    }

    }