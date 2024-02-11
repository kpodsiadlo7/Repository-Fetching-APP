package com.example.atipera.adapter.impl;

import com.example.atipera.adapter.mapper.RepoInfoMapper;
import com.example.atipera.adapter.web.GithubClient;
import com.example.atipera.dto.OwnerDto;
import com.example.atipera.dto.RepoInfoDto;
import com.example.atipera.exception.IncorrectRepoInfoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProviderTestSuite {

    @Test
    @DisplayName("Provider - exception when getRepoNames return null or empty array")
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
    @DisplayName("Provider - exception when getName or getOwner is null")
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
    @DisplayName("Provider - should pass when put correct data")
    void shouldPassWhenPutCorrectData() {
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
}
