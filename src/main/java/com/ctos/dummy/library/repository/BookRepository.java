package com.ctos.dummy.library.repository;

import com.ctos.dummy.library.entity.Book;
import com.ctos.dummy.library.entity.Aisle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT b FROM Book b JOIN b.aisles a WHERE a.aisleId = :aisleId")
    List<Book> findByAisleId(@Param("aisleId") Integer aisleId);

    // Alternatively find by aisle object
    List<Book> findDistinctByAislesContaining(Aisle aisle);
}