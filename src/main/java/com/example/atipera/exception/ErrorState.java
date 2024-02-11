package com.example.atipera.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorState {
    INVALID_BRANCH_DTO("Incorrect branches data"),
    INVALID_REPOINFO_DTO("Incorrect repositories data"),
    INVALID_OWNER_DTO("Incorrect owner data"),
    INVALID_COMMIT_DTO("Incorrect commit data"),
    INTERNAL_SERVER_ERROR("Internal server error");
    private final String message;
}
