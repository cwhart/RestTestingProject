package com.sg.hotelreservations.viewmodels.reservation.list;

import java.util.List;

public class ListReservationViewModel {

    private int[] pageNumbers;
    private int selectedPage;
    private List<ReservationViewModel> reservations;

    public int[] getPageNumbers() {
        return pageNumbers;
    }

    public void setPageNumbers(int[] pageNumbers) {
        this.pageNumbers = pageNumbers;
    }

    public int getSelectedPage() {
        return selectedPage;
    }

    public void setSelectedPage(int selectedPage) {
        this.selectedPage = selectedPage;
    }

    public List<ReservationViewModel> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationViewModel> reservations) {
        this.reservations = reservations;
    }
}
