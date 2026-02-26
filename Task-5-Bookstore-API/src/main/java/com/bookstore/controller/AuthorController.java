package com.bookstore.controller;

import com.bookstore.dto.AuthorRequestDTO;
import com.bookstore.dto.AuthorResponseDTO;
import com.bookstore.entity.Author;
import com.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author Controller
 * 
 * REST API endpoints for Author operations.
 * Follows Controller layer pattern in layered architecture.
 */
@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    
    @Autowired
    private AuthorService authorService;
    
    // Create Author
    @PostMapping
    public ResponseEntity<AuthorResponseDTO> createAuthor(@RequestBody AuthorRequestDTO dto) {
        Author author = new Author();
        author.setName(dto.getName());
        author.setBiography(dto.getBiography());
        author.setEmail(dto.getEmail());
        
        Author saved = authorService.saveAuthor(author);
        return new ResponseEntity<>(mapToResponseDTO(saved), HttpStatus.CREATED);
    }
    
    // Get all authors
    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthors() {
        List<AuthorResponseDTO> authors = authorService.getAllAuthors()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(authors);
    }
    
    // Get all authors with pagination
    @GetMapping("/paginated")
    public ResponseEntity<Page<AuthorResponseDTO>> getAllAuthorsPaginated(Pageable pageable) {
        Page<AuthorResponseDTO> authors = authorService.getAllAuthors(pageable)
                .map(this::mapToResponseDTO);
        return ResponseEntity.ok(authors);
    }
    
    // Get author by ID
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id)
                .map(author -> ResponseEntity.ok(mapToResponseDTO(author)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Search authors by name
    @GetMapping("/search")
    public ResponseEntity<List<AuthorResponseDTO>> searchAuthors(@RequestParam String name) {
        List<AuthorResponseDTO> authors = authorService.searchAuthorsByName(name)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(authors);
    }
    
    // Update Author
    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> updateAuthor(@PathVariable Long id, 
                                                          @RequestBody AuthorRequestDTO dto) {
        return authorService.getAuthorById(id)
                .map(author -> {
                    author.setName(dto.getName());
                    author.setBiography(dto.getBiography());
                    author.setEmail(dto.getEmail());
                    Author updated = authorService.saveAuthor(author);
                    return ResponseEntity.ok(mapToResponseDTO(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Delete Author
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        if (authorService.existsById(id)) {
            authorService.deleteAuthor(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    // Helper method to map Author to AuthorResponseDTO
    private AuthorResponseDTO mapToResponseDTO(Author author) {
        AuthorResponseDTO dto = new AuthorResponseDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        dto.setBiography(author.getBiography());
        dto.setEmail(author.getEmail());
        
        if (author.getBooks() != null) {
            dto.setBooks(author.getBooks().stream()
                    .map(book -> {
                        AuthorResponseDTO.BookDTO bookDTO = new AuthorResponseDTO.BookDTO();
                        bookDTO.setId(book.getId());
                        bookDTO.setTitle(book.getTitle());
                        bookDTO.setIsbn(book.getIsbn());
                        return bookDTO;
                    })
                    .collect(Collectors.toList()));
        }
        
        return dto;
    }
}
