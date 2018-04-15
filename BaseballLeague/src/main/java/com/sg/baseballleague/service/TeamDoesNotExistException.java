package com.sg.baseballleague.service;

public class TeamDoesNotExistException extends Exception{

    public TeamDoesNotExistException(String message) {

        //..

        super(message);

    }

    public TeamDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
