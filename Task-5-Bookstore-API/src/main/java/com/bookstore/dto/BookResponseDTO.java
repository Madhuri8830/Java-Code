package com.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Book Response DTO
 * 
 * Used for returning book data via API.
 * 
 * Key Concepts:
 * - Data Transfer Object (DTO)
 * - Lombok annotations
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDTO {
    
    private Long id;
    private String title;
    private String isbn;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private LocalDate publishedDate;
    private String genre;
    private String publisher;
    private AuthorDTO author;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthorDTO {
        private Long id;
        private String name;
        private String email;
    }
}
