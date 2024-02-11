package com.example.atipera.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IncorrectCommitException extends RuntimeException {
    private ErrorState errorState;
}
