package com.ctos.dummy.library.mapper;

import com.ctos.dummy.library.dto.*;
import com.ctos.dummy.library.entity.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LibraryMapper {

    public static Library toEntity(LibraryDTO dto) {
        if (dto == null) return null;

        Library library = new Library();
        library.setLibraryName(dto.libraryName());

        if (dto.aisles() != null) {
            List<Aisle> aisles = dto.aisles().stream()
                    .map(a -> toEntity(a, library))
                    .collect(Collectors.toList());
            library.setAisles(aisles);
        }

        return library;
    }

    public static Aisle toEntity(AisleDTO dto, Library library) {
        if (dto == null) return null;

        Aisle aisle = new Aisle();
        aisle.setAisleName(dto.aisleName());
        aisle.setLibrary(library);

        if (dto.books() != null) {
            Set<Book> books = dto.books().stream()
                    .map(LibraryMapper::toEntity)
                    .collect(Collectors.toSet());
            aisle.setBooks(books);
        }
        return aisle;
    }

    public static Book toEntity(BookDTO dto) {
        if (dto == null) return null;

        Book book = new Book();
        book.setBookName(dto.bookName());
        return book;
    }

    public static LibraryDTO toDTO(Library entity) {
        if (entity == null) return null;

        return new LibraryDTO(
                entity.getLibraryName(),
                entity.getAisles() != null
                        ? entity.getAisles().stream()
                        .map(LibraryMapper::toDTO)
                        .collect(Collectors.toList())
                        : null
        );
    }

    public static AisleDTO toDTO(Aisle entity) {
        if (entity == null) return null;

        return new AisleDTO(
                entity.getAisleName(),
                entity.getBooks() != null
                        ? entity.getBooks().stream()
                        .map(LibraryMapper::toDTO)
                        .collect(Collectors.toList())
                        : null
        );
    }

    public static BookDTO toDTO(Book entity) {
        if (entity == null) return null;
        return new BookDTO(entity.getBookName());
    }

    public static LibraryDTO toDto(Library library) {
        if (library == null) return null;

        List<AisleDTO> aisles = library.getAisles().stream()
                .map(a -> new AisleDTO(
                        a.getAisleName(),
                        a.getBooks().stream()
                                .map(b -> new BookDTO(b.getBookName()))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());

        return new LibraryDTO(library.getLibraryName(), aisles);
    }

    public static AisleDTO toDto(Aisle aisle) {
        if (aisle == null) return null;
        return new AisleDTO(
                aisle.getAisleName(),
                aisle.getBooks() != null
                        ? aisle.getBooks().stream()
                        .map(book -> new BookDTO(book.getBookName()))
                        .collect(Collectors.toList())
                        : List.of()
        );
    }
}
