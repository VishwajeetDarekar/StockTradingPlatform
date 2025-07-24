import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private ArrayList<Integer> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addGrade(int grade) {
        grades.add(grade);
    }

    public double getAverage() {
        if (grades.isEmpty()) return 0;
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }

    public int getHighest() {
        if (grades.isEmpty()) return 0;
        int max = grades.get(0);
        for (int grade : grades) {
            if (grade > max) {
                max = grade;
            }
        }
        return max;
    }

    public int getLowest() {
        if (grades.isEmpty()) return 0;
        int min = grades.get(0);
        for (int grade : grades) {
            if (grade < min) {
                min = grade;
            }
        }
        return min;
    }

    public void displayReport() {
        System.out.println("Name: " + name);
        System.out.println("Grades: " + grades);
        System.out.printf("Average: %.2f\n", getAverage());
        System.out.println("Highest: " + getHighest());
        System.out.println("Lowest: " + getLowest());
        System.out.println("------------------------");
    }
}

public class StudentGradeTracker {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("----- Student Grade Tracker -----");
        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade to Student");
            System.out.println("3. Display Report");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> addGradeToStudent();
                case 3 -> displayReports();
                case 4 -> {
                    System.out.println("Exiting... Thank you!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        students.add(new Student(name));
        System.out.println("Student added successfully!");
    }

    private static void addGradeToStudent() {
        if (students.isEmpty()) {
            System.out.println("No students available. Please add a student first.");
            return;
        }

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        Student found = null;
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                found = s;
                break;
            }
        }

        if (found == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.print("Enter grade to add: ");
        int grade = scanner.nextInt();
        scanner.nextLine(); // consume newline
        found.addGrade(grade);
        System.out.println("Grade added successfully!");
    }

    private static void displayReports() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        for (Student s : students) {
            s.displayReport();
        }
    }
}
