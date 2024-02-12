package com.example.atipera.adapter.web;

import com.example.atipera.model.RecordBranch;
import com.example.atipera.model.RecordRepositories;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "Github", url = "${github.url}")
public interface GithubClient {
    @GetMapping("users/{username}/repos")
    List<RecordRepositories> getRepoNames(@PathVariable final String username);

    @GetMapping("repos/{userName}/{repoName}/branches")
    List<RecordBranch> getBranchByUserNameAndRepoName(
            @PathVariable final String userName,
            @PathVariable final String repoName);
}
