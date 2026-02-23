import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * NotesFileHandler Class - Demonstrates File I/O concepts
 * 
 * Key Concepts Covered:
 * - FileReader and FileWriter for file operations
 * - BufferedReader for efficient reading
 * - Exception Handling (try-catch)
 * - try-with-resources for automatic resource management
 * - IOException handling
 * - Append and Overwrite modes
 */
public class NotesFileHandler {
    private String fileName;

    // Constructor
    public NotesFileHandler(String fileName) {
        this.fileName = fileName;
    }

    // Getter
    public String getFileName() {
        return fileName;
    }

    /**
     * Write notes to file - Supports both append and overwrite modes
     * Uses try-with-resources for automatic resource management
     */
    public void writeNote(String content, boolean append) {
        // try-with-resources ensures FileWriter is closed automatically
        try (FileWriter writer = new FileWriter(fileName, append);
             PrintWriter printWriter = new PrintWriter(writer)) {
            
            printWriter.println(content);
            printWriter.println("---"); // Separator
            System.out.println(append ? "Note appended successfully!" : "Note written successfully!");
            
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            e.printStackTrace(); // Stack trace for debugging
        }
    }

    /**
     * Read notes from file using FileReader and BufferedReader
     * BufferedReader provides efficient reading
     */
    public void readNotes() {
        File file = new File(fileName);
        
        // Check if file exists
        if (!file.exists()) {
            System.out.println("No notes file found! Create one first.");
            return;
        }

        // try-with-resources for automatic resource management
        try (FileReader reader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            
            System.out.println("\n========== YOUR NOTES ==========");
            String line;
            boolean hasContent = false;
            
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                hasContent = true;
            }
            
            if (!hasContent) {
                System.out.println("No notes to display!");
            }
            System.out.println("=================================\n");
            
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Read notes using try-catch-finally (alternative approach)
     * Demonstrates traditional exception handling
     */
    public String readNotesAsString() {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = null;
        
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            
        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
            e.printStackTrace();
            
        } finally {
            // Finally block - always executes
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing reader: " + e.getMessage());
            }
        }
        
        return content.toString();
    }

    /**
     * Delete all notes by overwriting with empty content
     */
    public void deleteAllNotes() {
        try (FileWriter writer = new FileWriter(fileName, false)) {
            // Overwrite mode - false clears the file
            System.out.println("All notes deleted successfully!");
        } catch (IOException e) {
            System.out.println("Error deleting notes: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Check if file exists
     */
    public boolean fileExists() {
        File file = new File(fileName);
        return file.exists();
    }
}
