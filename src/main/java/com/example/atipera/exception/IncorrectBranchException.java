package com.example.atipera.exception;

import com.example.atipera.exception.enumes.ErrorState;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IncorrectBranchException extends RuntimeException {
    private ErrorState errorState;
}
