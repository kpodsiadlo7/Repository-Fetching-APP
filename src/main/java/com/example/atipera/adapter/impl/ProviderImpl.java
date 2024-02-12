package com.example.atipera.adapter.impl;

import com.example.atipera.domain.Provider;
import com.example.atipera.exception.IncorrectBranchException;
import com.example.atipera.exception.IncorrectRepoInfoException;
import com.example.atipera.exception.enumes.ErrorState;
import com.example.atipera.model.RecordBranch;
import com.example.atipera.model.RecordRepositories;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class ProviderImpl implements Provider {
    //private final GithubClient githubClient;
    private final RestClient restClient;

    ProviderImpl() {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.github.com/").build();
    }

    @Override
    public List<RecordRepositories> getRepoByUsername(final String username) {
        //List<RecordRepositories> repos = githubClient.getRepoNames(username);
        List<RecordRepositories> repos = restClient.get().uri("users/" + username + "/repos").retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

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
        //List<RecordBranch> branches = githubClient.getBranchByUserNameAndRepoName(username, repoName);
        List<RecordBranch> branches = restClient.get().uri("repos/" + username + "/" + repoName + "/branches").retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
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
