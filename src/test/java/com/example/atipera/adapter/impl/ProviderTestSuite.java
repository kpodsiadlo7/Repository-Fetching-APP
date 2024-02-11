package com.example.atipera.adapter.impl;

import com.example.atipera.adapter.mapper.BranchMapper;
import com.example.atipera.adapter.mapper.RepoInfoMapper;
import com.example.atipera.adapter.web.GithubClient;
import com.example.atipera.dto.BranchDto;
import com.example.atipera.dto.CommitDto;
import com.example.atipera.dto.OwnerDto;
import com.example.atipera.dto.RepoInfoDto;
import com.example.atipera.exception.IncorrectBranchException;
import com.example.atipera.exception.IncorrectRepoInfoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProviderTestSuite {

    @Test
    @DisplayName("Provider(getRepoByUsername) - exception when getRepoNames return null or empty array")
    void shouldThrowIncorrectRepoInfoExceptionWhenListIsNullOrEmpty() {
        //given
        var mockGithubClient = mock(GithubClient.class);
        when(mockGithubClient.getRepoNames("empty")).thenReturn(new ArrayList<>());
        when(mockGithubClient.getRepoNames("null")).thenReturn(null);
        //when
        var toTest = new ProviderImpl(mockGithubClient, null, null);
        //then
        Assertions.assertThrows(IncorrectRepoInfoException.class, () -> toTest.getRepoByUsername("empty"));
        Assertions.assertThrows(IncorrectRepoInfoException.class, () -> toTest.getRepoByUsername("null"));
    }

    @Test
    @DisplayName("Provider(getRepoByUsername) - exception when getName or getOwner is null")
    void shouldThrowIncorrectRepoInfoExceptionWhenPutIncorrectData() {
        //given
        RepoInfoDto repoWithNullName = new RepoInfoDto(null, new OwnerDto(), null);
        RepoInfoDto repoWithNullOwner = new RepoInfoDto("name", null, null);
        RepoInfoDto repoWithNullNameAndOwner = new RepoInfoDto(null, null, null);

        var mockGithubClient = mock(GithubClient.class);
        when(mockGithubClient.getRepoNames("repoWithNullName")).thenReturn(List.of(repoWithNullName));
        when(mockGithubClient.getRepoNames("repoWithNullOwner")).thenReturn(List.of(repoWithNullOwner));
        when(mockGithubClient.getRepoNames("repoWithNullNameAndOwner")).thenReturn(List.of(repoWithNullNameAndOwner));
        //when
        var toTest = new ProviderImpl(mockGithubClient, null, null);
        //then
        Assertions.assertThrows(IncorrectRepoInfoException.class, () -> toTest.getRepoByUsername("repoWithNullName"));
        Assertions.assertThrows(IncorrectRepoInfoException.class, () -> toTest.getRepoByUsername("repoWithNullOwner"));
        Assertions.assertThrows(IncorrectRepoInfoException.class, () -> toTest.getRepoByUsername("repoWithNullNameAndOwner"));
    }

    @Test
    @DisplayName("Provider(getRepoByUsername) - should pass when put correct data")
    void shouldPassGetRepoByUsernameWhenPutCorrectData() {
        //given
        RepoInfoDto repoInfoDto = new RepoInfoDto("name", new OwnerDto("login"), null);

        var mockGithubClient = mock(GithubClient.class);
        var mockRepoInfoMapper = mock(RepoInfoMapper.class);
        when(mockGithubClient.getRepoNames(anyString())).thenReturn(List.of(repoInfoDto));
        when(mockRepoInfoMapper.fromDtoList(any())).thenReturn(new ArrayList<>());
        //when
        var toTest = new ProviderImpl(mockGithubClient, mockRepoInfoMapper, null);
        //then
        Assertions.assertEquals(new ArrayList<>(), toTest.getRepoByUsername(anyString()));
    }

    @Test
    @DisplayName("Provider(getBranchByUserNameAndRepoName) - exception when getBranchByUserNameAndRepoName return null or empty array")
    void shouldThrowIncorrectBranchExceptionWhenListIsNullOrEmpty() {
        //given
        var mockGithubClient = mock(GithubClient.class);
        when(mockGithubClient.getBranchByUserNameAndRepoName(eq("empty"),anyString())).thenReturn(new ArrayList<>());
        when(mockGithubClient.getBranchByUserNameAndRepoName(eq("null"),anyString())).thenReturn(null);
        //when
        var toTest = new ProviderImpl(mockGithubClient, null, null);
        //then
        Assertions.assertThrows(IncorrectBranchException.class, () -> toTest.getBranchByUserNameAndRepoName("empty",""));
        Assertions.assertThrows(IncorrectBranchException.class, () -> toTest.getBranchByUserNameAndRepoName("null",""));
    }

    @Test
    @DisplayName("Provider(getBranchByUserNameAndRepoName) - exception when branchDto name or CommitDto is null")
    void shouldThrowIncorrectBranchExceptionWhenPutIncorrectData() {
        //given
        BranchDto branchWithNullName = new BranchDto(null, new CommitDto("sha"));
        BranchDto branchWithNullCommitDto = new BranchDto("name", null);
        BranchDto branchWithNullNameAndNullCommitDto = new BranchDto(null, null);

        var mockGithubClient = mock(GithubClient.class);
        var mockBranchMapper = mock(BranchMapper.class);
        when(mockGithubClient.getBranchByUserNameAndRepoName
                (eq("branchWithNullName"), anyString())).thenReturn(List.of(branchWithNullName));
        when(mockGithubClient.getBranchByUserNameAndRepoName
                (eq("branchWithNullCommitDto"), anyString())).thenReturn(List.of(branchWithNullCommitDto));
        when(mockGithubClient.getBranchByUserNameAndRepoName
                (eq("branchWithNullNameAndNullCommitDto"), anyString())).thenReturn(List.of(branchWithNullNameAndNullCommitDto));

        when(mockBranchMapper.fromDtoList(any())).thenReturn(new ArrayList<>());
        //when
        var toTest = new ProviderImpl(mockGithubClient, null, mockBranchMapper);
        //then
        Assertions.assertThrows(IncorrectBranchException.class, () ->
                toTest.getBranchByUserNameAndRepoName("branchWithNullName", ""));
        Assertions.assertThrows(IncorrectBranchException.class, () ->
                toTest.getBranchByUserNameAndRepoName("branchWithNullCommitDto", ""));
        Assertions.assertThrows(IncorrectBranchException.class, () ->
                toTest.getBranchByUserNameAndRepoName("repoWithNullNameAndOwner", ""));
    }

    @Test
    @DisplayName("Provider(getBranchByUserNameAndRepoName) - should pass when put correct data")
    void shouldPassWhenPutCorrectData() {
        //given
        BranchDto branchDto = new BranchDto("name", new CommitDto("sha"));

        var mockGithubClient = mock(GithubClient.class);
        var mockBranchMapper = mock(BranchMapper.class);
        when(mockGithubClient.getBranchByUserNameAndRepoName(anyString(), anyString())).thenReturn(List.of(branchDto));
        when(mockBranchMapper.fromDtoList(any())).thenReturn(new ArrayList<>());
        //when
        var toTest = new ProviderImpl(mockGithubClient, null, mockBranchMapper);
        //then
        Assertions.assertEquals(new ArrayList<>(), toTest.getBranchByUserNameAndRepoName(anyString(), anyString()));
    }
}
