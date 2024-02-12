package com.example.atipera.adapter.impl;

import com.example.atipera.adapter.web.GithubClient;
import com.example.atipera.domain.Provider;
import com.example.atipera.exception.IncorrectBranchException;
import com.example.atipera.exception.IncorrectRepoInfoException;
import com.example.atipera.exception.enumes.ErrorState;
import com.example.atipera.model.RecordBranch;
import com.example.atipera.model.RecordRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProviderImpl implements Provider {

    private final GithubClient githubClient;

    @Override
    public List<RecordRepositories> getRepoByUsername(final String username) {
        List<RecordRepositories> repos = githubClient.getRepoNames(username);
        if (repos != null && !repos.isEmpty()) {
            for (var repo : repos) {
                if (repo.name() == null || repo.owner() == null)
                    throw new IncorrectRepoInfoException(ErrorState.INVALID_REPOINFO_DTO);
            }
            return repos;
        }
        throw new IncorrectRepoInfoException(ErrorState.INVALID_REPOINFO_DTO);
    }

    @Override
    public List<RecordBranch> getBranchByUserNameAndRepoName(final String username, final String repoName) {
        List<RecordBranch> branches = githubClient.getBranchByUserNameAndRepoName(username, repoName);
        if (branches != null && !branches.isEmpty()) {
            for (var branch : branches) {
                if (branch.name() == null || branch.commit() == null)
                    throw new IncorrectBranchException(ErrorState.INVALID_BRANCH_DTO);
            }
            return branches;
        }
        throw new IncorrectBranchException(ErrorState.INVALID_BRANCH_DTO);
    }
}
