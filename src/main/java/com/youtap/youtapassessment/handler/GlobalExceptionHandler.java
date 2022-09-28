package com.youtap.youtapassessment.handler;

import com.youtap.youtapassessment.domain.response.BaseUserResult;
import com.youtap.youtapassessment.exception.UserUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserUnavailableException.class)
    public ResponseEntity handleUserUnavailableException(UserUnavailableException e) {
        BaseUserResult response = new BaseUserResult("null", "User Not Found", "-1");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }
}