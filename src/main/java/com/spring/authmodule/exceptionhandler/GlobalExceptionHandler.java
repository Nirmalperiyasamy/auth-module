package com.spring.authmodule.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException err) {
        List<String> errorMessages = err.getBindingResult().
                getFieldErrors().
                stream().
                map(error -> error.getDefaultMessage()).
                collect(Collectors.toList());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Validation failed", errorMessages);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserAlreadyExist.class)
    public ResponseEntity<?> exception(UserAlreadyExist exception) {
        return ResponseEntity.badRequest().body("user already exist");
    }

    @ExceptionHandler(value = WrongPassword.class)
    public ResponseEntity<?> exception(WrongPassword exception) {
        return ResponseEntity.badRequest().body("Wrong password");
    }

    @ExceptionHandler(value = UserNotRegistered.class)
    public ResponseEntity<?> exception(UserNotRegistered exception) {
        return ResponseEntity.badRequest().body("user not registered");
    }
}
