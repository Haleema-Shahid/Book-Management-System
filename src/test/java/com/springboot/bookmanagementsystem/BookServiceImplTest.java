package com.springboot.bookmanagementsystem;

import com.springboot.bookmanagementsystem.dtos.BookRequestDTO;
import com.springboot.bookmanagementsystem.dtos.BookResponseDTO;
import com.springboot.bookmanagementsystem.entities.AuthorEntity;
import com.springboot.bookmanagementsystem.entities.BookEntity;
import com.springboot.bookmanagementsystem.exceptions.NotFoundException;
import com.springboot.bookmanagementsystem.repositories.AuthorRepository;
import com.springboot.bookmanagementsystem.repositories.BookRepository;
import com.springboot.bookmanagementsystem.services.BookServiceImpl;
import com.springboot.bookmanagementsystem.utils.BookUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private static BookUtils bookUtils;

    @InjectMocks
    private BookServiceImpl bookService;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    void testGetAllBooks() {
        // Arrange
        List<BookEntity> books = new ArrayList<>();
        when(bookRepository.findAll()).thenReturn(books);

        // Act
        List<BookResponseDTO> result = bookService.getAllBooks();

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void testGetBookById() {
        // Arrange
        int bookId = 1;
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(bookId);
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(1);
        authorEntity.setFirstName("John");
        authorEntity.setLastName("Doe");
        authorEntity.setBooksByAuthorId(null);
        bookEntity.setAuthorByAuthorId(authorEntity);
        when(bookRepository.findById(anyInt())).thenReturn(Optional.of(bookEntity));

        // Act
        BookResponseDTO result = bookService.getBookById(bookId);

        // Assert
        assertNotNull(result);
        assertEquals(bookId, result.getId());
    }

    @Test
    void testGetBookByIdNotFound() {
        // Arrange
        int bookId = 1;
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NotFoundException.class, () -> {
            bookService.getBookById(bookId);
        });
    }
    @Test
    void testUpdateBookNotFound() {
        // Arrange
        int bookId = 1;
        BookRequestDTO bookRequestDTO = new BookRequestDTO();
        bookRequestDTO.setId(bookId);

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NotFoundException.class, () -> {
            bookService.updateBook(bookRequestDTO);
        });
    }

    @Test
    void testGetBookByTitle() {
        // Arrange
        String title = "Sample Book";
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(title);
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(1);
        authorEntity.setFirstName("Joe");
        authorEntity.setLastName("Doe");
        authorEntity.setBooksByAuthorId(null);
        bookEntity.setAuthorByAuthorId(authorEntity);
        when(bookRepository.findByTitle(title)).thenReturn(Optional.of(bookEntity));

        // Act
        BookResponseDTO result = bookService.getBookByTitle(title);

        // Assert
        assertNotNull(result);
        assertEquals(title, result.getTitle());
    }

    @Test
    void testGetBookByTitleNotFound() {
        // Arrange
        String title = "Non-existent Book";
        when(bookRepository.findByTitle(title)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NotFoundException.class, () -> {
            bookService.getBookByTitle(title);
        });
    }
}
