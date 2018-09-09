package com.sg.hotelreservations.webservice.webinterface;

import com.sg.hotelreservations.dto.Reservation;
import com.sg.hotelreservations.viewmodels.reservationroom.search.InputReservationDatesCommandModel;
import com.sg.hotelreservations.viewmodels.reservation.list.ListReservationViewModel;
import com.sg.hotelreservations.viewmodels.reservationroom.search.InputReservationDatesViewModel;

public interface ReservationWebService {

    public ListReservationViewModel getReservationListViewModel(Integer offset);
    public InputReservationDatesViewModel getReservationDatesViewModel();
    public Reservation searchInputDatesCommandModel(InputReservationDatesCommandModel var1);

    //    public ProfileReservationViewModel getReservationProfileViewModel(Long id); //takes in ID since that's what is sent to the
//    //page via URL so it can load.
//
//    //These two are input/output for search room
//    public CreateReservationViewModel getCreateReservationViewModel();
//    public Reservation saveCreateReservationCommandModel(CreateReservationCommandModel commandModel);
//
//    //do not re-use search for edit. Make models for edit. Same pattern.
//    public EditReservationViewModel getEditReservationViewModel(Long id);
//    public void saveEditReservationCommandModel(EditReservationCommandModel commandModel);
//
    public void deleteReservation(Long id);
}
