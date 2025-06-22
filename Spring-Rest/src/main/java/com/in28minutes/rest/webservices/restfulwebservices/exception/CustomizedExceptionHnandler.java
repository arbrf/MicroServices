package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.in28minutes.rest.webservices.restfulwebservices.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// Apply this exception handler globally across all controllers
@ControllerAdvice
public class CustomizedExceptionHnandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                 LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


     @ExceptionHandler(UserNotFoundException.class)
     public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
         ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "User Not Found", request.getDescription(false));
         return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
     }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "Total Errors"+ex.getErrorCount()+"First Error"+ex.getFieldError(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
