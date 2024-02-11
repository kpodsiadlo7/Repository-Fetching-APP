package com.example.atipera.adapter.mapper;

import com.example.atipera.adapter.mapper.CommitMapper;
import com.example.atipera.dto.CommitDto;
import com.example.atipera.exception.IncorrectCommitException;
import com.example.atipera.model.Commit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommitMapperTestSuite {

    @Autowired
    private CommitMapper commitMapper;

    @Test
    @DisplayName("CommitMapper - fromDto")
    void mapToCommitFromCommitDto() {
        //given
        CommitDto commitDto = new CommitDto("testSha");
        //when
        Commit commit = commitMapper.fromDto(commitDto);
        //then
        Assertions.assertNotNull(commit);
        Assertions.assertEquals("testSha", commit.getSha());
    }

    @Test
    @DisplayName("CommitMapper - toDto")
    void mapToCommitDtoFromCommit() {
        //given
        Commit commit = Commit.builder().sha("testSha").build();
        //when
        CommitDto commitDto = commitMapper.toDto(commit);
        //then
        Assertions.assertNotNull(commitDto);
        Assertions.assertEquals("testSha", commitDto.getSha());
    }

    @Test
    @DisplayName("CommitMapper - exception")
    void shouldThrowExceptionWhenCommitIsNull(){
        //when & then
        Assertions.assertThrows(IncorrectCommitException.class, () -> commitMapper.fromDto(null));
    }
}
