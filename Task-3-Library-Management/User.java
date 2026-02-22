/**
 * User Class - Demonstrates Encapsulation
 * Fields are private with getters and setters
 */
public class User {
    // Private fields - Encapsulation
    private String userId;
    private String name;

    // Default Constructor
    public User() {
    }

    // Parameterized Constructor - Constructor Chaining using this()
    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Method to display user information
    public void displayUserInfo() {
        System.out.println("User ID: " + userId);
        System.out.println("Name: " + name);
        System.out.println("-----------------------------");
    }
}
