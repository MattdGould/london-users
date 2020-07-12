package com.dwp.users.dwplondonusers.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandlerAdvice.class);

    @ExceptionHandler({RuntimeException.class})
    public String handleRunTimeException(RuntimeException e) {
        LOGGER.error("RuntimeException caught. Error is : ", e);
        return "/error/error";
    }

}
