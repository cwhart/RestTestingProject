package com.sg.hotelreservations.viewmodels.promo;

import com.sg.hotelreservations.viewmodels.DropdownViewModel;

import java.util.List;

public class EditPromoViewModel {

    private List<DropdownViewModel> promoTypes;
    private List<DropdownViewModel> promoRates;
    private EditPromoCommandModel commandModel;

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

    public EditPromoCommandModel getCommandModel() {
        return commandModel;
    }

    public void setCommandModel(EditPromoCommandModel commandModel) {
        this.commandModel = commandModel;
    }
}
