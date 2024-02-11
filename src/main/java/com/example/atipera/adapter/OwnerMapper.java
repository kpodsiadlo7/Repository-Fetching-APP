package com.example.atipera.adapter;

import com.example.atipera.dto.OwnerDto;
import com.example.atipera.exception.enumes.ErrorState;
import com.example.atipera.exception.IncorrectOwnerException;
import com.example.atipera.model.Owner;
import org.springframework.stereotype.Service;

@Service
class OwnerMapper {
    Owner fromDto(final OwnerDto owner) {
        if (owner.getLogin() == null) throw new IncorrectOwnerException(ErrorState.INVALID_OWNER_DTO);
        return Owner.builder()
                .login(owner.getLogin()).build();
    }

    OwnerDto toDto(final Owner owner) {
        return new OwnerDto(
                owner.getLogin()
        );
    }
}
