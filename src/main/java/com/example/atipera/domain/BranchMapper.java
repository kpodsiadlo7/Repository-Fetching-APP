package com.example.atipera.domain;

import com.example.atipera.dto.BranchDto;
import com.example.atipera.model.Branch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class BranchMapper {
    private final CommitMapper commitMapper;

    List<Branch> fromDtoList(final List<BranchDto> branchDto) {
        return branchDto.stream()
                .map(this::fromDto)
                .collect(Collectors.toList());
    }

    Branch fromDto(final BranchDto branchDto) {
        return Branch.builder()
                .name(branchDto.getName())
                .commit(commitMapper.fromDto(branchDto.getCommit())).build();
    }

    List<BranchDto> toDtoList(final List<Branch> branch) {
        return branch.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    BranchDto toDto(final Branch branch) {
        return new BranchDto(
                branch.getName(),
                commitMapper.toDto(branch.getCommit())
        );
    }
}
