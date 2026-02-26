package com.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author Request DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRequestDTO {
    
    private String name;
    private String biography;
    private String email;
}
