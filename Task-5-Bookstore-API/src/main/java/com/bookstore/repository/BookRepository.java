package com.bookstore.repository;

import com.bookstore.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Book Repository
 * 
 * Provides database operations for Book entity.
 * Extends JpaRepository for basic CRUD and JpaSpecificationExecutor for filtering.
 * 
 * Key Concepts:
 * - Spring Data JPA
 * - Pagination support (Pageable)
 * - Custom queries with JPQL
 * - Specification pattern for filtering
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    
    // Find book by ISBN
    Optional<Book> findByIsbn(String isbn);
    
    // Find books by title containing (case-insensitive)
    List<Book> findByTitleContainingIgnoreCase(String title);
    
    // Find books by genre
    List<Book> findByGenre(String genre);
    
    // Find books by author ID
    List<Book> findByAuthorId(Long authorId);
    
    // Find books by price range
    List<Book> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    
    // Find books by publisher
    List<Book> findByPublisher(String publisher);
    
    // Custom JPQL query - Find books by author name
    @Query("SELECT b FROM Book b WHERE b.author.name = :authorName")
    List<Book> findBooksByAuthorName(@Param("authorName") String authorName);
    
    // Custom JPQL query - Find expensive books
    @Query("SELECT b FROM Book b WHERE b.price > :price ORDER BY b.price DESC")
    List<Book> findExpensiveBooks(@Param("price") BigDecimal price);
    
    // Pagination - Get all books with pagination
    Page<Book> findAll(Pageable pageable);
    
    // Check if book exists by ISBN
    boolean existsByIsbn(String isbn);
}
