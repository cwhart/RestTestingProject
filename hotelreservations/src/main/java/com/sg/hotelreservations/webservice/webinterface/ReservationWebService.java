package com.sg.hotelreservations.webservice.webinterface;

import com.sg.hotelreservations.dto.Reservation;
import com.sg.hotelreservations.viewmodels.reservation.*;
import com.sg.hotelreservations.webservice.exception.InvalidDatesException;
import com.sg.hotelreservations.webservice.exception.InvalidPromoException;

import java.util.List;

public interface ReservationWebService {

    public ReservationModel saveCreate  (ReservationModel reservationModel)
            throws InvalidDatesException, InvalidPromoException;
    public SearchAvailableRoomsViewModel getSearchAvailableRoomsViewModel();
    public InputPersonDetailsViewModel getInputPersonDetailsViewModel();
    //public ProfileReservationViewModelDEPRECATED getReservationProfileViewModel(Long id);
    public ReservationModel getReservationModel(Long id);
    public SearchReservationCommandModel getSearchReservationCommandModel();
    public void cancelReservation(Long reservationId);
    EditReservationViewModel getEditReservationViewModel(Long id);
    public ReservationModel saveEditReservation(ReservationModel reservationModel) throws InvalidPromoException;
    public List<Reservation> retrieveAllReservations();

}
