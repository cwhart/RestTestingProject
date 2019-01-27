package com.sg.hotelreservations.webservice.impl;

import com.sg.hotelreservations.dto.Promo;
import com.sg.hotelreservations.dto.PromoRate;
import com.sg.hotelreservations.dto.PromoType;
import com.sg.hotelreservations.service.serviceinterface.PromoRateService;
import com.sg.hotelreservations.service.serviceinterface.PromoService;
import com.sg.hotelreservations.service.serviceinterface.PromoTypeService;
import com.sg.hotelreservations.viewmodels.DropdownViewModel;
import com.sg.hotelreservations.viewmodels.promo.*;
import com.sg.hotelreservations.webservice.webinterface.PromoWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PromoWebServiceImpl implements PromoWebService {

    @Autowired
    PromoService promoService;

    @Autowired
    PromoRateService promoRateService;

    @Autowired
    PromoTypeService promoTypeService;

    @Override
    public CreatePromoViewModel getCreatePromoViewModel() {
        CreatePromoViewModel viewModel = new CreatePromoViewModel();

        List<PromoType> promoTypes = promoTypeService.retrieveAll(Integer.MAX_VALUE,0);
        List<DropdownViewModel> dropdownPromoTypes = new ArrayList<>();
        for(PromoType currentPromoType: promoTypes){
            DropdownViewModel ddi = new DropdownViewModel();
            ddi.setId(currentPromoType.getId());
            String promoTypeNameDesc = currentPromoType.getPromoCode() + " - " + currentPromoType.getDescription();
            ddi.setName(promoTypeNameDesc);
            dropdownPromoTypes.add(ddi);
        }
        viewModel.setPromoTypes(dropdownPromoTypes);

        List<PromoRate> promoRates = promoRateService.retrieveAll(Integer.MAX_VALUE, 0);
        List<DropdownViewModel> dropdownPromoRates = new ArrayList<>();
        for(PromoRate currentPromoRate : promoRates) {
            DropdownViewModel ddi = new DropdownViewModel();
            ddi.setId(currentPromoRate.getId());
            if(currentPromoRate.getType().equals("$")) {
                ddi.setName("$" + currentPromoRate.getRate().toString());
            } else {
                ddi.setName(currentPromoRate.getRate().toString() + "%");
           }
           dropdownPromoRates.add(ddi);
        }
        viewModel.setPromoRates(dropdownPromoRates);

        viewModel.setCommandModel(new CreatePromoCommandModel());

        return viewModel;
    }

    @Override
    public Promo saveCreatePromoCommandModel(CreatePromoCommandModel commandModel) {
        Promo promo = new Promo();
        PromoType promoType = promoTypeService.retrieve(commandModel.getPromoTypeId());
        PromoRate promoRate = promoRateService.retrieve(commandModel.getRateId());
        promoType.setPromoRate(promoRate);
        promo.setPromoType(promoType);
        promo.setStartDate(LocalDate.parse(commandModel.getStartDate()));
        promo.setEndDate(LocalDate.parse(commandModel.getEndDate()));

        return promoService.create(promo);

    }

    @Override
    public List<ListPromoViewModel> getPromoListViewModel() {
        List<Promo> promos = promoService.retrieveAll(Integer.MAX_VALUE, 0);
        List<ListPromoViewModel> listPromoViewModels = new ArrayList<>();

        for (Promo currentPromo: promos) {
            ListPromoViewModel viewModel = new ListPromoViewModel();
            viewModel.setId(currentPromo.getId());
            viewModel.setStartDate(currentPromo.getStartDate().toString());
            viewModel.setEndDate(currentPromo.getEndDate().toString());
            viewModel.setPromoCode(currentPromo.getPromoType().getPromoCode());
            viewModel.setDescription(currentPromo.getPromoType().getDescription());

            PromoRate promoRate = promoRateService.retrieve(currentPromo.getPromoType().getPromoRate().getId());
            if(promoRate.getType().equals("$")) {
                viewModel.setRate("$" + promoRate.getRate().toString());
            } else {
                viewModel.setRate(promoRate.getRate().toString() + "%");
            }

            listPromoViewModels.add(viewModel);
        }

        return listPromoViewModels;
    }

    @Override
    public EditPromoViewModel getEditPromoViewModel(Long id) {
        EditPromoViewModel viewModel = new EditPromoViewModel();
        Promo promo = promoService.retrieve(id);
        List<PromoType> promoTypes = promoTypeService.retrieveAll(Integer.MAX_VALUE,0);
        EditPromoCommandModel commandModel = new EditPromoCommandModel();
        viewModel.setPromoTypes(new ArrayList<DropdownViewModel>());
        for (PromoType currentPromoType :promoTypes) {
            DropdownViewModel ddl = new DropdownViewModel();
            ddl.setId(currentPromoType.getId());
            ddl.setName(currentPromoType.getPromoCode() + " - " + currentPromoType.getDescription());
            viewModel.getPromoTypes().add(ddl);
        }

        List<PromoRate> promoRates = promoRateService.retrieveAll(Integer.MAX_VALUE,0);
        viewModel.setPromoRates(new ArrayList<DropdownViewModel>());
        for (PromoRate currentPromoRate :promoRates) {
            DropdownViewModel ddl = new DropdownViewModel();
            ddl.setId(currentPromoRate.getId());

            if(currentPromoRate.getType().equals("$")) {
                ddl.setName("$" + currentPromoRate.getRate().toString());
            } else {
                ddl.setName(currentPromoRate.getRate().toString() + "%");
            }

            viewModel.getPromoRates().add(ddl);
        }

        commandModel.setStartDate(promo.getStartDate().toString());
        commandModel.setEndDate(promo.getEndDate().toString());
        commandModel.setId(promo.getId());

        PromoRate promoRate = promoRateService.retrieve(promo.getPromoType().getPromoRate().getId());
        PromoType promoType = promoTypeService.retrieve(promo.getPromoType().getId());

        if (promoRate.getType().equals("$")) {
            commandModel.setRate("$" + promoRate.getRate().toString());
        } else {
            commandModel.setRate(promoRate.getRate().toString() + "%");
        }

        commandModel.setRateId(promoRate.getId());
        commandModel.setPromoCodeDescription(promo.getPromoType().getPromoCode() + " - " + promo.getPromoType().getDescription());
        commandModel.setPromoTypeId(promo.getPromoType().getId());
        viewModel.setCommandModel(commandModel);
        return viewModel;

    }

    @Override
    public void saveEditPromoCommandModel(EditPromoCommandModel commandModel) {

        Promo promo = promoService.retrieve(commandModel.getId());
        promo.setStartDate(LocalDate.parse(commandModel.getStartDate()));
        promo.setEndDate(LocalDate.parse(commandModel.getEndDate()));

        PromoType promoType = promoTypeService.retrieve(commandModel.getPromoTypeId());
        promo.setPromoType(promoType);
        promoService.update(promo);

        PromoRate promoRate = promoRateService.retrieve(commandModel.getRateId());
        promoType.setPromoRate(promoRate);
        
        promoTypeService.update(promoType);

    }
}
