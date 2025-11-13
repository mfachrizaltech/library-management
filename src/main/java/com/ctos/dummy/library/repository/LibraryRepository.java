package com.ctos.dummy.library.repository;


import com.ctos.dummy.library.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Integer> {

    List<Library> findByLibraryNameContainingIgnoreCase(String name);
}
