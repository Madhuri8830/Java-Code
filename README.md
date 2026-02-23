# Java Code Repository

A collection of Java projects and assignments organized by task.

## Projects

- **Task-1-Calculator**: Calculator application
- **Task-2-Student-Management**: Student management system
- **Task-3-Library-Management**: Library management system
- **Task-4**: Java File I/O Notes App

## Task-1: Java Console Calculator

This project demonstrates basic Java syntax and logic flow:

- **OOP**: Object-Oriented Programming with Calculator class
- **Methods**: Addition, subtraction, multiplication, division
- **Method Overloading**: Same method name, different parameters
- **Conditionals**: if-else, switch-case
- **Loops**: while loop for repeated calculations
- **Input/Output**: Scanner class for user input
- **Exception Handling**: Divide-by-zero handling

### Running the Program

```
bash
cd Task-1-Calculator
javac *.java
java Main
```

## Task-2: Student Record Management System

This project demonstrates CLI-based CRUD operations:

- **Classes & Objects**: Student class with fields
- **Encapsulation**: Private fields with getters/setters
- **Constructors**: Default and parameterized constructors
- **Collections**: ArrayList<Student> for dynamic storage
- **Loops**: While loop for menu
- **Conditional Statements**: Switch-case for choices
- **CRUD Operations**: Add, View, Update, Delete students
- **Sorting**: ArrayList sorting by marks

### Running the Program

```
bash
cd Task-2-Student-Management
javac *.java
java Main
```

## Task-3: Library Management System

This project demonstrates Object-Oriented Programming concepts:

- **Abstraction**: Library class hides implementation details
- **Encapsulation**: Private fields with public getters/setters
- **Inheritance**: Class relationships
- **Polymorphism**: Method overriding capability
- **Constructor Chaining**: Using this() in constructors
- **Classes & Objects**: Book, User, Library classes

### Running the Program

```
bash
cd Task-3-Library-Management
javac *.java
java Main
```

## Task-4: Java File I/O - Notes App

This project demonstrates Java File I/O concepts:

- **File I/O**: FileReader, FileWriter, BufferedReader
- **Exception Handling**: try-catch blocks
- **try-with-resources**: Automatic resource management
- **Checked & Unchecked Exceptions**: IOException, NumberFormatException
- **Append vs Overwrite Mode**: FileWriter with boolean flag
- **Stack Trace & Finally Block**: Exception handling best practices

### Running the Program

```
bash
cd Task-4
javac *.java
java Main
```

## Getting Started

Each task folder contains its own Java source files. Compile and run them individually using Java compiler.

```
bash
javac Task-FolderName/src/*.java
java -cp Task-FolderName/src MainClassName
