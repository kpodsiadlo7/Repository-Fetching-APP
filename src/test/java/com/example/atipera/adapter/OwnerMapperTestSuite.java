package com.example.atipera.adapter;

import com.example.atipera.dto.OwnerDto;
import com.example.atipera.exception.IncorrectOwnerException;
import com.example.atipera.model.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OwnerMapperTestSuite {

    @Autowired
    private OwnerMapper ownerMapper;

    @Test
    @DisplayName("OwnerMapper - fromDto")
    void mapToOwnerFromOwnerDto() {
        //given
        OwnerDto ownerDto = new OwnerDto("login");
        //when
        Owner owner = ownerMapper.fromDto(ownerDto);
        //then
        Assertions.assertNotNull(owner);
        Assertions.assertEquals("login", owner.getLogin());
    }

    @Test
    @DisplayName("OwnerMapper - toDto")
    void mapToOwnerDtoFromOwner() {
        //given
        Owner owner = Owner.builder().login("login").build();
        //when
        OwnerDto ownerDto = ownerMapper.toDto(owner);
        //then
        Assertions.assertNotNull(ownerDto);
        Assertions.assertEquals("login", ownerDto.getLogin());
    }

    @Test
    @DisplayName("OwnerMapper - exception")
    void shouldThrowExceptionWhenOwnerIsNull() {
        //when & then
        Assertions.assertThrows(IncorrectOwnerException.class, () -> ownerMapper.fromDto(null));
    }
}
