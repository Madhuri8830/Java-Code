package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Author Service
 * 
 * Business logic layer for Author operations.
 * Follows Service layer pattern in layered architecture.
 * 
 * Key Concepts:
 * - @Service annotation
 * - @Transactional for database transactions
 * - Dependency Injection with @Autowired
 * - Business logic implementation
 */
@Service
@Transactional
public class AuthorService {
    
    @Autowired
    private AuthorRepository authorRepository;
    
    // Create or Update Author
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }
    
    // Get all authors
    @Transactional(readOnly = true)
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
    
    // Get all authors with pagination
    @Transactional(readOnly = true)
    public Page<Author> getAllAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }
    
    // Get author by ID
    @Transactional(readOnly = true)
    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }
    
    // Get author by email
    @Transactional(readOnly = true)
    public Optional<Author> getAuthorByEmail(String email) {
        return authorRepository.findByEmail(email);
    }
    
    // Search authors by name
    @Transactional(readOnly = true)
    public List<Author> searchAuthorsByName(String name) {
        return authorRepository.findByNameContainingIgnoreCase(name);
    }
    
    // Delete author by ID
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
    
    // Check if author exists
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return authorRepository.existsById(id);
    }
    
    // Count total authors
    @Transactional(readOnly = true)
    public long countAuthors() {
        return authorRepository.count();
    }
}
