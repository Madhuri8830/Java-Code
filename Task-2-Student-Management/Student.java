/**
 * Student Class - Demonstrates Encapsulation
 * 
 * Key Concepts Covered:
 * - Classes & Objects
 * - Encapsulation (private fields with getters/setters)
 * - Constructors & Constructor Overloading
 * - Static variables
 * - Access modifiers
 */
public class Student {
    
    // Private fields - Encapsulation
    private int id;
    private String name;
    private double marks;
    
    // Static variable - shared by all objects
    private static int studentCount = 0;
    
    // Default Constructor - Constructor Overloading
    public Student() {
        this.id = 0;
        this.name = "";
        this.marks = 0.0;
    }
    
    // Parameterized Constructor
    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
        studentCount++;
    }
    
    // Getters and Setters - Why we use getters and setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getMarks() {
        return marks;
    }
    
    public void setMarks(double marks) {
        // Validate marks to avoid runtime errors
        if (marks >= 0 && marks <= 100) {
            this.marks = marks;
        } else {
            System.out.println("Error: Marks should be between 0 and 100!");
        }
    }
    
    // Static getter for student count
    public static int getStudentCount() {
        return studentCount;
    }
    
    // Display student info
    public void display() {
        System.out.printf("| %-5d | %-20s | %-6.2f |%n", id, name, marks);
    }
    
    // ToString method
    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', marks=" + marks + "}";
    }
}
