package com.ctos.dummy.library.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "aisles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/* 3.  Create Entity named Aisle with the following attributes:
	int aisleId
	String isleName
*/    
public class Aisle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aisleId;

    @Column(nullable = false)
    private String aisleName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id")
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private Library library;

    // Owning side of the many-to-many
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "aisle_books",
            joinColumns = @JoinColumn(name = "aisle_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private Set<Book> books = new HashSet<>();

//    // Helper methods
//    public void addBook(Book book) {
//        books.add(book);
//        book.getAisles().add(this);
//    }
//
//    public void removeBook(Book book) {
//        books.remove(book);
//        book.getAisles().remove(this);
//    }
}
