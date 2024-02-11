package com.example.atipera.adapter;

import com.example.atipera.dto.BranchDto;
import com.example.atipera.dto.CommitDto;
import com.example.atipera.exception.IncorrectCommitException;
import com.example.atipera.model.Branch;
import com.example.atipera.model.Commit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BranchMapperTestSuite {
    @Autowired
    private BranchMapper branchMapper;

    @Test
    @DisplayName("BranchMapper - fromDtoList")
    void mapFromBranchDtoListToBranchList() {
        //given
        List<BranchDto> branchDtoList = getBranchDtoListWithTwoExamples();
        //when
        List<Branch> branchList = branchMapper.fromDtoList(branchDtoList);
        //then
        Assertions.assertNotNull(branchList);
        Assertions.assertEquals(2, branchList.size());
        Assertions.assertTrue(branchList.stream().anyMatch(branch -> branch.getName().equals("testBranchName0")));
        Assertions.assertTrue(branchList.stream().anyMatch(branch -> branch.getName().equals("testBranchName1")));
        Assertions.assertTrue(branchList.stream().allMatch(branch -> branch.getCommit() != null));
        Assertions.assertTrue(branchList.stream().anyMatch(branch -> branch.getCommit().getSha().equals("testSha0")));
        Assertions.assertTrue(branchList.stream().anyMatch(branch -> branch.getCommit().getSha().equals("testSha1")));
    }

    @Test
    @DisplayName("BranchMapper - fromDtoList with null as Commit")
    void shouldThrowExceptionWhenMapFromBranchDtoList() {
        //given
        List<BranchDto> branchDtoList = new ArrayList<>();
        BranchDto branchDto = new BranchDto("exception", null);
        branchDtoList.add(branchDto);
        //when && then
        Assertions.assertThrows(IncorrectCommitException.class, () -> branchMapper.fromDtoList(branchDtoList));
    }

    @Test
    @DisplayName("BranchMapper - toDtoList")
    void mapToBranchDtoListFromBranchList() {
        //given
        List<Branch> branchList = getBranchListWithTwoExamples();
        //when
        List<BranchDto> branchDtoList = branchMapper.toDtoList(branchList);
        //then
        Assertions.assertNotNull(branchDtoList);
        Assertions.assertEquals(2, branchDtoList.size());
        Assertions.assertTrue(branchDtoList.stream().anyMatch(branch -> branch.getName().equals("testBranchName0")));
        Assertions.assertTrue(branchDtoList.stream().anyMatch(branch -> branch.getName().equals("testBranchName1")));
        Assertions.assertTrue(branchDtoList.stream().allMatch(branch -> branch.getCommit() != null));
        Assertions.assertTrue(branchDtoList.stream().anyMatch(branch -> branch.getCommit().getSha().equals("testSha0")));
        Assertions.assertTrue(branchDtoList.stream().anyMatch(branch -> branch.getCommit().getSha().equals("testSha1")));
    }

    private List<Branch> getBranchListWithTwoExamples() {
        List<Branch> branchList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Commit commit = Commit.builder().sha("testSha"+i).build();
            Branch branch = Branch.builder().name("testBranchName"+i).commit(commit).build();
            branchList.add(branch);
        }
        return branchList;
    }

    private List<BranchDto> getBranchDtoListWithTwoExamples() {
        List<BranchDto> branchDtoList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            CommitDto commitDto = new CommitDto("testSha" + i);
            BranchDto branchDto = new BranchDto("testBranchName" + i, commitDto);
            branchDtoList.add(branchDto);
        }
        return branchDtoList;
    }
}
