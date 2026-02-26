package com.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Author Response DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponseDTO {
    
    private Long id;
    private String name;
    private String biography;
    private String email;
    private List<BookDTO> books;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookDTO {
        private Long id;
        private String title;
        private String isbn;
    }
}
