package com.example.atipera.domain;

import com.example.atipera.dto.CommitDto;
import com.example.atipera.model.Commit;
import org.springframework.stereotype.Service;

@Service
class CommitMapper {
    Commit fromDto(final CommitDto commitDto) {
        return Commit.builder()
                .sha(commitDto.getSha()).build();
    }

    CommitDto toDto(final Commit commit) {
        return new CommitDto(
                commit.getSha()
        );
    }
}
