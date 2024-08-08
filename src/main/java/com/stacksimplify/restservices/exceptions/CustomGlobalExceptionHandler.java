package com.stacksimplify.restservices.exceptions;

import java.time.LocalDateTime;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


// Any exception not handling through Global ExceptionHandler class
@ControllerAdvice // Since we are returning ResponseEntity object from all the methods in this class dont have to use @ResponseBody above these methods. If it was any objects other than ResponseEntity we have to use @ResponseBody above that function in order to return contents of that object to client.
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler{

    // MethodArgumentNotValid - Exception printed in the console when validation using javax.validations fails. If we havn't handled exception, the response statsus code will be 500.
    // Since we have applied @Size(min = 2...) for firstName and @NotEmpty(message = ....) for userName of user entity, any of the condition failed means MethodArgumentNotValidException will be thrown.
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(),
                "From method Argument not valid exception in GEH", ex.getMessage());
        return new ResponseEntity<Object>(customErrorDetails, HttpStatus.BAD_REQUEST);
    }

    // HttpRequestMethodNotSupportedException - Wrong HTTP Method selected while sending request. If we havn't handled exception error the response statsus code will be 500.
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(),
                "From HttpRequestMethodNotSupported in GEH", ex.getMessage());
        return new ResponseEntity<Object>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(UserNameNotFoundException.class)
    public ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex, WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<Object>(customErrorDetails, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
            WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(true));
        return new ResponseEntity<Object>(customErrorDetails, HttpStatus.BAD_REQUEST);
    }
}
