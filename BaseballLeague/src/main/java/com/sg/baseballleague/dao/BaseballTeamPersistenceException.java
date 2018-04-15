package com.sg.baseballleague.dao;

public class BaseballTeamPersistenceException extends Exception{

    public BaseballTeamPersistenceException (String message) {

        //..

        super(message);

    }

    public BaseballTeamPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
