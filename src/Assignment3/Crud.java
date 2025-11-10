package Assignment3;

import java.sql.*;
import java.util.Scanner;

public class Crud {

    private static final String URL = "jdbc:postgresql://localhost:5432/Assignment3";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Broken11";

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver"); // load driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner scanner = new Scanner(System.in)) {
            System.out.println("Connected to the database.");
            boolean running = true;

            while (running) {
                System.out.println("\n--- STUDENT MENU ---");
                System.out.println("1. View all students");
                System.out.println("2. Add student");
                System.out.println("3. Update student email");
                System.out.println("4. Delete student");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> getAllStudents(conn);
                    case 2 -> {
                        System.out.print("First name: ");
                        String first = scanner.nextLine();
                        System.out.print("Last name: ");
                        String last = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enrollment date (YYYY-MM-DD): ");
                        String date = scanner.nextLine();
                        addStudent(conn, first, last, email, Date.valueOf(date));
                    }
                    case 3 -> {
                        System.out.print("Student ID to update: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("New email: ");
                        String newEmail = scanner.nextLine();
                        updateStudentEmail(conn, id, newEmail);
                    }
                    case 4 -> {
                        System.out.print("Student ID to delete: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        deleteStudent(conn, id);
                    }
                    case 5 -> {
                        System.out.println("Exiting...");
                        running = false;
                    }
                    default -> System.out.println("Invalid option, try again.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------------- CRUD METHODS ----------------

    private static void getAllStudents(Connection conn) throws SQLException {
        String sql = "SELECT * FROM public.students";
        try (Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            System.out.println("\nStudents:");
            while (rs.next()) {
                System.out.println(rs.getInt("student_id") + " - " +
                        rs.getString("first_name") + " " +
                        rs.getString("last_name") + " | " +
                        rs.getString("email") + " | " +
                        rs.getDate("enrollment_date"));
            }
        }
    }

    private static void addStudent(Connection conn, String first, String last, String email, Date enrollmentDate) throws SQLException {
        String sql = "INSERT INTO public.students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, first);
            statement.setString(2, last);
            statement.setString(3, email);
            statement.setDate(4, enrollmentDate);
            statement.executeUpdate();
            System.out.println("Student added.");
        }
    }

    private static void updateStudentEmail(Connection conn, int studentId, String newEmail) throws SQLException {
        String sql = "UPDATE public.students SET email = ? WHERE student_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, newEmail);
            statement.setInt(2, studentId);
            int rows = statement.executeUpdate();
            if (rows > 0) System.out.println("Email updated.");
            else System.out.println("⚠ Student not found.");
        }
    }

    private static void deleteStudent(Connection conn, int studentId) throws SQLException {
        String sql = "DELETE FROM public.students WHERE student_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            int rows = statement.executeUpdate();
            if (rows > 0) System.out.println("Student deleted.");
            else System.out.println("Student not found.");
        }
    }
}
