package com.sg.hotelreservations.webservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RoomNotFoundException extends Exception {

    public RoomNotFoundException(String message) {

        super(message);

    }
}
