package com.ctos.dummy.library.service;

import com.ctos.dummy.library.dto.AisleDTO;
import com.ctos.dummy.library.dto.BookDTO;
import com.ctos.dummy.library.dto.LibraryDTO;
import com.ctos.dummy.library.entity.Aisle;
import com.ctos.dummy.library.entity.Library;
import com.ctos.dummy.library.mapper.LibraryMapper;
import com.ctos.dummy.library.repository.AisleRepository;
import com.ctos.dummy.library.repository.BookRepository;
import com.ctos.dummy.library.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibraryService {
    private final LibraryRepository libraryRepository;
    private final AisleRepository aisleRepository;
    private final BookRepository bookRepository;

    // Get all aisles based on library (by id)
    public List<Aisle> getAislesByLibraryId(Integer libraryId) {
        return aisleRepository.findByLibraryLibraryId(libraryId);
    }

    // Get all aisles by library entity
    public List<Aisle> getAislesByLibrary(Library library) {
        if (library == null) return Collections.emptyList();
        return aisleRepository.findByLibrary(library);
    }

    // 9.  Create a method to save library information
    @Transactional
    public LibraryDTO saveLibrary(LibraryDTO dto) {
        Library library = LibraryMapper.toEntity(dto);
        Library saved = libraryRepository.save(library);
        return LibraryMapper.toDTO(saved);
    }

    // 10. Create a method to update library information
    @Transactional
    public Optional<LibraryDTO> updateLibrary(Integer libraryId, LibraryDTO dto) {
        return libraryRepository.findById(libraryId).map(existing -> {
            existing.setLibraryName(dto.libraryName());
            existing.getAisles().clear();

            if (dto.aisles() != null) {
                dto.aisles().forEach(a -> {
                    Aisle aisle = LibraryMapper.toEntity(a, existing);
                    existing.getAisles().add(aisle);
                });
            }
            Library saved = libraryRepository.save(existing);
            return LibraryMapper.toDTO(saved);
        });
    }

    // 8. Create a Service - Library Service Create a method to get all aisles based on library
    public List<AisleDTO> getAllAislesByLibraryName(String libraryName) {
        return libraryRepository.findByLibraryNameContainingIgnoreCase(libraryName)
                .stream() // list of libraries
                .flatMap(library -> library.getAisles().stream()) // flatten aisles across all found libraries
                .map(LibraryMapper::toDto) // map each aisle to DTO
                .collect(Collectors.toList());
    }

    // 11. Create a method to get all books based on aisle information
    public Set<BookDTO> findByAisleNameAndLibraryLibraryName(String aisleName, String libraryName) {
        return aisleRepository.findByAisleNameAndLibraryLibraryName(aisleName, libraryName)
                .stream()
                .flatMap(aisle -> aisle.getBooks().stream())
                .map(book -> new BookDTO(book.getBookName())) // convert to DTO
                .collect(Collectors.toSet());
    }

}
