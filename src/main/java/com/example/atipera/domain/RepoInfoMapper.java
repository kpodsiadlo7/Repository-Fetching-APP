package com.example.atipera.domain;

import com.example.atipera.dto.RepoInfoDto;
import com.example.atipera.model.RepoInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class RepoInfoMapper {

    private final OwnerMapper ownerMapper;
    private final BranchMapper branchMapper;

    List<RepoInfo> fromDtoList(final List<RepoInfoDto> repoNames) {
        return repoNames.stream()
                .map(this::fromDto)
                .collect(Collectors.toList());
    }

    RepoInfo fromDto(final RepoInfoDto repoInfoDto) {
        return RepoInfo.builder()
                .name(repoInfoDto.getName())
                .owner(ownerMapper.fromDto(repoInfoDto.getOwner()))
                .branch(new ArrayList<>()).build();
    }

    List<RepoInfoDto> toDtoList(final List<RepoInfo> repoInfoList) {
        return repoInfoList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    RepoInfoDto toDto(final RepoInfo repoInfo) {
        return new RepoInfoDto(
                repoInfo.getName(),
                ownerMapper.toDto(repoInfo.getOwner()),
                branchMapper.toDtoList(repoInfo.getBranch())
        );
    }
}
