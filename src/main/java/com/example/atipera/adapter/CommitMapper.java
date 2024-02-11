package com.example.atipera.adapter;

import com.example.atipera.dto.CommitDto;
import com.example.atipera.exception.enumes.ErrorState;
import com.example.atipera.exception.IncorrectCommitException;
import com.example.atipera.model.Commit;
import org.springframework.stereotype.Service;

@Service
class CommitMapper {
    Commit fromDto(final CommitDto commitDto) {
        if (commitDto == null || commitDto.getSha() == null) throw new IncorrectCommitException(ErrorState.INVALID_COMMIT_DTO);
        return Commit.builder()
                .sha(commitDto.getSha()).build();
    }

    CommitDto toDto(final Commit commit) {
        return new CommitDto(
                commit.getSha()
        );
    }
}
