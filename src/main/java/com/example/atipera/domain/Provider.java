package com.example.atipera.domain;

import com.example.atipera.exception.IncorrectBranchException;
import com.example.atipera.model.Branch;
import com.example.atipera.model.RepoInfo;

import java.util.List;

public interface Provider {
    List<RepoInfo> getRepoByUsername(final String username);

    List<Branch> getBranchByUserNameAndRepoName(final String username, final String repoName) throws IncorrectBranchException;
}
