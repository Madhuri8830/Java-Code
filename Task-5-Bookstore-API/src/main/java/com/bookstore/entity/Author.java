package com.bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Author Entity
 * 
 * Represents an author in the bookstore system.
 * One Author can have many Books (One-to-Many relationship)
 * 
 * Key Concepts:
 * - JPA Entity
 * - Lombok annotations (@Data, @NoArgsConstructor, @AllArgsConstructor)
 * - One-to-Many relationship with Book
 * - Cascade operations (orphanRemoval)
 */
@Entity
@Table(name = "authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(length = 1000)
    private String biography;
    
    @Column(unique = true)
    private String email;
    
    // One-to-Many relationship: One author can write many books
    // cascade = CascadeType.ALL means operations on Author cascade to Books
    // orphanRemoval = true removes books when removed from author's book list
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();
    
    // Helper method to add book
    public void addBook(Book book) {
        books.add(book);
        book.setAuthor(this);
    }
    
    // Helper method to remove book
    public void removeBook(Book book) {
        books.remove(book);
        book.setAuthor(null);
    }
}
