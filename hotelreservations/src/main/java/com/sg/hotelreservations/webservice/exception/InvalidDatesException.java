package com.sg.hotelreservations.webservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class InvalidDatesException extends Exception {

    public InvalidDatesException(String message) {


        super(message);

    }


}
