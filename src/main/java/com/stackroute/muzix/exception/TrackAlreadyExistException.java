package com.stackroute.muzix.exception;

public class TrackAlreadyExistException extends  Exception{

    private String message;
    public TrackAlreadyExistException(){}
    public TrackAlreadyExistException(String message) {
        super(message);
        this.message = message;
    }
}
