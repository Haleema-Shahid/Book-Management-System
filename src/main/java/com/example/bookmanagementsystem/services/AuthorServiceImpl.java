package com.example.bookmanagementsystem.services;

import com.example.bookmanagementsystem.dtos.AuthorEntityDTO;
import com.example.bookmanagementsystem.entities.AuthorEntity;
import com.example.bookmanagementsystem.exceptions.InvalidCredentialsException;
import com.example.bookmanagementsystem.exceptions.NotAuthorizedException;
import com.example.bookmanagementsystem.exceptions.NotFoundException;
import com.example.bookmanagementsystem.repositories.AuthorRepository;
import com.example.bookmanagementsystem.repositories.BookRepository;
import com.example.bookmanagementsystem.utils.AuthorUtils;
import com.example.bookmanagementsystem.utils.DateUtils;
import com.example.bookmanagementsystem.utils.RangeUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<AuthorEntityDTO> getAllAuthors() {
        try {
            List<AuthorEntity> authorEntities = authorRepository.findAll();

            return authorEntities.stream()
                    .map(authorEntity -> AuthorEntityDTO.builder()
                            .id(authorEntity.getId())
                            .firstName(authorEntity.getFirstName())
                            .lastName(authorEntity.getLastName())
                            .dateOfBirth(authorEntity.getDateOfBirth())
                            .biography(authorEntity.getBiography())
                            .build())
                    .toList();
        }
        catch (Exception exception)
        {
           throw new NotAuthorizedException("You are not Authorized!");
        }
    }

    @Override
    public AuthorEntityDTO getAuthorById(Integer id) {
            Optional<AuthorEntity> optionalAuthor = authorRepository.findById(id);

            if(optionalAuthor.isEmpty())
            {
                throw new NotFoundException("Author Not Found");
            }
            return optionalAuthor.map(AuthorUtils::toAuthorEntityDTO).orElse(null);
    }

    @Override
    public AuthorEntityDTO createAuthor(AuthorEntityDTO authorEntityDTO) {
        if (
                DateUtils.isDateInPast(authorEntityDTO.getDateOfBirth()) ||
                        RangeUtils.isLengthInRange(authorEntityDTO.getBiography(), 1000)) {
            throw new InvalidCredentialsException("Invalid Arguments!");
        }
        AuthorEntity authorEntity = AuthorUtils.mapAuthorDTOToEntity(authorEntityDTO);
        authorRepository.save(authorEntity);
        return authorEntityDTO;
    }

    @Override
    public AuthorEntityDTO updateAuthor(AuthorEntityDTO authorEntityDTO) {
        Optional<AuthorEntity>  optionalAuthor = authorRepository.findById(authorEntityDTO.getId());
        if(optionalAuthor.isEmpty()) {
            throw new NotFoundException("Author Not Found");
        }

        AuthorEntity authorEntity = optionalAuthor.get();

        if (
                DateUtils.isDateInPast(authorEntityDTO.getDateOfBirth()) ||
                        RangeUtils.isLengthInRange(authorEntityDTO.getBiography(), 1000)) {
            throw new InvalidCredentialsException("Parameters Not Correct!");
        }

        // Update AuthorEntity with data from AuthorEntityDTO
        authorEntity.setFirstName(authorEntityDTO.getFirstName());
        authorEntity.setLastName(authorEntityDTO.getLastName());
        authorEntity.setDateOfBirth(authorEntityDTO.getDateOfBirth());
        authorEntity.setBiography(authorEntityDTO.getBiography());

        // Save the updated AuthorEntity
        authorEntity = authorRepository.save(authorEntity);

        // Convert the updated AuthorEntity back to AuthorEntityDTO and return it
        return AuthorEntityDTO.builder()
                .id(authorEntity.getId())
                .firstName(authorEntity.getFirstName())
                .lastName(authorEntity.getLastName())
                .dateOfBirth(authorEntity.getDateOfBirth())
                .biography(authorEntity.getBiography())
                .build();

    }
}
