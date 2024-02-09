package com.example.atipera.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class Commit {
    private String sha;
}
