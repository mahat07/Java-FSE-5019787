import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;

public class FundTransfer {
    public static void safeTransferFunds(Connection conn, int fromAccountId, int toAccountId, double amount) {
        String deductQuery = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";
        String creditQuery = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";
        Savepoint savepoint = null;

        try {
            conn.setAutoCommit(false);
            savepoint = conn.setSavepoint("BeforeTransfer");

            // Deduct amount from source account
            try (PreparedStatement deductStmt = conn.prepareStatement(deductQuery)) {
                deductStmt.setDouble(1, amount);
                deductStmt.setInt(2, fromAccountId);
                int rowsAffected = deductStmt.executeUpdate();
                if (rowsAffected == 0) {
                    throw new SQLException("Insufficient funds or account does not exist.");
                }
            }

            // Credit amount to destination account
            try (PreparedStatement creditStmt = conn.prepareStatement(creditQuery)) {
                creditStmt.setDouble(1, amount);
                creditStmt.setInt(2, toAccountId);
                creditStmt.executeUpdate();
            }

            conn.commit();
            System.out.println("Funds transferred successfully.");
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback(savepoint);
                    System.out.println("Transaction rolled back. Error: " + e.getMessage());
                }
            } catch (SQLException rollbackEx) {
                System.out.println("Error during rollback: " + rollbackEx.getMessage());
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error resetting auto-commit: " + e.getMessage());
            }
        }
    }
}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateSalary {
    public static void updateSalary(Connection conn, int employeeId, double percentageIncrease) {
        String updateQuery = "UPDATE employees SET salary = salary + (salary * ?) WHERE employee_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
            pstmt.setDouble(1, percentageIncrease / 100.0);
            pstmt.setInt(2, employeeId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Employee ID not found.");
            }

            System.out.println("Salary updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating salary: " + e.getMessage());
        }
    }
}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddNewCustomer {
    public static void addNewCustomer(Connection conn, int customerId, String name, String email) {
        String insertQuery = "INSERT INTO customers (customer_id, name, email) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
            pstmt.setInt(1, customerId);
            pstmt.setString(2, name);
            pstmt.setString(3, email);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Failed to insert customer.");
            }

            System.out.println("Customer added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding customer: " + e.getMessage());
        }
    }
}
