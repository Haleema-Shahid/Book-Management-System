package com.springboot.bookmanagementsystem;

import com.springboot.bookmanagementsystem.dtos.AuthorEntityDTO;
import com.springboot.bookmanagementsystem.dtos.BookResponseDTO;
import com.springboot.bookmanagementsystem.entities.AuthorEntity;
import com.springboot.bookmanagementsystem.entities.BookEntity;
import com.springboot.bookmanagementsystem.exceptions.NotFoundException;
import com.springboot.bookmanagementsystem.repositories.AuthorRepository;
import com.springboot.bookmanagementsystem.repositories.BookRepository;
import com.springboot.bookmanagementsystem.services.AuthorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    void testGetAllAuthors() {
        // Arrange
        List<AuthorEntity> authors = new ArrayList<>();
        when(authorRepository.findAll()).thenReturn(authors);

        // Act
        List<AuthorEntityDTO> result = authorService.getAllAuthors();

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void testGetAuthorById() {
        // Arrange
        int authorId = 1;
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(authorId);
        when(authorRepository.findById(authorId)).thenReturn(Optional.of(authorEntity));

        // Act
        AuthorEntityDTO result = authorService.getAuthorById(authorId);

        // Assert
        assertNotNull(result);
        assertEquals(authorId, result.getId());
    }

    @Test
    void testGetAuthorByIdNotFound() {
        // Arrange
        int authorId = 1;
        when(authorRepository.findById(authorId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NotFoundException.class, () -> {
            authorService.getAuthorById(authorId);
        });
    }

    @Test
    void testCreateAuthor() {
        // Arrange
        AuthorEntityDTO authorDTO = new AuthorEntityDTO();
        authorDTO.setFirstName("John");
        authorDTO.setLastName("Doe");
        authorDTO.setDateOfBirth(Date.from(Instant.now().plusSeconds(3 * 24 * 60 * 60))); // Adding 3 days in seconds
        authorDTO.setBiography("Sample Biography");

        AuthorEntity savedAuthorEntity = new AuthorEntity();
        savedAuthorEntity.setId(1);
        when(authorRepository.save(any(AuthorEntity.class))).thenReturn(savedAuthorEntity);

        // Act
        AuthorEntityDTO result = authorService.createAuthor(authorDTO);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testUpdateAuthor() {
        // Arrange
        int authorId = 1;
        AuthorEntityDTO authorDTO = new AuthorEntityDTO();
        authorDTO.setId(authorId);
        authorDTO.setFirstName("Updated John");
        authorDTO.setDateOfBirth(java.util.Date.from(Instant.now()));
        authorDTO.setBiography("This is test Biography");

        AuthorEntity existingAuthorEntity = new AuthorEntity();
        existingAuthorEntity.setId(authorId);
        when(authorRepository.findById(authorId)).thenReturn(Optional.of(existingAuthorEntity));

        AuthorEntity updatedAuthorEntity = new AuthorEntity();
        updatedAuthorEntity.setId(authorId);
        when(authorRepository.save(any(AuthorEntity.class))).thenReturn(updatedAuthorEntity);

        // Act
        AuthorEntityDTO result = authorService.updateAuthor(authorDTO);

        // Assert
        assertNotNull(result);
        assertEquals(authorDTO.getId(), result.getId());
    }

    @Test
    void testUpdateAuthorNotFound() {
        // Arrange
        int authorId = 1;
        AuthorEntityDTO authorDTO = new AuthorEntityDTO();
        authorDTO.setId(authorId);

        when(authorRepository.findById(authorId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NotFoundException.class, () -> {
            authorService.updateAuthor(authorDTO);
        });
    }

    @Test
    void testGetBooksByAuthor() {
        // Arrange
        int authorId = 1;
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(authorId);
        when(authorRepository.findById(authorId)).thenReturn(Optional.of(authorEntity));

        List<BookEntity> books = new ArrayList<>();
        when(bookRepository.findByAuthorByAuthorId(authorEntity)).thenReturn(books);

        // Act
        List<BookResponseDTO> result = authorService.getBooksByAuthor(authorId);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void testGetBooksByAuthorNotFound() {
        // Arrange
        int authorId = 1;
        when(authorRepository.findById(authorId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NotFoundException.class, () -> {
            authorService.getBooksByAuthor(authorId);
        });
    }
}
