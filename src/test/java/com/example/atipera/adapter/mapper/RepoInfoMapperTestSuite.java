package com.example.atipera.adapter.mapper;

import com.example.atipera.dto.BranchDto;
import com.example.atipera.dto.CommitDto;
import com.example.atipera.dto.OwnerDto;
import com.example.atipera.dto.RepoInfoDto;
import com.example.atipera.model.Branch;
import com.example.atipera.model.Commit;
import com.example.atipera.model.Owner;
import com.example.atipera.model.RepoInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RepoInfoMapperTestSuite {
    @Autowired
    private RepoInfoMapper repoInfoMapper;

    @Test
    @DisplayName("RepoInfoMapper - fromDtoList")
    void mapToRepoInfoListFromDto() {
        //given
        List<RepoInfoDto> repoInfoDtoList = getRepoInfoDtoList();
        //when
        List<RepoInfo> repoInfoList = repoInfoMapper.fromDtoList(repoInfoDtoList);
        //then
        Assertions.assertNotNull(repoInfoDtoList);
        Assertions.assertEquals(3, repoInfoList.size());

        //should be 0, not 3, even we put some branches into repoInfoDtoList
        //because we collect branches later
        Assertions.assertEquals(0, repoInfoList.stream().findFirst().get().getBranch().size());

        Assertions.assertTrue(repoInfoList.stream().anyMatch(repo -> repo.getName().equals("repoInfoName0")));
        Assertions.assertTrue(repoInfoList.stream().anyMatch(repo -> repo.getOwner().getLogin().equals("login0")));
    }

    @Test
    @DisplayName("RepoInfoMapper - toDtoList")
    void mapToRepoInfoDto() {
        //given
        List<RepoInfo> repoInfoList = getRepoInfoList();
        //when
        List<RepoInfoDto> repoInfoDtoList = repoInfoMapper.toDtoList(repoInfoList);
        //then
        Assertions.assertNotNull(repoInfoDtoList);
        Assertions.assertEquals(3, repoInfoDtoList.size());
        Assertions.assertEquals(3, repoInfoDtoList.stream().findFirst().get().getBranch().size());
        Assertions.assertTrue(repoInfoDtoList.stream().findFirst().get().getBranch().stream().anyMatch
                (branch -> branch.getCommit().getSha().contains("testSha")));
        Assertions.assertTrue(repoInfoDtoList.stream().anyMatch(repo -> repo.getName().equals("repoInfoName0")));
        Assertions.assertTrue(repoInfoDtoList.stream().anyMatch(repo -> repo.getOwner().getLogin().equals("login0")));
    }

    private List<RepoInfo> getRepoInfoList() {
        List<RepoInfo> repos = new ArrayList<>();
        List<Branch> branches = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            branches.add(Branch.builder()
                    .name("test" + i)
                    .commit(Commit.builder()
                            .sha("testSha" + i).build()).build());
        }
        for (int i = 0; i < 3; i++) {
            repos.add(RepoInfo.builder()
                    .name("repoInfoName" + i)
                    .owner(Owner.builder()
                            .login("login" + i).build())
                    .branch(branches).build());
        }
        return repos;
    }

    private List<RepoInfoDto> getRepoInfoDtoList() {
        List<RepoInfoDto> repos = new ArrayList<>();
        List<BranchDto> branches = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            branches.add(new BranchDto("test" + i, new CommitDto("testSha" + i)));
        }
        for (int i = 0; i < 3; i++) {
            repos.add(new RepoInfoDto("repoInfoName" + i, new OwnerDto("login" + i), branches));
        }
        return repos;
    }
}
