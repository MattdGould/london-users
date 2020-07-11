package com.dwp.users.dwplondonusers.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlerAdvice {

/*    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleExceptionException(RuntimeException e) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }*/

    @ExceptionHandler({RuntimeException.class})
    public String handleRunTimeException(RuntimeException e) {
        return "/error/error";
    }

    @ExceptionHandler({Exception.class})
    public String handleExceptionException(RuntimeException e) {
        return "/error";
    }

    private ResponseEntity<String> error(HttpStatus status, Exception e) {
        //log.error("Exception : ", e);
        return ResponseEntity.status(status).body(e.getMessage());
    }

}
