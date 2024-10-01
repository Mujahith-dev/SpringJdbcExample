package com.spring.Spring_Jdbc_Example;

import com.spring.Spring_Jdbc_Example.Model.Student;
import com.spring.Spring_Jdbc_Example.Services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleAppRunner implements CommandLineRunner {

    private final StudentServices studentServices;
    @Autowired
    public ConsoleAppRunner(StudentServices studentServices) {
        this.studentServices = studentServices;
    }

    @Override
    public void run(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            printMenu();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addStudent(scanner);
                    break;
                case "2":
                    viewAllStudents();
                    break;
                case "3":
                    updateStudent(scanner);
                    break;
                case "4":
                    deleteStudent(scanner);
                    break;
                case "5":
                    searchStudentById(scanner);
                    break;
                case "6":
                    searchStudentsByName(scanner);
                    break;
                case "0":
                    exit = true;
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private void printMenu() {
        System.out.println("\n=== Student Management Menu ===");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Search Student by RollNo");
        System.out.println("6. Search Students by Name");
        System.out.println("0. Exit");
    }

    private void addStudent(Scanner scanner) {
        try {
            System.out.print("Enter Roll No: ");
            int rollNo = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Age: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter City: ");
            String city = scanner.nextLine();

            System.out.print("Enter Marks: ");
            int marks = Integer.parseInt(scanner.nextLine());

            Student student = new Student();
            student.setRollNo(rollNo);
            student.setName(name);
            student.setAge(age);
            student.setCity(city);
            student.setMarks(marks);

            studentServices.addStudent(student);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format. Please enter numeric values for Roll No, Age, and Marks.");
        } catch (Exception e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    private void viewAllStudents() {
        List<Student> students = studentServices.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("\n--- All Students ---");
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    private void updateStudent(Scanner scanner) {
        try {
            System.out.print("Enter Roll No of the student to update: ");
            int rollNo = Integer.parseInt(scanner.nextLine());

            Student existingStudent = studentServices.getStudentById(rollNo);
            if (existingStudent == null) {
                System.out.println("No student found with Roll No: " + rollNo);
                return;
            }
            //update name
            System.out.print("Enter new Name (current: " + existingStudent.getName() + ", press Enter to skip): ");
            String name = scanner.nextLine();
            if (!name.trim().isEmpty()) {
                existingStudent.setName(name);
            }
            //update age
            System.out.print("Enter new Age (current: " + existingStudent.getAge() + ", press Enter to skip): ");
            String ageInput = scanner.nextLine();
            if (!ageInput.trim().isEmpty()) {
                int age = Integer.parseInt(ageInput);
                existingStudent.setAge(age);
            }
            //update city
            System.out.print("Enter new City (current: " + existingStudent.getCity() + ", press Enter to skip): ");
            String city = scanner.nextLine();
            if (!city.trim().isEmpty()) {
                existingStudent.setCity(city);
            }
            //update marks
            System.out.print("Enter new Marks (current: " + existingStudent.getMarks() + ", press Enter to skip): ");
            String markInput = scanner.nextLine();
            if (!markInput.trim().isEmpty()) {
                int mark = Integer.parseInt(markInput);
                existingStudent.setMarks(mark);
            }

            studentServices.updateStudent(existingStudent);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Roll No format.");
        } catch (Exception e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    private void deleteStudent(Scanner scanner) {
        try {
            System.out.print("Enter Roll No of the student to delete: ");
            int rollNo = Integer.parseInt(scanner.nextLine());

            boolean deleted = studentServices.deleteStudentById(rollNo);
            if (deleted) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("No student found with Roll No: " + rollNo);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Roll No format.");
        } catch (Exception e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }

    private void searchStudentById(Scanner scanner) {
        try {
            System.out.print("Enter Roll No to search: ");
            int rollNo = Integer.parseInt(scanner.nextLine());

            Student student = studentServices.getStudentById(rollNo);
            if (student != null) {
                System.out.println("Student found: " + student);
            } else {
                System.out.println("No student found with Roll No: " + rollNo);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Roll No format.");
        } catch (Exception e) {
            System.out.println("Error searching student: " + e.getMessage());
        }
    }

    private void searchStudentsByName(Scanner scanner) {
        System.out.print("Enter Name to search: ");
        String name = scanner.nextLine();

        List<Student> students = studentServices.getStudentByName(name);
        if (students.isEmpty()) {
            System.out.println("No students found with the name: " + name);
        } else {
            System.out.println("\n--- Search Results ---");
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }
}
