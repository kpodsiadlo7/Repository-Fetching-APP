package com.example.atipera.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepoInfoDto {
    private String name;
    private OwnerDto owner;
    private List<BranchDto> branch;
}
