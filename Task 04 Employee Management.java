class Employee {
    int employeeId;
    String name;
    String position;
    double salary;

    public Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee [ID=" + employeeId + ", Name=" + name + ", Position=" + position + ", Salary=" + salary + "]";
    }
}
class EmployeeManagementSystem {
    private Employee[] employees;
    private int size;
    private int capacity;

    public EmployeeManagementSystem(int capacity) {
        this.capacity = capacity;
        this.employees = new Employee[capacity];
        this.size = 0;
    }

    // Add employee
    public boolean addEmployee(Employee employee) {
        if (size >= capacity) {
            System.out.println("Employee list is full!");
            return false;
        }
        employees[size++] = employee;
        return true;
    }

    // Search employee by ID
    public Employee searchEmployee(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].employeeId == employeeId) {
                return employees[i];
            }
        }
        return null;
    }

    // Traverse employees
    public void traverseEmployees() {
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
    }

    // Delete employee by ID
    public boolean deleteEmployee(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].employeeId == employeeId) {
                employees[i] = employees[size - 1]; // Replace with last employee
                employees[size - 1] = null; // Nullify the last element
                size--;
                return true;
            }
        }
        return false;
    }
}
public class Main {
    public static void main(String[] args) {
        EmployeeManagementSystem system = new EmployeeManagementSystem(10);
        
        system.addEmployee(new Employee(1, "Alice", "Developer", 70000));
        system.addEmployee(new Employee(2, "Bob", "Designer", 65000));
        system.addEmployee(new Employee(3, "Charlie", "Manager", 90000));

        System.out.println("Traverse Employees:");
        system.traverseEmployees();

        System.out.println("\nSearch Employee with ID 2:");
        System.out.println(system.searchEmployee(2));

        System.out.println("\nDelete Employee with ID 2:");
        system.deleteEmployee(2);
        system.traverseEmployees();
    }
}