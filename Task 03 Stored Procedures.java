import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ProcessMonthlyInterest {
    public static void processMonthlyInterest(Connection conn) {
        String callProcedure = "{call ProcessMonthlyInterest()}";
        
        try (CallableStatement stmt = conn.prepareCall(callProcedure)) {
            stmt.execute();
            conn.commit();
            System.out.println("Monthly interest processed for all savings accounts.");
        } catch (SQLException e) {
            try {
                conn.rollback();
                System.out.println("Error processing monthly interest: " + e.getMessage());
            } catch (SQLException rollbackEx) {
                System.out.println("Error during rollback: " + rollbackEx.getMessage());
            }
        }
    }
}

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class UpdateEmployeeBonus {
    public static void updateEmployeeBonus(Connection conn, int departmentId, double bonusPercentage) {
        String callProcedure = "{call UpdateEmployeeBonus(?, ?)}";
        
        try (CallableStatement stmt = conn.prepareCall(callProcedure)) {
            stmt.setInt(1, departmentId);
            stmt.setDouble(2, bonusPercentage);
            stmt.execute();
            conn.commit();
            System.out.println("Bonus updated for employees in department " + departmentId);
        } catch (SQLException e) {
            try {
                conn.rollback();
                System.out.println("Error updating employee bonuses: " + e.getMessage());
            } catch (SQLException rollbackEx) {
                System.out.println("Error during rollback: " + rollbackEx.getMessage());
            }
        }
    }
}

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class TransferFunds {
    public static void transferFunds(Connection conn, int fromAccountId, int toAccountId, double amount) {
        String callProcedure = "{call TransferFunds(?, ?, ?)}";
        
        try (CallableStatement stmt = conn.prepareCall(callProcedure)) {
            stmt.setInt(1, fromAccountId);
            stmt.setInt(2, toAccountId);
            stmt.setDouble(3, amount);
            stmt.execute();
            conn.commit();
            System.out.println("Transferred " + amount + " from account " + fromAccountId + " to account " + toAccountId);
        } catch (SQLException e) {
            try {
                conn.rollback();
                System.out.println("Error transferring funds: " + e.getMessage());
            } catch (SQLException rollbackEx) {
                System.out.println("Error during rollback: " + rollbackEx.getMessage());
            }
        }
    }
}
