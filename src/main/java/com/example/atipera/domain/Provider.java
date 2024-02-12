package com.example.atipera.domain;

import com.example.atipera.exception.IncorrectBranchException;
import com.example.atipera.model.RecordBranch;
import com.example.atipera.model.RecordRepositories;

import java.util.List;

public interface Provider {
    List<RecordRepositories> getRepoByUsername(final String username);

    List<RecordBranch> getBranchByUserNameAndRepoName(final String username, final String repoName) throws IncorrectBranchException;
}
