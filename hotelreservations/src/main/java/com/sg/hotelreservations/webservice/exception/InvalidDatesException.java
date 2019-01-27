package com.sg.hotelreservations.webservice.exception;

import org.springframework.stereotype.Service;

public class InvalidDatesException extends Exception {


    public InvalidDatesException(String message) {


        super(message);

    }


}
