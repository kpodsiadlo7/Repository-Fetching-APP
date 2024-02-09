package com.example.atipera.web;

import com.example.atipera.dto.BranchDto;
import com.example.atipera.dto.RepoInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "Github", url = "${github.url}")
public interface GithubClient {
    @GetMapping("users/{username}/repos")
    List<RepoInfoDto> getRepoNames(@PathVariable final String username);

    @GetMapping("repos/{userName}/{repoName}/branches")
    List<BranchDto> getBranchByUserNameAndRepoName(
            @PathVariable final String userName,
            @PathVariable final String repoName);
}
