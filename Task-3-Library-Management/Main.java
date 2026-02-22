/**
 * Main Class - Demonstrates all OOP Concepts
 * 
 * This program demonstrates:
 * - Abstraction: Library class hides implementation details
 * - Encapsulation: Private fields with public getters/setters
 * - Inheritance: (Could be demonstrated with subclasses)
 * - Polymorphism: Method overriding (displayBookInfo could be overridden)
 * - Constructor Chaining: Using this() in constructors
 * - Classes & Objects: Creating and using objects
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  LIBRARY MANAGEMENT SYSTEM");
        System.out.println("  Demonstrating OOP Concepts");
        System.out.println("========================================\n");

        // Create Library object (Abstraction)
        Library library = new Library();

        // ========================================
        // ENCAPSULATION DEMONSTRATION
        // ========================================
        System.out.println(">>> ENCAPSULATION: Using getters and setters");
        
        // Create Book objects using constructor
        Book book1 = new Book("B001", "Java Programming", "James Gosling");
        Book book2 = new Book("B002", "Clean Code", "Robert Martin");
        Book book3 = new Book("B003", "Design Patterns", "Gang of Four");

        // Add books to library
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // ========================================
        // ABSTRACTION DEMONSTRATION
        // ========================================
        System.out.println("\n>>> ABSTRACTION: Using Library class methods");
        
        // Display all books
        library.displayBooks();

        // ========================================
        // ISSUING BOOKS
        // ========================================
        System.out.println("\n>>> ISSUING BOOKS");
        library.issueBook("B001");  // Issue book 1
        library.issueBook("B002");  // Issue book 2
        library.issueBook("B001");  // Try to issue already issued book

        // Display after issuing
        System.out.println("\n--- After Issuing Books ---");
        library.displayBooks();

        // ========================================
        // RETURNING BOOKS
        // ========================================
        System.out.println("\n>>> RETURNING BOOKS");
        library.returnBook("B001");  // Return book 1
        library.returnBook("B003");  // Try to return not-issued book

        // Display after returning
        System.out.println("\n--- After Returning Books ---");
        library.displayBooks();

        // ========================================
        // USER DEMONSTRATION
        // ========================================
        System.out.println("\n>>> USER MANAGEMENT");
        User user = new User("U001", "John Doe");
        user.displayUserInfo();

        // ========================================
        // OOP CONCEPTS SUMMARY
        // ========================================
        System.out.println("\n========================================");
        System.out.println("  OOP CONCEPTS DEMONSTRATED");
        System.out.println("========================================");
        System.out.println("1. ABSTRACTION: Library class hides complexity");
        System.out.println("2. ENCAPSULATION: Private fields with getters/setters");
        System.out.println("3. INHERITANCE: (Could be shown with subclasses)");
        System.out.println("4. POLYMORPHISM: Method overriding capability");
        System.out.println("5. CONSTRUCTOR CHAINING: Used in Book/User classes");
        System.out.println("6. CLASSES & OBJECTS: Book, User, Library");
        System.out.println("========================================\n");
        
        System.out.println("Program executed successfully!");
    }
}
