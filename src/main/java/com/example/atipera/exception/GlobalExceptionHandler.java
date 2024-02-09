package com.example.atipera.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignExceptions(FeignException ex) {
        String response = "{\"status\": "+ ex.status() +", \"message\": "+ ex.getMessage() +"}";
        if (ex.status() == 404) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            return ResponseEntity.status(ex.status()).body(response);
        }
    }
}