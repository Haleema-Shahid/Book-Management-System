package com.example.bookmanagementsystem.utils;

import com.example.bookmanagementsystem.dtos.AuthorEntityDTO;
import com.example.bookmanagementsystem.entities.AuthorEntity;

public class AuthorUtils {
    public static AuthorEntityDTO toAuthorEntityDTO(AuthorEntity authorEntity) {
        return AuthorEntityDTO.builder()
                .id(authorEntity.getId())
                .firstName(authorEntity.getFirstName())
                .lastName(authorEntity.getLastName())
                .dateOfBirth(authorEntity.getDateOfBirth())
                .biography(authorEntity.getBiography())
                .build();
    }

    public static AuthorEntity mapAuthorDTOToEntity(AuthorEntityDTO authorEntityDTO) {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(authorEntityDTO.getId());
        authorEntity.setFirstName(authorEntityDTO.getFirstName());
        authorEntity.setLastName(authorEntityDTO.getLastName());
        authorEntity.setDateOfBirth(authorEntityDTO.getDateOfBirth());
        authorEntity.setBiography(authorEntityDTO.getBiography());
        return authorEntity;
    }
}
