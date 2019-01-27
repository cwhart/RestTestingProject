package com.sg.hotelreservations.viewmodels.reservation;

public class EditReservationViewModel {

    EditReservationCommandModel commandModel;
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EditReservationCommandModel getCommandModel() {
        return commandModel;
    }

    public void setCommandModel(EditReservationCommandModel commandModel) {
        this.commandModel = commandModel;
    }
}

