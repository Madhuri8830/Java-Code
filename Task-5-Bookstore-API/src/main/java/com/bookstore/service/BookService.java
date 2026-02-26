package com.bookstore.service;

import com.bookstore.entity.Book;
import com.bookstore.entity.Author;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Book Service
 * 
 * Business logic layer for Book operations.
 * Implements pagination, sorting, and filtering.
 * 
 * Key Concepts:
 * - @Service annotation
 * - @Transactional for database transactions
 * - Specification pattern for dynamic filtering
 * - Pagination and Sorting support
 */
@Service
@Transactional
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private AuthorRepository authorRepository;
    
    // Create or Update Book
    public Book saveBook(Book book, Long authorId) {
        if (authorId != null) {
            Author author = authorRepository.findById(authorId)
                    .orElseThrow(() -> new RuntimeException("Author not found with id: " + authorId));
            book.setAuthor(author);
        }
        return bookRepository.save(book);
    }
    
    // Get all books
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    // Get all books with pagination
    @Transactional(readOnly = true)
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
    
    // Get all books with sorting
    @Transactional(readOnly = true)
    public List<Book> getAllBooks(Sort sort) {
        return bookRepository.findAll(sort);
    }
    
    // Get book by ID
    @Transactional(readOnly = true)
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
    
    // Get book by ISBN
    @Transactional(readOnly = true)
    public Optional<Book> getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }
    
    // Search books by title
    @Transactional(readOnly = true)
    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
    
    // Get books by genre
    @Transactional(readOnly = true)
    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }
    
    // Get books by author ID
    @Transactional(readOnly = true)
    public List<Book> getBooksByAuthor(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }
    
    // Get books by price range
    @Transactional(readOnly = true)
    public List<Book> getBooksByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return bookRepository.findByPriceBetween(minPrice, maxPrice);
    }
    
    // Get books by publisher
    @Transactional(readOnly = true)
    public List<Book> getBooksByPublisher(String publisher) {
        return bookRepository.findByPublisher(publisher);
    }
    
    // Get expensive books
    @Transactional(readOnly = true)
    public List<Book> getExpensiveBooks(BigDecimal minPrice) {
        return bookRepository.findExpensiveBooks(minPrice);
    }
    
    // Dynamic filtering with Specifications
    @Transactional(readOnly = true)
    public Page<Book> filterBooks(String title, String genre, String authorName, 
                                   BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        
        Specification<Book> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            // Filter by title
            if (title != null && !title.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
            }
            
            // Filter by genre
            if (genre != null && !genre.isEmpty()) {
                predicates.add(cb.equal(root.get("genre"), genre));
            }
            
            // Filter by author name (join)
            if (authorName != null && !authorName.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("author").get("name")), 
                                       "%" + authorName.toLowerCase() + "%"));
            }
            
            // Filter by price range
            if (minPrice != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), minPrice));
            }
            if (maxPrice != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), maxPrice));
            }
            
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        
        return bookRepository.findAll(spec, pageable);
    }
    
    // Delete book by ID
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    
    // Check if book exists
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return bookRepository.existsById(id);
    }
    
    // Count total books
    @Transactional(readOnly = true)
    public long countBooks() {
        return bookRepository.count();
    }
}
