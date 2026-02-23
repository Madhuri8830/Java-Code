import java.util.Scanner;

/**
 * Main Class - Notes Management Application
 * Demonstrates Java File I/O concepts
 * 
 * Key Concepts Covered:
 * - File I/O (FileReader, FileWriter, BufferedReader)
 * - Exception Handling
 * - try-with-resources
 * - Checked and Unchecked Exceptions
 * - IOException Handling
 * - Append vs Overwrite mode
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NotesFileHandler handler = new NotesFileHandler("notes.txt");

        int choice;

        do {
            // Display menu
            System.out.println("========================================");
            System.out.println("     NOTES MANAGEMENT APPLICATION");
            System.out.println("========================================");
            System.out.println("1. Write Note (Overwrite mode)");
            System.out.println("2. Write Note (Append mode)");
            System.out.println("3. Read All Notes");
            System.out.println("4. Delete All Notes");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                // Unchecked Exception - RuntimeException
                System.out.println("Invalid input! Please enter a number.");
                choice = 0;
            }

            System.out.println();

            switch (choice) {
                case 1:
                    // Write Note - Overwrite mode
                    System.out.println(">>> WRITE NOTE (OVERWRITE MODE) <<<");
                    System.out.print("Enter your note: ");
                    String noteOverwrite = scanner.nextLine();
                    handler.writeNote(noteOverwrite, false); // false = overwrite
                    break;

                case 2:
                    // Write Note - Append mode
                    System.out.println(">>> WRITE NOTE (APPEND MODE) <<<");
                    System.out.print("Enter your note: ");
                    String noteAppend = scanner.nextLine();
                    handler.writeNote(noteAppend, true); // true = append
                    break;

                case 3:
                    // Read Notes - Demonstrates BufferedReader
                    System.out.println(">>> READ ALL NOTES <<<");
                    handler.readNotes();
                    break;

                case 4:
                    // Delete All Notes
                    System.out.println(">>> DELETE ALL NOTES <<<");
                    System.out.print("Are you sure? (yes/no): ");
                    String confirm = scanner.nextLine();
                    if (confirm.equalsIgnoreCase("yes")) {
                        handler.deleteAllNotes();
                    } else {
                        System.out.println("Deletion cancelled!");
                    }
                    break;

                case 5:
                    System.out.println("Thank you for using Notes App!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

            System.out.println();

        } while (choice != 5);

        scanner.close();

        // ========================================
        // INTERVIEW QUESTIONS SUMMARY
        // ========================================
        System.out.println("\n========================================");
        System.out.println("  KEY CONCEPTS DEMONSTRATED");
        System.out.println("========================================");
        System.out.println("1. FileReader vs BufferedReader");
        System.out.println("   - BufferedReader is faster due to buffering");
        System.out.println("2. try-with-resources");
        System.out.println("   - Auto-closes resources after use");
        System.out.println("3. IOException Handling");
        System.out.println("   - Using try-catch blocks");
        System.out.println("4. Checked vs Unchecked Exceptions");
        System.out.println("   - IOException is checked");
        System.out.println("   - NumberFormatException is unchecked");
        System.out.println("5. Append vs Overwrite mode");
        System.out.println("   - FileWriter(file, true) = append");
        System.out.println("   - FileWriter(file, false) = overwrite");
        System.out.println("6. Exception Propagation");
        System.out.println("   - Exceptions propagate up the call stack");
        System.out.println("7. Stack Trace");
        System.out.println("   - Shows the sequence of method calls");
        System.out.println("8. Finally Block");
        System.out.println("   - Always executes regardless of exception");
        System.out.println("========================================\n");
    }
}
