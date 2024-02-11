package com.example.atipera.exception;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> handleFeignExceptions(FeignException exception) {
        if (exception.status() == 404) {
            log.error(Arrays.toString(exception.getStackTrace()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(exception.status(), exception.getMessage()));
        } else {
            log.error(Arrays.toString(exception.getStackTrace()));
            return ResponseEntity.status(exception.status()).body(new ErrorResponse(exception.status(), exception.getMessage()));
        }
    }

    @ExceptionHandler(IncorrectRepoInfoException.class)
    public ResponseEntity<ErrorResponse> handleIncorrectRepoInfoDtoException(IncorrectRepoInfoException exception) {
        log.error(Arrays.toString(exception.getStackTrace()));
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(HttpStatus.CONFLICT.value(), exception.getErrorState().getMessage()));
    }

    @ExceptionHandler(IncorrectBranchException.class)
    public ResponseEntity<ErrorResponse> handleIncorrectBranchDtoException(IncorrectBranchException exception) {
        log.error(Arrays.toString(exception.getStackTrace()));
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(HttpStatus.CONFLICT.value(), exception.getErrorState().getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        log.error(Arrays.toString(exception.getStackTrace()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
    }
}