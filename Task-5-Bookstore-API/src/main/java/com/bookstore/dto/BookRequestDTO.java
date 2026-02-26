package com.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Book Request DTO
 * 
 * Used for creating/updating books via API.
 * 
 * Key Concepts:
 * - Data Transfer Object (DTO)
 * - Lombok annotations
 * - Validation annotations
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDTO {
    
    private String title;
    private String isbn;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private LocalDate publishedDate;
    private String genre;
    private String publisher;
    private Long authorId;
}
