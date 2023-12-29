package org.qr.controller;

import java.util.logging.Level;
import org.qr.models.BaseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler({ Exception.class })
    public final ResponseEntity<BaseStatus> handleGlobalException(Exception ex) {
        logger.log(Level.INFO, "Global exception {0}", ex.toString());
        BaseStatus baseStatus = new BaseStatus().setCode("5000").setDescription("Something went wrong: " + ex.toString());
        return new ResponseEntity<>(baseStatus, HttpStatus.ACCEPTED);
    }
}
