package com.springboot.bookmanagementsystem.utils;

import com.springboot.bookmanagementsystem.dtos.BookResponseDTO;
import com.springboot.bookmanagementsystem.entities.BookEntity;

public class BookUtils {
    public static BookResponseDTO mapBookEntityToDTO(BookEntity bookEntity) {
        return BookResponseDTO.builder()
                .id(bookEntity.getId())
                .title(bookEntity.getTitle())
                .isbn(bookEntity.getIsbn())
                .publicationDate(bookEntity.getPublicationDate())
                .summary(bookEntity.getSummary())
                .authorByAuthorId(AuthorUtils.toAuthorEntityDTO(bookEntity.getAuthorByAuthorId()))
                .build();
    }
    public static boolean isValidISBN(String isbn) {
        // Remove dashes and spaces from the input ISBN
        isbn = isbn.replaceAll("[-\\s]", "");

        // Check if the ISBN is 10 or 13 digits long
        if (isbn.length() != 10 && isbn.length() != 13) {
            return false;
        }

        // For 10-digit ISBN, validate the checksum digit
        if (isbn.length() == 10) {
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                int digit = Character.getNumericValue(isbn.charAt(i));
                sum += (10 - i) * digit;
            }
            char lastChar = isbn.charAt(9);
            int checksum = (lastChar == 'X') ? 10 : Character.getNumericValue(lastChar);
            return (sum + checksum) % 11 == 0;
        }

        // For 13-digit ISBN, validate the checksum digit
        if (isbn.length() == 13) {
            int sum = 0;
            for (int i = 0; i < 12; i++) {
                int digit = Character.getNumericValue(isbn.charAt(i));
                sum += (i % 2 == 0) ? digit : digit * 3;
            }
            int checksum = Character.getNumericValue(isbn.charAt(12));
            return (10 - (sum % 10)) % 10 == checksum;
        }

        return false;
    }

}
