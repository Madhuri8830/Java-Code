package com.bookstore.repository;

import com.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

/**
 * Author Repository
 * 
 * Provides database operations for Author entity.
 * Extends JpaRepository for basic CRUD operations.
 * 
 * Key Concepts:
 * - Spring Data JPA
 * - Custom query methods
 * - @Query annotation for custom JPQL
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    
    // Find author by email
    Optional<Author> findByEmail(String email);
    
    // Find authors by name containing (case-insensitive)
    List<Author> findByNameContainingIgnoreCase(String name);
    
    // Custom JPQL query
    @Query("SELECT a FROM Author a WHERE a.name = :name")
    Optional<Author> findAuthorByName(String name);
    
    // Check if author exists by email
    boolean existsByEmail(String email);
}
