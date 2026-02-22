import java.util.ArrayList;
import java.util.List;

/**
 * Library Class - Demonstrates Abstraction
 * Provides abstract-like interface for managing books
 */
public class Library {
    // HAS-A Relationship: Library HAS-A list of books
    private List<Book> books;

    // Constructor - initializes the book list
    public Library() {
        this.books = new ArrayList<>();
    }

    // Method to add a book
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully: " + book.getTitle());
    }

    // Method to issue a book
    public boolean issueBook(String bookId) {
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                if (book.isAvailable()) {
                    book.setAvailable(false);
                    System.out.println("Book issued successfully: " + book.getTitle());
                    return true;
                } else {
                    System.out.println("Sorry, book is already issued!");
                    return false;
                }
            }
        }
        System.out.println("Book not found!");
        return false;
    }

    // Method to return a book
    public boolean returnBook(String bookId) {
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                if (!book.isAvailable()) {
                    book.setAvailable(true);
                    System.out.println("Book returned successfully: " + book.getTitle());
                    return true;
                } else {
                    System.out.println("This book was not issued!");
                    return false;
                }
            }
        }
        System.out.println("Book not found!");
        return false;
    }

    // Method to display all books - Polymorphism via method overriding could be added
    public void displayBooks() {
        System.out.println("\n========== LIBRARY BOOKS ==========");
        if (books.isEmpty()) {
            System.out.println("No books in the library!");
            return;
        }
        for (Book book : books) {
            book.displayBookInfo();
        }
    }

    // Getter for books
    public List<Book> getBooks() {
        return books;
    }
}
