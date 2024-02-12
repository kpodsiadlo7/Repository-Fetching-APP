package com.example.atipera.adapter.web;

import com.example.atipera.domain.RepoService;
import com.example.atipera.model.RecordRepoInfo;
import com.example.atipera.model.RecordRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final RepoService repoService;

    @GetMapping("/repositories")
    ResponseEntity<List<RecordRepoInfo>> getAllReposByUsername(@RequestParam String username) {
        return ResponseEntity.ok(repoService.getRepoInfoByName(username));
    }
}
