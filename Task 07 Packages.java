import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class PackageExecutor {

    public static void addCustomer(Connection conn, int customerId, String name, java.sql.Date dob, double balance) {
        String plsql = "{call CustomerManagement.AddCustomer(?, ?, ?, ?)}";
        try (CallableStatement stmt = conn.prepareCall(plsql)) {
            stmt.setInt(1, customerId);
            stmt.setString(2, name);
            stmt.setDate(3, dob);
            stmt.setDouble(4, balance);
            stmt.execute();
            conn.commit();
        } catch (SQLException e) {
            System.out.println("Error executing AddCustomer: " + e.getMessage());
        }
    }

    public static void updateEmployeeDetails(Connection conn, int employeeId, String name, double salary) {
        String plsql = "{call EmployeeManagement.UpdateEmployeeDetails(?, ?, ?)}";
        try (CallableStatement stmt = conn.prepareCall(plsql)) {
            stmt.setInt(1, employeeId);
            stmt.setString(2, name);
            stmt.setDouble(3, salary);
            stmt.execute();
            conn.commit();
        } catch (SQLException e) {
            System.out.println("Error executing UpdateEmployeeDetails: " + e.getMessage());
        }
    }

    public static double getTotalBalance(Connection conn, int customerId) {
        String plsql = "{? = call AccountOperations.GetTotalBalance(?)}";
        double totalBalance = 0.0;
        try (CallableStatement stmt = conn.prepareCall(plsql)) {
            stmt.registerOutParameter(1, Types.DOUBLE);
            stmt.setInt(2, customerId);
            stmt.execute();
            totalBalance = stmt.getDouble(1);
        } catch (SQLException e) {
            System.out.println("Error executing GetTotalBalance: " + e.getMessage());
        }
        return totalBalance;
    }
}
