package com.example.atipera.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RepoInfoDto {
    private String name;
    private OwnerDto owner;
    private List<BranchDto> branch;
}
