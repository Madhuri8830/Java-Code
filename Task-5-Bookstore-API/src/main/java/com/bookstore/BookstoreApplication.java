package com.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot Application for Bookstore REST API
 * 
 * Features:
 * - RESTful API with Spring Boot
 * - JPA with H2/MySQL database
 * - Swagger API Documentation
 * - Pagination, Sorting, and Filtering
 */
@SpringBootApplication
public class BookstoreApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }
}
