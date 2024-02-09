package com.example.atipera.domain;

import com.example.atipera.dto.OwnerDto;
import com.example.atipera.model.Owner;
import org.springframework.stereotype.Service;

@Service
class OwnerMapper {
    Owner fromDto(final OwnerDto owner) {
        return Owner.builder()
                .login(owner.getLogin()).build();
    }

    OwnerDto toDto(final Owner owner) {
        return new OwnerDto(
                owner.getLogin()
        );
    }
}
