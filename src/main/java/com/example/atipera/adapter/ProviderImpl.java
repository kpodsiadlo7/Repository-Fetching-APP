package com.example.atipera.adapter;

import com.example.atipera.adapter.web.GithubClient;
import com.example.atipera.domain.Provider;
import com.example.atipera.dto.BranchDto;
import com.example.atipera.dto.RepoInfoDto;
import com.example.atipera.exception.ErrorState;
import com.example.atipera.exception.IncorrectBranchException;
import com.example.atipera.exception.IncorrectRepoInfoException;
import com.example.atipera.model.Branch;
import com.example.atipera.model.RepoInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProviderImpl implements Provider {

    private final GithubClient githubClient;
    private final RepoInfoMapper repoInfoMapper;
    private final BranchMapper branchMapper;

    @Override
    public List<RepoInfo> getRepoByUsername(final String username) {
        List<RepoInfoDto> repos = githubClient.getRepoNames(username);
        if (repos != null && !repos.isEmpty()) {
            for (var repo : repos) {
                if (repo.getName() == null || repo.getOwner() == null)
                    throw new IncorrectRepoInfoException(ErrorState.INVALID_REPOINFO_DTO);
            }
            return repoInfoMapper.fromDtoList(repos);
        }
        throw new IncorrectRepoInfoException(ErrorState.INVALID_REPOINFO_DTO);
    }

    @Override
    public List<Branch> getBranchByUserNameAndRepoName(final String username, final String repoName) {
        List<BranchDto> branchesDto = githubClient.getBranchByUserNameAndRepoName(username, repoName);
        if (branchesDto != null && !branchesDto.isEmpty()) {
            for (var branch : branchesDto) {
                if (branch.getName() == null || branch.getCommit() == null)
                    throw new IncorrectBranchException(ErrorState.INVALID_BRANCH_DTO);
            }
            return branchMapper.fromDtoList(branchesDto);
        }
        throw new IncorrectBranchException(ErrorState.INVALID_BRANCH_DTO);
    }
}
