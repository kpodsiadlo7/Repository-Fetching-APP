package com.example.atipera.domain;

import com.example.atipera.exception.IncorrectRepoInfoException;
import com.example.atipera.exception.enumes.ErrorState;
import com.example.atipera.model.RepoInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepoService {
    private final Provider provider;

    public List<RepoInfo> getRepoInfoByName(final String username) {
        validateUsername(username);

        // getting repository - without branches
        List<RepoInfo> repoInfoList = getUserRepo(username);

        // collecting all branches based on username and repository name
        populateRepoInfoListWithBranches(username, repoInfoList);

        return repoInfoList;
    }

    private static void validateUsername(String username) {
        if (username == null || username.isEmpty())
            throw new IllegalArgumentException("username must be not null and empty");
    }

    private List<RepoInfo> getUserRepo(final String username) {
        List<RepoInfo> repoInfoList = provider.getRepoByUsername(username);
        if (repoInfoList == null || repoInfoList.isEmpty())
            throw new IncorrectRepoInfoException(ErrorState.INTERNAL_SERVER_ERROR);
        return repoInfoList;
    }

    private void populateRepoInfoListWithBranches(final String username, List<RepoInfo> repoInfoList) {
        for (var repo : repoInfoList) {
            if (repo.getName() == null) throw new IncorrectRepoInfoException(ErrorState.INTERNAL_SERVER_ERROR);
            repo.setBranch(provider.getBranchByUserNameAndRepoName(username, repo.getName()));
        }
    }
}
