package com.stackroute.muzix.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

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



//    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
//
//    @ExceptionHandler(TrackAlreadyExistException.class)
//    public String handleTrackAlreadyExistsException(HttpServletRequest request, Exception ex){
//        logger.info("TrackAlreadyExistsException Occured: URL="+request.getRequestURL());
//        return "database_error";
//    }
//
//    @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="IOException occured")
//    @ExceptionHandler(TrackNotFoundException.class)
//    public void handleTrackNotFoundException(){
//        logger.error("IOException handler executed");
//        //returning 404 error code
//    }


}
