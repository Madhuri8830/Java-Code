# RESTful Bookstore API

A Spring Boot REST API for managing books and authors in a bookstore.

## Features

- **CRUD Operations** - Create, Read, Update, Delete for Books and Authors
- **Pagination** - Paginate book results
- **Sorting** - Sort books by various fields (price, title, etc.)
- **Filtering** - Filter books by title, genre, author, price range
- **API Documentation** - Swagger UI integration
- **Database** - H2 in-memory database (easily switchable to MySQL)

## Tech Stack

- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- H2 Database
- Swagger/OpenAPI 3.0
- Lombok

## Project Structure

```
Task-5-Bookstore-API/
├── src/main/java/com/bookstore/
│   ├── BookstoreApplication.java
│   ├── config/
│   │   └── SwaggerConfig.java
│   ├── controller/
│   │   ├── AuthorController.java
│   │   └── BookController.java
│   ├── dto/
│   │   ├── AuthorRequestDTO.java
│   │   ├── AuthorResponseDTO.java
│   │   ├── BookRequestDTO.java
│   │   └── BookResponseDTO.java
│   ├── entity/
│   │   ├── Author.java
│   │   └── Book.java
│   ├── repository/
│   │   ├── AuthorRepository.java
│   │   └── BookRepository.java
│   └── service/
│       ├── AuthorService.java
│       └── BookService.java
├── src/main/resources/
│   └── application.properties
└── pom.xml
```

## API Endpoints

### Books

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/books | Get all books |
| GET | /api/books/paginated | Get books with pagination & sorting |
| GET | /api/books/{id} | Get book by ID |
| GET | /api/books/isbn/{isbn} | Get book by ISBN |
| GET | /api/books/search?title=... | Search books by title |
| GET | /api/books/genre/{genre} | Get books by genre |
| GET | /api/books/author/{authorId} | Get books by author |
| GET | /api/books/price?minPrice=...&maxPrice=... | Get books by price range |
| GET | /api/books/filter | Filter books with multiple criteria |
| POST | /api/books | Create new book |
| PUT | /api/books/{id} | Update book |
| DELETE | /api/books/{id} | Delete book |

### Authors

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/authors | Get all authors |
| GET | /api/authors/paginated | Get authors with pagination |
| GET | /api/authors/{id} | Get author by ID |
| GET | /api/authors/search?name=... | Search authors by name |
| POST | /api/authors | Create new author |
| PUT | /api/authors/{id} | Update author |
| DELETE | /api/authors/{id} | Delete author |

## Query Parameters for Pagination & Sorting

```
GET /api/books/paginated?page=0&size=10&sort=price,desc
```

- `page` - Page number (0-indexed)
- `size` - Page size
- `sort` - Sort field and direction (e.g., `price,desc` or `title,asc`)

## Query Parameters for Filtering

```
GET /api/books/filter?title=java&genre=programming&minPrice=100&maxPrice=500&page=0&size=10&sortBy=price&sortDir=asc
```

- `title` - Filter by title (partial match)
- `genre` - Filter by genre
- `authorName` - Filter by author name
- `minPrice` - Minimum price
- `maxPrice` - Maximum price

## Running the Application

### Prerequisites
- JDK 17 or higher
- Maven

### Build and Run

```
bash
cd Task-5-Bookstore-API
mvn clean install
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## Swagger Documentation

Once the application is running, access:
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api-docs

## H2 Console

For debugging, access the H2 database console:
- **URL**: http://localhost:8080/h2-console
- **JDBC URL**: jdbc:h2:mem:bookstore
- **Username**: sa
- **Password**: (empty)

## Sample API Requests

### Create an Author
```
json
POST /api/authors
{
    "name": "John Doe",
    "email": "john@example.com",
    "biography": "Famous author of programming books"
}
```

### Create a Book
```
json
POST /api/books
{
    "title": "Java Programming",
    "isbn": "978-0134685991",
    "description": "Complete guide to Java",
    "price": 499.99,
    "quantity": 10,
    "genre": "Programming",
    "publisher": "Tech Publishers",
    "authorId": 1
}
```

### Get Paginated and Sorted Books
```
GET /api/books/paginated?page=0&size=5&sort=price,desc
```

### Filter Books
```
GET /api/books/filter?genre=Programming&minPrice=100&maxPrice=1000
```

## Database Configuration

### H2 (Default)
```
properties
spring.datasource.url=jdbc:h2:mem:bookstore
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
```

### MySQL (Optional)
```
properties
spring.datasource.url=jdbc:mysql://localhost:3306/bookstore
spring.datasource.username=root
spring.datasource.password=your_password
```

## Interview Questions Covered

1. What is Spring Boot?
2. What is JPA?
3. Difference between JPA and Hibernate?
4. What are Spring Data Repositories?
5. What is RESTful API?
6. What is the difference between @RestController and @Controller?
7. What is @Autowired?
8. What is the difference between @GetMapping and @PostMapping?
9. What is pagination in Spring Data?
10. What is sorting in Spring Data?
11. What are the different types of relationships in JPA?
12. What is @OneToMany and @ManyToOne?
13. What is Swagger/OpenAPI?
14. What is Lombok and its annotations?
15. What is the difference between H2 and MySQL?
16. What is the purpose of application.properties?
17. What is Spring Boot Starter?
18. What is the role of @Entity annotation?
19. What is @Id and @GeneratedValue?
20. What is the difference between GET, POST, PUT, DELETE?

## Author

Created for Java Developer Internship Task
