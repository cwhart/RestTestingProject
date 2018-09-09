package com.sg.hotelreservations.viewmodels.reservationroom.list;

import com.sg.hotelreservations.viewmodels.reservationroom.search.InputReservationDatesCommandModel;

import java.time.LocalDate;
import java.util.List;

public class ListReservationRoomViewModel {

    private int[] pageNumbers;
    private int selectedPage;
    private List<ReservationRoomViewModel> reservationRooms;
    private InputReservationDatesCommandModel inputReservationDatesCommandModel;

    public InputReservationDatesCommandModel getInputReservationDatesCommandModel() {
        return inputReservationDatesCommandModel;
    }

    public void setInputReservationDatesCommandModel(InputReservationDatesCommandModel inputReservationDatesCommandModel) {
        this.inputReservationDatesCommandModel = inputReservationDatesCommandModel;
    }

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

    public List<ReservationRoomViewModel> getReservationRooms() {
        return reservationRooms;
    }

    public void setReservationRooms(List<ReservationRoomViewModel> reservationRooms) {
        this.reservationRooms = reservationRooms;
    }
}
