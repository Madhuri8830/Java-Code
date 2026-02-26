package com.bookstore.controller;

import com.bookstore.dto.BookRequestDTO;
import com.bookstore.dto.BookResponseDTO;
import com.bookstore.entity.Book;
import com.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Book Controller
 * 
 * REST API endpoints for Book operations.
 * Implements CRUD, Pagination, Sorting, and Filtering.
 */
@RestController
@RequestMapping("/api/books")
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    // Create Book
    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody BookRequestDTO dto) {
        Book book = mapToEntity(dto);
        Book saved = bookService.saveBook(book, dto.getAuthorId());
        return new ResponseEntity<>(mapToResponseDTO(saved), HttpStatus.CREATED);
    }
    
    // Get all books
    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        List<BookResponseDTO> books = bookService.getAllBooks()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }
    
    // Get all books with pagination
    // Example: GET /api/books/paginated?page=0&size=10&sort=price,desc
    @GetMapping("/paginated")
    public ResponseEntity<Page<BookResponseDTO>> getAllBooksPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase("desc") 
                ? Sort.by(sortBy).descending() 
                : Sort.by(sortBy).ascending();
        
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<BookResponseDTO> books = bookService.getAllBooks(pageable)
                .map(this::mapToResponseDTO);
        
        return ResponseEntity.ok(books);
    }
    
    // Get book by ID
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(book -> ResponseEntity.ok(mapToResponseDTO(book)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Get book by ISBN
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookResponseDTO> getBookByIsbn(@PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn)
                .map(book -> ResponseEntity.ok(mapToResponseDTO(book)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Search books by title
    @GetMapping("/search")
    public ResponseEntity<List<BookResponseDTO>> searchBooks(@RequestParam String title) {
        List<BookResponseDTO> books = bookService.searchBooksByTitle(title)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }
    
    // Get books by genre
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<BookResponseDTO>> getBooksByGenre(@PathVariable String genre) {
        List<BookResponseDTO> books = bookService.getBooksByGenre(genre)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }
    
    // Get books by author
    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<BookResponseDTO>> getBooksByAuthor(@PathVariable Long authorId) {
        List<BookResponseDTO> books = bookService.getBooksByAuthor(authorId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }
    
    // Get books by price range
    @GetMapping("/price")
    public ResponseEntity<List<BookResponseDTO>> getBooksByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {
        List<BookResponseDTO> books = bookService.getBooksByPriceRange(minPrice, maxPrice)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }
    
    // Get expensive books
    @GetMapping("/expensive")
    public ResponseEntity<List<BookResponseDTO>> getExpensiveBooks(
            @RequestParam(defaultValue = "500") BigDecimal minPrice) {
        List<BookResponseDTO> books = bookService.getExpensiveBooks(minPrice)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }
    
    // Filter books with multiple criteria (Pagination + Filtering)
    @GetMapping("/filter")
    public ResponseEntity<Page<BookResponseDTO>> filterBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String authorName,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase("desc") 
                ? Sort.by(sortBy).descending() 
                : Sort.by(sortBy).ascending();
        
        Pageable pageable = PageRequest.of(page, size, sort);
        
        Page<BookResponseDTO> books = bookService.filterBooks(
                title, genre, authorName, minPrice, maxPrice, pageable)
                .map(this::mapToResponseDTO);
        
        return ResponseEntity.ok(books);
    }
    
    // Update Book
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long id, 
                                                       @RequestBody BookRequestDTO dto) {
        return bookService.getBookById(id)
                .map(book -> {
                    Book updatedBook = mapToEntity(dto);
                    updatedBook.setId(id);
                    Book saved = bookService.saveBook(updatedBook, dto.getAuthorId());
                    return ResponseEntity.ok(mapToResponseDTO(saved));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Delete Book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookService.existsById(id)) {
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    // Helper: Map BookRequestDTO to Book entity
    private Book mapToEntity(BookRequestDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setDescription(dto.getDescription());
        book.setPrice(dto.getPrice());
        book.setQuantity(dto.getQuantity());
        book.setPublishedDate(dto.getPublishedDate());
        book.setGenre(dto.getGenre());
        book.setPublisher(dto.getPublisher());
        return book;
    }
    
    // Helper: Map Book entity to BookResponseDTO
    private BookResponseDTO mapToResponseDTO(Book book) {
        BookResponseDTO dto = new BookResponseDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setIsbn(book.getIsbn());
        dto.setDescription(book.getDescription());
        dto.setPrice(book.getPrice());
        dto.setQuantity(book.getQuantity());
        dto.setPublishedDate(book.getPublishedDate());
        dto.setGenre(book.getGenre());
        dto.setPublisher(book.getPublisher());
        
        if (book.getAuthor() != null) {
            BookResponseDTO.AuthorDTO authorDTO = new BookResponseDTO.AuthorDTO();
            authorDTO.setId(book.getAuthor().getId());
            authorDTO.setName(book.getAuthor().getName());
            authorDTO.setEmail(book.getAuthor().getEmail());
            dto.setAuthor(authorDTO);
        }
        
        return dto;
    }
}
