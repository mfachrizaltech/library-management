package com.ctos.dummy.library.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libraries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/*  2.  Create Entity named Library with the following attributes:
	int libraryId
	String libraryName
*/
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer libraryId;

    @Column(nullable = false)
    private String libraryName;

    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Aisle> aisles = new ArrayList<>();

    public void addAisle(Aisle aisle) {
        aisles.add(aisle);
        aisle.setLibrary(this);
    }

    public void removeAisle(Aisle aisle) {
        aisles.remove(aisle);
        aisle.setLibrary(null);
    }
}
