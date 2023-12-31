package com.springboot.bookmanagementsystem.services;

import com.springboot.bookmanagementsystem.dtos.BookRequestDTO;
import com.springboot.bookmanagementsystem.dtos.BookResponseDTO;
import com.springboot.bookmanagementsystem.entities.AuthorEntity;
import com.springboot.bookmanagementsystem.entities.BookEntity;
import com.springboot.bookmanagementsystem.exceptions.InvalidCredentialsException;
import com.springboot.bookmanagementsystem.exceptions.NotFoundException;
import com.springboot.bookmanagementsystem.repositories.AuthorRepository;
import com.springboot.bookmanagementsystem.repositories.BookRepository;
import com.springboot.bookmanagementsystem.utils.BookUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public List<BookResponseDTO> getAllBooks() {
        List<BookEntity> bookEntities = bookRepository.findAll();

        return bookEntities.stream()
                .map(BookUtils::mapBookEntityToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public BookResponseDTO getBookById(Integer id) {
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(id);

        if (optionalBookEntity.isEmpty()) {
            throw new NotFoundException("Book Not Found!");
        }
        return optionalBookEntity.map(BookUtils::mapBookEntityToDTO).orElse(null);
    }


    @Override
    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(bookRequestDTO.getTitle());
        String isbn = bookRequestDTO.getIsbn();
        if (!BookUtils.isValidISBN(isbn))
            throw new InvalidCredentialsException("ISBN not in correct format!");
        bookEntity.setIsbn(isbn);
        bookEntity.setPublicationDate(bookRequestDTO.getPublicationDate());
        bookEntity.setSummary(bookRequestDTO.getSummary());

        Optional<AuthorEntity> optionalAuthor = authorRepository.findById(bookRequestDTO.getAuthorByAuthorId());
        if (optionalAuthor.isEmpty()) {
            throw new NotFoundException("Author Not Found!");
        }

        bookEntity.setAuthorByAuthorId(optionalAuthor.get());

        bookEntity = bookRepository.save(bookEntity);

        return BookUtils.mapBookEntityToDTO(bookEntity);
    }


    @Override
    public BookResponseDTO updateBook(BookRequestDTO bookRequestDTO) {
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(bookRequestDTO.getId());

        if (optionalBookEntity.isPresent()) {
            BookEntity bookEntity = optionalBookEntity.get();

            // Update the fields in the entity with the data from the request DTO
            bookEntity.setTitle(bookRequestDTO.getTitle());
            String isbn = bookRequestDTO.getIsbn();
            if (!BookUtils.isValidISBN(isbn))
                throw new InvalidCredentialsException("ISBN not in correct format!");
            bookEntity.setIsbn(isbn);
            bookEntity.setPublicationDate(bookRequestDTO.getPublicationDate());
            bookEntity.setSummary(bookRequestDTO.getSummary());

            Optional<AuthorEntity> optionalAuthor = authorRepository.findById(bookRequestDTO.getAuthorByAuthorId());
            if (optionalAuthor.isEmpty()) {
                throw new NotFoundException("Author Not Found!");
            }

            bookEntity.setAuthorByAuthorId(optionalAuthor.get());

            // Save the updated book in the database
            bookEntity = bookRepository.save(bookEntity);

            return BookUtils.mapBookEntityToDTO(bookEntity);
        } else {
            throw new NotFoundException("Book Not Found");
        }
    }

    @Override
    public BookResponseDTO getBookByTitle(String title) {
        Optional<BookEntity> optionalBookEntity = bookRepository.findByTitle(title);
        if (optionalBookEntity.isPresent()) {
            return optionalBookEntity.map(BookUtils::mapBookEntityToDTO).orElse(null);
        } else {
            throw new NotFoundException("No Book Found By the Given Title!");
        }
    }


}
