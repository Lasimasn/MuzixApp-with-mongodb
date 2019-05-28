package com.stackroute.muzix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TrackNotFoundException.class)
    public ResponseEntity<?> handleTrackNotFoundException(TrackNotFoundException ex)
    {
        return new ResponseEntity<>("Track Not Found...",HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TrackAlreadyExistException.class)
    public ResponseEntity<?> handleTrackExistsException(TrackAlreadyExistException ex)
    {
        return new ResponseEntity<>("Track Already Exists",HttpStatus.ALREADY_REPORTED);
    }

}
