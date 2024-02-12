package com.example.atipera.domain;

import com.example.atipera.exception.IncorrectRepoInfoException;
import com.example.atipera.exception.enumes.ErrorState;
import com.example.atipera.model.RecordRepoInfo;
import com.example.atipera.model.RecordRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepoService {
    private final Provider provider;

    public List<RecordRepoInfo> getRepoInfoByName(final String username) {
        validateUsername(username);

        // getting repository - without branches
        List<RecordRepositories> repoInfoList = getUserRepo(username);

        // collecting all branches based on username and repository name
        return populateRepoInfoListWithBranches(username, repoInfoList);
    }

    private static void validateUsername(String username) {
        if (username == null || username.isEmpty())
            throw new IllegalArgumentException("username must be not null and empty");
    }

    private List<RecordRepositories> getUserRepo(final String username) {
        List<RecordRepositories> repoInfoList = provider.getRepoByUsername(username);
        if (repoInfoList == null || repoInfoList.isEmpty())
            throw new IncorrectRepoInfoException(ErrorState.INTERNAL_SERVER_ERROR);
        return repoInfoList;
    }

    private List<RecordRepoInfo> populateRepoInfoListWithBranches(final String username, List<RecordRepositories> repoInfoList) {
        return repoInfoList.stream().peek(repo -> {
                    if (repo.name() == null) throw new IncorrectRepoInfoException(ErrorState.INTERNAL_SERVER_ERROR);
                }).map(repo -> new RecordRepoInfo(repo.name(), repo.owner(), provider.getBranchByUserNameAndRepoName(username, repo.name())))
                .toList();
    }
}
