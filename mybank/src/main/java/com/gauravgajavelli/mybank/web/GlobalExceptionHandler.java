package com.gauravgajavelli.mybank.web;

import com.gauravgajavelli.mybank.model.Transaction;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HandleValidationException handlemethodArgumentNotValid(MethodArgumentNotValidException exception) { //
        return new HandleValidationException(exception);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public HandleConstraintViolation handleConstraintViolation(ConstraintViolationException exception) { //
        return new HandleConstraintViolation(exception);
    }

    private class HandleValidationException {
        public String screwup;
        public HandleValidationException(Exception e) {
            this.screwup = "Sorry, that was not quite right: " + e.getMessage();
        }
    }
    private class HandleConstraintViolation {
        public String screwup;
        public HandleConstraintViolation(Exception e) {
            this.screwup = "Sorry, that was not quite right: " + e.getMessage();
        }
    }
}