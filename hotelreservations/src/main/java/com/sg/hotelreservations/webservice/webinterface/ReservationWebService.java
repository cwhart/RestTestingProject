package com.sg.hotelreservations.webservice.webinterface;

import com.sg.hotelreservations.dto.Reservation;
import com.sg.hotelreservations.viewmodels.reservation.*;
import com.sg.hotelreservations.webservice.exception.InvalidDatesException;
import com.sg.hotelreservations.webservice.exception.InvalidPromoException;

public interface ReservationWebService {

    public Reservation saveCreate  (SaveReservationCommandModel saveReservationCommandModel)
            throws InvalidDatesException, InvalidPromoException;
    public SearchAvailableRoomsViewModel getSearchAvailableRoomsViewModel();
    public InputPersonDetailsViewModel getInputPersonDetailsViewModel();
    public ProfileReservationViewModel getReservationProfileViewModel(Long id);
    public SearchReservationCommandModel getSearchReservationCommandModel();
    public void cancelReservation(Long reservationId);
    EditReservationViewModel getEditReservationViewModel(Long id);
    public Reservation saveEditReservationCommandModel(EditReservationCommandModel commandModel) throws InvalidPromoException;
}
