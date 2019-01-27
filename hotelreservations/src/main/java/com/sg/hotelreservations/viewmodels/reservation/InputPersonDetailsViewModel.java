package com.sg.hotelreservations.viewmodels.reservation;

public class InputPersonDetailsViewModel {

    InputPersonDetailsCommandModel commandModel;
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public InputPersonDetailsCommandModel getCommandModel() {
        return commandModel;
    }

    public void setCommandModel(InputPersonDetailsCommandModel commandModel) {
        this.commandModel = commandModel;
    }
}

