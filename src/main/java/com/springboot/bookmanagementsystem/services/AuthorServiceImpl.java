package com.springboot.bookmanagementsystem.services;

import com.springboot.bookmanagementsystem.dtos.AuthorEntityDTO;
import com.springboot.bookmanagementsystem.dtos.BookResponseDTO;
import com.springboot.bookmanagementsystem.entities.AuthorEntity;
import com.springboot.bookmanagementsystem.entities.BookEntity;
import com.springboot.bookmanagementsystem.exceptions.InvalidCredentialsException;
import com.springboot.bookmanagementsystem.exceptions.NotAuthorizedException;
import com.springboot.bookmanagementsystem.exceptions.NotFoundException;
import com.springboot.bookmanagementsystem.repositories.AuthorRepository;
import com.springboot.bookmanagementsystem.repositories.BookRepository;
import com.springboot.bookmanagementsystem.utils.AuthorUtils;
import com.springboot.bookmanagementsystem.utils.BookUtils;
import com.springboot.bookmanagementsystem.utils.DateUtils;
import com.springboot.bookmanagementsystem.utils.RangeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

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
                throw new NotFoundException("Author Not Found!");
            }
            return optionalAuthor.map(AuthorUtils::toAuthorEntityDTO).orElse(null);
    }

    @Override
    public AuthorEntityDTO createAuthor(AuthorEntityDTO authorEntityDTO) {
        if (
                DateUtils.isDateInPast(authorEntityDTO.getDateOfBirth()) ||
                        !RangeUtils.isLengthInRange(authorEntityDTO.getBiography(), 1000)) {
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
            throw new NotFoundException("Author Not Found!");
        }

        AuthorEntity authorEntity = optionalAuthor.get();

        if (
                DateUtils.isDateInPast(authorEntityDTO.getDateOfBirth()) ||
                        !RangeUtils.isLengthInRange(authorEntityDTO.getBiography(), 1000)) {
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

    @Override
    public List<BookResponseDTO> getBooksByAuthor(Integer id) {
        Optional<AuthorEntity>  optionalAuthor = authorRepository.findById(id);
        if(optionalAuthor.isEmpty()) {
            throw new NotFoundException("Author Not Found!");
        }

        List<BookEntity> bookEntities = bookRepository.findByAuthorByAuthorId(optionalAuthor.get());
        return bookEntities.stream()
                .map(BookUtils::mapBookEntityToDTO)
                .collect(Collectors.toList());
    }
}
