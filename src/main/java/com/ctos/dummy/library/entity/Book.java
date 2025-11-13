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
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(nullable = false)
    private String bookName;

    @ManyToMany(mappedBy = "books")
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private Set<Aisle> aisles = new HashSet<>();
}