package com.example.bookmanagementsystem.services;

import com.example.bookmanagementsystem.dtos.BookRequestDTO;
import com.example.bookmanagementsystem.dtos.BookResponseDTO;
import com.example.bookmanagementsystem.entities.AuthorEntity;
import com.example.bookmanagementsystem.entities.BookEntity;
import com.example.bookmanagementsystem.repositories.AuthorRepository;
import com.example.bookmanagementsystem.repositories.BookRepository;
import com.example.bookmanagementsystem.utils.AuthorUtils;
import com.example.bookmanagementsystem.utils.BookUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
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

        // TODO: NOT FOUND EXCEPTION in place of null
        return optionalBookEntity.map(BookUtils::mapBookEntityToDTO).orElse(null);
    }


    @Override
    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {
        BookEntity bookEntity = new BookEntity();

        bookEntity.setTitle(bookRequestDTO.getTitle());
        bookEntity.setIsbn(bookRequestDTO.getIsbn());
        bookEntity.setPublicationDate(bookRequestDTO.getPublicationDate());
        bookEntity.setSummary(bookRequestDTO.getSummary());

        Optional<AuthorEntity> optionalAuthor = authorRepository.findById(bookRequestDTO.getAuthorByAuthorId());
        if(optionalAuthor.isEmpty())
        {
            // TODO: AUTHOR NOT FOUND EXCEPTION
            return null;
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
            bookEntity.setIsbn(bookRequestDTO.getIsbn());
            bookEntity.setPublicationDate(bookRequestDTO.getPublicationDate());
            bookEntity.setSummary(bookRequestDTO.getSummary());

            Optional<AuthorEntity> optionalAuthor = authorRepository.findById(bookRequestDTO.getAuthorByAuthorId());
            if(optionalAuthor.isEmpty())
            {
                // TODO: AUTHOR NOT FOUND EXCEPTION
                return null;
            }

            bookEntity.setAuthorByAuthorId(optionalAuthor.get());

            // Save the updated book in the database
            bookEntity = bookRepository.save(bookEntity);

            return BookUtils.mapBookEntityToDTO(bookEntity);
        } else {
            // TODO: BOOK NOT FOUND EXCEPTION
            return null;
        }
    }

}
