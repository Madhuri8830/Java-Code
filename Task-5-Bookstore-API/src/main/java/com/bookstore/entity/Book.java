package com.bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Book Entity
 * 
 * Represents a book in the bookstore system.
 * Many Books can have one Author (Many-to-One relationship)
 * 
 * Key Concepts:
 * - JPA Entity with proper annotations
 * - Many-to-One relationship with Author
 * - Various data types (String, BigDecimal, LocalDate)
 * - Column constraints (@Column)
 */
@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(unique = true)
    private String isbn;
    
    private String description;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    
    private Integer quantity;
    
    @Column(name = "published_date")
    private LocalDate publishedDate;
    
    private String genre;
    
    private String publisher;
    
    // Many-to-One relationship: Many books can have one author
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;
    
    // Constructor with required fields
    public Book(String title, String isbn, BigDecimal price) {
        this.title = title;
        this.isbn = isbn;
        this.price = price;
    }
}
