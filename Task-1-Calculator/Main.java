import java.util.Scanner;

/**
 * Main Class - Java Console Calculator
 * Demonstrates Java syntax and logic flow
 * 
 * Key Concepts Covered:
 * - Loops (while, for)
 * - Conditionals (if-else, switch)
 * - Input/Output using Scanner
 * - Basic data types in Java
 * - JVM and platform independence
 * - Debugging Java programs
 */
public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();
        
        int choice;
        boolean continueCalculating = true;
        
        // Loop to allow multiple calculations
        // While loop - used when condition is not known beforehand
        while (continueCalculating) {
            displayMenu();
            
            System.out.print("Enter your choice (1-8): ");
            
            // How is Scanner used for input?
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }
            
            // Switch case - different from if-else
            switch (choice) {
                case 1:
                    performAddition(scanner, calculator);
                    break;
                    
                case 2:
                    performSubtraction(scanner, calculator);
                    break;
                    
                case 3:
                    performMultiplication(scanner, calculator);
                    break;
                    
                case 4:
                    performDivision(scanner, calculator);
                    break;
                    
                case 5:
                    performModulus(scanner, calculator);
                    break;
                    
                case 6:
                    performPower(scanner, calculator);
                    break;
                    
                case 7:
                    performSquareRoot(scanner, calculator);
                    break;
                    
                case 8:
                    continueCalculating = false;
                    System.out.println("\nThank you for using Java Calculator!");
                    System.out.println("Exiting... Have a great day!");
                    break;
                    
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            
            System.out.println();
        }
        
        scanner.close();
        
        // Print interview questions summary
        printInterviewQuestions();
    }
    
    // Display menu - Methods in Java
    private static void displayMenu() {
        System.out.println("========================================");
        System.out.println("        JAVA CONSOLE CALCULATOR");
        System.out.println("========================================");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.println("5. Modulus");
        System.out.println("6. Power (x^y)");
        System.out.println("7. Square Root");
        System.out.println("8. Exit");
        System.out.println("========================================");
    }
    
    // Addition operation
    private static void performAddition(Scanner scanner, Calculator calculator) {
        System.out.println("\n>>> ADDITION <<<");
        System.out.print("Enter first number: ");
        double num1 = getNumber(scanner);
        System.out.print("Enter second number: ");
        double num2 = getNumber(scanner);
        
        // Method overloading - same method name, different parameters
        double result = calculator.add(num1, num2);
        System.out.println("Result: " + num1 + " + " + num2 + " = " + result);
    }
    
    // Subtraction operation
    private static void performSubtraction(Scanner scanner, Calculator calculator) {
        System.out.println("\n>>> SUBTRACTION <<<");
        System.out.print("Enter first number: ");
        double num1 = getNumber(scanner);
        System.out.print("Enter second number: ");
        double num2 = getNumber(scanner);
        
        double result = calculator.subtract(num1, num2);
        System.out.println("Result: " + num1 + " - " + num2 + " = " + result);
    }
    
    // Multiplication operation
    private static void performMultiplication(Scanner scanner, Calculator calculator) {
        System.out.println("\n>>> MULTIPLICATION <<<");
        System.out.print("Enter first number: ");
        double num1 = getNumber(scanner);
        System.out.print("Enter second number: ");
        double num2 = getNumber(scanner);
        
        double result = calculator.multiply(num1, num2);
        System.out.println("Result: " + num1 + " * " + num2 + " = " + result);
    }
    
    // Division operation - handles divide-by-zero
    private static void performDivision(Scanner scanner, Calculator calculator) {
        System.out.println("\n>>> DIVISION <<<");
        System.out.print("Enter dividend (number to divide): ");
        double num1 = getNumber(scanner);
        System.out.print("Enter divisor (number to divide by): ");
        double num2 = getNumber(scanner);
        
        // How to handle divide-by-zero?
        if (num2 == 0) {
            System.out.println("Error: Cannot divide by zero!");
            return;
        }
        
        double result = calculator.divide(num1, num2);
        if (!Double.isNaN(result)) {
            System.out.println("Result: " + num1 + " / " + num2 + " = " + result);
        }
    }
    
    // Modulus operation
    private static void performModulus(Scanner scanner, Calculator calculator) {
        System.out.println("\n>>> MODULUS <<<");
        System.out.print("Enter first number: ");
        int num1 = (int) getNumber(scanner);
        System.out.print("Enter second number: ");
        int num2 = (int) getNumber(scanner);
        
        int result = calculator.modulus(num1, num2);
        System.out.println("Result: " + num1 + " % " + num2 + " = " + result);
    }
    
    // Power operation
    private static void performPower(Scanner scanner, Calculator calculator) {
        System.out.println("\n>>> POWER <<<");
        System.out.print("Enter base: ");
        double base = getNumber(scanner);
        System.out.print("Enter exponent: ");
        int exponent = (int) getNumber(scanner);
        
        double result = calculator.power(base, exponent);
        System.out.println("Result: " + base + "^" + exponent + " = " + result);
    }
    
    // Square root operation
    private static void performSquareRoot(Scanner scanner, Calculator calculator) {
        System.out.println("\n>>> SQUARE ROOT <<<");
        System.out.print("Enter number: ");
        double num = getNumber(scanner);
        
        double result = calculator.squareRoot(num);
        if (!Double.isNaN(result)) {
            System.out.println("Result: √" + num + " = " + result);
        }
    }
    
    // Helper method to get number input
    private static double getNumber(Scanner scanner) {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number! Using 0.");
            return 0;
        }
    }
    
    // Print interview questions
    private static void printInterviewQuestions() {
        System.out.println("\n========================================");
        System.out.println("     INTERVIEW QUESTIONS COVERED");
        System.out.println("========================================");
        System.out.println("1. Method Overloading");
        System.out.println("   - Same method name, different parameters");
        System.out.println("2. Divide-by-zero Handling");
        System.out.println("   - Check divisor before division");
        System.out.println("3. == vs .equals()");
        System.out.println("   - == compares references");
        System.out.println("   - .equals() compares values");
        System.out.println("4. Basic Data Types in Java");
        System.out.println("   - int, double, float, char, boolean");
        System.out.println("5. Scanner for Input");
        System.out.println("   - Scanner class for console input");
        System.out.println("6. Role of Loop");
        System.out.println("   - Repeat code execution");
        System.out.println("7. While vs For Loop");
        System.out.println("   - While: when condition unknown");
        System.out.println("   - For: when iterations known");
        System.out.println("8. JVM (Java Virtual Machine)");
        System.out.println("   - Executes Java bytecode");
        System.out.println("9. Platform Independence");
        System.out.println("   - Write Once, Run Anywhere");
        System.out.println("10. Debugging Java");
        System.out.println("   - Use print statements, IDE debugger");
        System.out.println("========================================\n");
    }
}
