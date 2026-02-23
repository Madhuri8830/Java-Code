/**
 * Calculator Class - Demonstrates OOP and Method Overloading
 * 
 * Key Concepts Covered:
 * - OOP (Object-Oriented Programming)
 * - Methods
 * - Method Overloading
 * - Conditionals
 * - Exception Handling for divide-by-zero
 */
public class Calculator {
    
    // Default constructor
    public Calculator() {
    }
    
    // Addition - Method Overloading (different parameters)
    public int add(int a, int b) {
        return a + b;
    }
    
    public double add(double a, double b) {
        return a + b;
    }
    
    public int add(int a, int b, int c) {
        return a + b + c;
    }
    
    // Subtraction
    public int subtract(int a, int b) {
        return a - b;
    }
    
    public double subtract(double a, double b) {
        return a - b;
    }
    
    // Multiplication
    public int multiply(int a, int b) {
        return a * b;
    }
    
    public double multiply(double a, double b) {
        return a * b;
    }
    
    // Division - Handles divide-by-zero
    public double divide(int a, int b) {
        // How to handle divide-by-zero
        if (b == 0) {
            System.out.println("Error: Cannot divide by zero!");
            return Double.NaN; // Not a Number
        }
        return (double) a / b;
    }
    
    public double divide(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Cannot divide by zero!");
            return Double.NaN;
        }
        return a / b;
    }
    
    // Modulus
    public int modulus(int a, int b) {
        if (b == 0) {
            System.out.println("Error: Cannot calculate modulus with zero!");
            return 0;
        }
        return a % b;
    }
    
    // Power
    public double power(double base, int exponent) {
        return Math.pow(base, exponent);
    }
    
    // Square root
    public double squareRoot(double num) {
        if (num < 0) {
            System.out.println("Error: Cannot calculate square root of negative number!");
            return Double.NaN;
        }
        return Math.sqrt(num);
    }
}
