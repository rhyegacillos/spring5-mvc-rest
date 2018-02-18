package com.springframework.rest.controller.v1;


import com.springframework.rest.service.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(Exception e, WebRequest request) {
        return new ResponseEntity<>("Resource Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
