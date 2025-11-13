package com.ctos.dummy.library.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/* 4.  Create Entity name Book with the following attribute:
	int bookId
	String bookName    
*/    
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(nullable = false)
    private String bookName;

    @ManyToMany(mappedBy = "books")
    @ToString.Exclude @EqualsAndHashCode.Exclude
    // 6.  Create Many to Many relationship between Aisle and Book.  Many aisles will have many book. 
    //     You might need additional things to add.
    private Set<Aisle> aisles = new HashSet<>();
}
