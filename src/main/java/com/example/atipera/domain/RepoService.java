package com.example.atipera.domain;

import com.example.atipera.dto.RepoInfoDto;
import com.example.atipera.model.RepoInfo;
import com.example.atipera.web.GithubClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepoService {
    private final GithubClient githubClient;
    private final RepoInfoMapper repoInfoMapper;
    private final BranchMapper branchMapper;

    public List<RepoInfoDto> getRepoInfoByName(final String username) {
        List<RepoInfo> repoInfoList = repoInfoMapper.fromDtoList(githubClient.getRepoNames(username));
        repoInfoList.forEach(
                repo -> repo.setBranch(
                        branchMapper.fromDtoList
                                (githubClient.getBranchByUserNameAndRepoName(username, repo.getName())))
        );
        return repoInfoMapper.toDtoList(repoInfoList);
    }
}
