package com.example.atipera.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IncorrectRepoInfoException extends RuntimeException {
    private ErrorState errorState;
}
