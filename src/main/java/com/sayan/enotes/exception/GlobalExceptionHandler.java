package com.sayan.enotes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DtoValidationException.class)
    public ResponseEntity<?> handleValidationException(DtoValidationException e) {
        return new ResponseEntity<>(e.getMapError(), HttpStatus.BAD_REQUEST);
    }
}
