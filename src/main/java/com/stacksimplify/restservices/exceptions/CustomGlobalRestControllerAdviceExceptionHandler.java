package com.stacksimplify.restservices.exceptions;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// @RestControllerAdvice
public class CustomGlobalRestControllerAdviceExceptionHandler {
    
    // @ResponseBody  - we dont have to use this since we are using @RestControllerAdvice annotation here, we may have to use it if we were using @ControllerAdvice instead of @RestControllerAdvice
    @ExceptionHandler(value = UserNameNotFoundException.class)
    public CustomErrorDetails handleUserNameNotFoundException(UserNameNotFoundException e) {    
        return new CustomErrorDetails(LocalDateTime.now(), "From UserNameNotFoundException in CustomGlobalRestControllerAdviceExceptionHandler ", e.getMessage());
    }
}