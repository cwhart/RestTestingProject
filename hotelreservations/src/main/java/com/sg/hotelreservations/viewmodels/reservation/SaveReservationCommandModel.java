package com.sg.hotelreservations.viewmodels.reservation;

import java.util.List;

public class SaveReservationCommandModel {

    List<InputPersonDetailsViewModel> personDetailsViewModels;
    SearchAvailableRoomsViewModel roomsViewModel;
    String promoCode;

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public List<InputPersonDetailsViewModel> getPersonDetailsViewModels() {
        return personDetailsViewModels;
    }

    public void setPersonDetailsViewModels(List<InputPersonDetailsViewModel> personDetailsViewModels) {
        this.personDetailsViewModels = personDetailsViewModels;
    }

    public SearchAvailableRoomsViewModel getRoomsViewModel() {
        return roomsViewModel;
    }

    public void setRoomsViewModel(SearchAvailableRoomsViewModel roomsViewModel) {
        this.roomsViewModel = roomsViewModel;
    }
}
