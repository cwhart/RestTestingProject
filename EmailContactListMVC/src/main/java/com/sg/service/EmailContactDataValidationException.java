package com.sg.service;

public class EmailContactDataValidationException extends Exception {
    public EmailContactDataValidationException(String message) {
        super(message);
    }

    public EmailContactDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
