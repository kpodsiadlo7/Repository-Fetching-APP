package com.example.atipera.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> handleFeignExceptions(FeignException ex) {
        if (ex.status() == 404) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ex.status(), ex.getMessage()));
        } else {
            return ResponseEntity.status(ex.status()).body(new ErrorResponse(ex.status(), ex.getMessage()));
        }
    }
}