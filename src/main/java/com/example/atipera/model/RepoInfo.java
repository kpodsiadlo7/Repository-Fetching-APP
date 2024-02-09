package com.example.atipera.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class RepoInfo {
    private String name;
    private Owner owner;
    private List<Branch> branch;
}
