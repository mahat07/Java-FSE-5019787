import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ApplyInterestDiscount {
    public static void applyDiscount(Connection conn) throws SQLException {
        String selectQuery = "SELECT customer_id, loan_interest_rate FROM customers JOIN loans ON customers.customer_id = loans.customer_id WHERE age > 60";
        String updateQuery = "UPDATE loans SET loan_interest_rate = loan_interest_rate - 0.01 WHERE customer_id = ?";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectQuery);
             PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {

            while (rs.next()) {
                int customerId = rs.getInt("customer_id");
                double currentInterestRate = rs.getDouble("loan_interest_rate");

                pstmt.setInt(1, customerId);
                pstmt.executeUpdate();

                System.out.println("Applied 1% discount to customer " + customerId);
            }
        }
    }
}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PromoteToVIP {
    public static void promoteToVIP(Connection conn) throws SQLException {
        String selectQuery = "SELECT customer_id, balance FROM customers WHERE balance > 10000";
        String updateQuery = "UPDATE customers SET IsVIP = TRUE WHERE customer_id = ?";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectQuery);
             PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {

            while (rs.next()) {
                int customerId = rs.getInt("customer_id");

                pstmt.setInt(1, customerId);
                pstmt.executeUpdate();

                System.out.println("Customer " + customerId + " promoted to VIP.");
            }
        }
    }
}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;

public class SendReminders {
    public static void sendReminders(Connection conn) throws SQLException {
        LocalDate today = LocalDate.now();
        LocalDate in30Days = today.plusDays(30);

        String selectQuery = "SELECT customer_id, loan_id, due_date FROM loans WHERE due_date BETWEEN ? AND ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(selectQuery)) {
            pstmt.setDate(1, Date.valueOf(today));
            pstmt.setDate(2, Date.valueOf(in30Days));

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int customerId = rs.getInt("customer_id");
                    int loanId = rs.getInt("loan_id");
                    Date dueDate = rs.getDate("due_date");

                    System.out.println("Reminder: Loan " + loanId + " for customer " + customerId + " is due on " + dueDate);
                }
            }
        }
    }
}
