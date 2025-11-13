package com.ctos.dummy.library.controller;

import com.ctos.dummy.library.dto.AisleDTO;
import com.ctos.dummy.library.dto.BookDTO;
import com.ctos.dummy.library.dto.LibraryDTO;
import com.ctos.dummy.library.entity.Book;
import com.ctos.dummy.library.entity.Library;
import com.ctos.dummy.library.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/libraries")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    // 13. Specific GET - get all books based on isle name 'NATURAL HISTORY' in the library name 'CENTRAL LIBRARY'
    @GetMapping("/central-library/natural-history/books")
    public ResponseEntity<Set<BookDTO>> getBooksByLibraryAndAisleName() {
        Set<BookDTO> books = libraryService.findByAisleNameAndLibraryLibraryName("NATURAL HISTORY", "CENTRAL LIBRARY");
        if (books.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(books);
    }

    // 14. POST - save library information
    @PostMapping
    public ResponseEntity<LibraryDTO> createLibrary(@RequestBody LibraryDTO library) {
        LibraryDTO saved = libraryService.saveLibrary(library);
        return ResponseEntity.ok(saved);
    }

    // 15. PUT - update library information
    @PutMapping("/{id}")
    public ResponseEntity<LibraryDTO> updateLibrary(@PathVariable Integer id, @RequestBody LibraryDTO library) {
        Optional<LibraryDTO> updated = libraryService.updateLibrary(id, library);
        return updated.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 16. GET - get all libraries
    @GetMapping("/{libraryName}/aisles")
    public ResponseEntity<List<AisleDTO>> getAllAislesByLibrary(@PathVariable("libraryName") String libraryName) {
        List<AisleDTO> aisles = libraryService.getAllAislesByLibraryName(libraryName);
        if (aisles.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(aisles);
    }

}