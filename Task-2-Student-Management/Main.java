import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main Class - Student Record Management System
 * CLI-based CRUD operations
 * 
 * Key Concepts Covered:
 * - ArrayList vs Arrays (ArrayList is dynamic, arrays are fixed size)
 * - Collections (ArrayList)
 * - Loops (while, for)
 * - Conditional Statements (if-else, switch)
 * - CRUD Operations (Create, Read, Update, Delete)
 * - Sorting ArrayList
 * - Garbage Collection
 * - Compile-time vs Runtime errors
 */
public class Main {
    
    // ArrayList to store Student objects - dynamic size
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        int choice;
        
        // While loop to repeatedly show menu
        while (true) {
            // Display menu
            displayMenu();
            
            System.out.print("Enter your choice: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                // Runtime error handling - NumberFormatException is unchecked
                System.out.println("Error: Please enter a valid number!");
                continue;
            }
            
            // Switch statement for menu choices
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                    
                case 2:
                    viewAllStudents();
                    break;
                    
                case 3:
                    updateStudent();
                    break;
                    
                case 4:
                    deleteStudent();
                    break;
                    
                case 5:
                    sortStudents();
                    break;
                    
                case 6:
                    System.out.println("\nThank you for using Student Management System!");
                    System.out.println("Total students: " + Student.getStudentCount());
                    scanner.close();
                    System.exit(0);
                    break;
                    
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            
            System.out.println();
        }
    }
    
    // Display menu options
    private static void displayMenu() {
        System.out.println("========================================");
        System.out.println("   STUDENT RECORD MANAGEMENT SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Sort Students by Marks");
        System.out.println("6. Exit");
        System.out.println("========================================");
    }
    
    // CREATE - Add Student
    private static void addStudent() {
        System.out.println("\n>>> ADD STUDENT <<<");
        
        try {
            System.out.print("Enter Student ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            // Check if ID already exists
            for (Student s : studentList) {
                if (s.getId() == id) {
                    System.out.println("Error: Student ID already exists!");
                    return;
                }
            }
            
            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter Student Marks: ");
            double marks = Double.parseDouble(scanner.nextLine());
            
            // Validate marks (runtime validation)
            if (marks < 0 || marks > 100) {
                System.out.println("Error: Marks should be between 0 and 100!");
                return;
            }
            
            // Create Student object and add to ArrayList
            Student student = new Student(id, name, marks);
            studentList.add(student);
            
            System.out.println("Student added successfully!");
            
        } catch (NumberFormatException e) {
            // Compile-time vs Runtime error handling
            System.out.println("Error: Invalid input format! Please enter correct data types.");
        }
    }
    
    // READ - View All Students
    private static void viewAllStudents() {
        System.out.println("\n>>> VIEW ALL STUDENTS <<<");
        
        if (studentList.isEmpty()) {
            System.out.println("No students found!");
            return;
        }
        
        // Table header
        System.out.println("+------------------------------------------+");
        System.out.printf("| %-5s | %-20s | %-6s |%n", "ID", "NAME", "MARKS");
        System.out.println("+------------------------------------------+");
        
        // Display all students
        for (Student student : studentList) {
            student.display();
        }
        
        System.out.println("+------------------------------------------+");
        System.out.println("Total Students: " + studentList.size());
    }
    
    // UPDATE - Update Student
    private static void updateStudent() {
        System.out.println("\n>>> UPDATE STUDENT <<<");
        
        if (studentList.isEmpty()) {
            System.out.println("No students to update!");
            return;
        }
        
        System.out.print("Enter Student ID to update: ");
        
        try {
            int searchId = Integer.parseInt(scanner.nextLine());
            boolean found = false;
            
            // Search in ArrayList
            for (Student student : studentList) {
                if (student.getId() == searchId) {
                    found = true;
                    
                    System.out.println("Current Details: " + student);
                    System.out.println("\nWhat do you want to update?");
                    System.out.println("1. Name");
                    System.out.println("2. Marks");
                    System.out.print("Enter choice: ");
                    
                    int updateChoice = Integer.parseInt(scanner.nextLine());
                    
                    switch (updateChoice) {
                        case 1:
                            System.out.print("Enter new Name: ");
                            String newName = scanner.nextLine();
                            student.setName(newName);
                            System.out.println("Name updated successfully!");
                            break;
                            
                        case 2:
                            System.out.print("Enter new Marks: ");
                            double newMarks = Double.parseDouble(scanner.nextLine());
                            student.setMarks(newMarks);
                            System.out.println("Marks updated successfully!");
                            break;
                            
                        default:
                            System.out.println("Invalid choice!");
                    }
                    
                    break;
                }
            }
            
            if (!found) {
                System.out.println("Student with ID " + searchId + " not found!");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input!");
        }
    }
    
    // DELETE - Delete Student
    private static void deleteStudent() {
        System.out.println("\n>>> DELETE STUDENT <<<");
        
        if (studentList.isEmpty()) {
            System.out.println("No students to delete!");
            return;
        }
        
        System.out.print("Enter Student ID to delete: ");
        
        try {
            int deleteId = Integer.parseInt(scanner.nextLine());
            boolean removed = false;
            
            // Remove student from ArrayList
            for (int i = 0; i < studentList.size(); i++) {
                if (studentList.get(i).getId() == deleteId) {
                    studentList.remove(i);
                    removed = true;
                    System.out.println("Student deleted successfully!");
                    // Garbage collection will clean up the removed object
                    break;
                }
            }
            
            if (!removed) {
                System.out.println("Student with ID " + deleteId + " not found!");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input!");
        }
    }
    
    // SORT - Sort ArrayList
    private static void sortStudents() {
        System.out.println("\n>>> SORT STUDENTS BY MARKS <<<");
        
        if (studentList.isEmpty()) {
            System.out.println("No students to sort!");
            return;
        }
        
        // Sort ArrayList using Collections.sort()
        // How to sort an ArrayList - using Comparable or Comparator
        studentList.sort((s1, s2) -> Double.compare(s1.getMarks(), s2.getMarks()));
        
        System.out.println("Students sorted by marks (ascending)!");
        viewAllStudents();
    }
}
