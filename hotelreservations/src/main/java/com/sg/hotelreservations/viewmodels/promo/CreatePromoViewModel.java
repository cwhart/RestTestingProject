package com.sg.hotelreservations.viewmodels.promo;

import com.sg.hotelreservations.viewmodels.DropdownViewModel;

import java.util.List;

public class CreatePromoViewModel {

    private List<DropdownViewModel> promoTypes;
    private List<DropdownViewModel> promoRates;
    private CreatePromoCommandModel commandModel;

    public List<DropdownViewModel> getPromoTypes() {
        return promoTypes;
    }

    public void setPromoTypes(List<DropdownViewModel> promoTypes) {
        this.promoTypes = promoTypes;
    }

    public List<DropdownViewModel> getPromoRates() {
        return promoRates;
    }

    public void setPromoRates(List<DropdownViewModel> promoRates) {
        this.promoRates = promoRates;
    }

    public CreatePromoCommandModel getCommandModel() {
        return commandModel;
    }

    public void setCommandModel(CreatePromoCommandModel commandModel) {
        this.commandModel = commandModel;
    }
}
