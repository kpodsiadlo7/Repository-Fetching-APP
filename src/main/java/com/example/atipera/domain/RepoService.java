package com.example.atipera.domain;

import com.example.atipera.exception.ErrorState;
import com.example.atipera.exception.IncorrectRepoInfoException;
import com.example.atipera.model.RepoInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepoService {
    private final Provider provider;

    public List<RepoInfo> getRepoInfoByName(final String username) {
        if (username == null || username.isEmpty())
            throw new IllegalArgumentException("username must be not null and empty");

        // getting repository - without branches
        List<RepoInfo> repoInfoList = provider.getRepoByUsername(username);
        if (repoInfoList == null || repoInfoList.isEmpty()) throw new IncorrectRepoInfoException(ErrorState.INTERNAL_SERVER_ERROR);

        for (var repo : repoInfoList) {
            if (repo.getName() == null) throw new IncorrectRepoInfoException(ErrorState.INTERNAL_SERVER_ERROR);

            // collecting all branches based on username and repository name, then setting them into RepoInfo
            repo.setBranch(provider.getBranchByUserNameAndRepoName(username, repo.getName()));
        }
        return repoInfoList;
    }
}
