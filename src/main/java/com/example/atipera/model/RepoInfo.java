package com.example.atipera.model;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepoInfo {
    private String name;
    private Owner owner;
    private List<Branch> branch;
}
