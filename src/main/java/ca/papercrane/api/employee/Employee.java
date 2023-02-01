package ca.papercrane.api.employee;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Scanner;

@EntityScan
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String name;
    private char role;
    /**
     * The method uses a Scanner object to read input from the user
     * and create a new Employee object with the given name and role.
     * The employeeService.saveEmployee(emp) line would save the new employee object to the database.
     */
    private EmployeeService employeeService;

    public Employee() {
        // TODO Auto-generated constructor stub
        this.userId = userId;
        this.name = name;
        this.role = role;
    }

    //getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getRole() {
        return role;
    }

    public void setRole(char role) {
        this.role = role;
    }

    private void register_emp_account() {

    }

    private void create_emp_account() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter employee name: ");
        String name = sc.nextLine();
        System.out.println("Enter employee role (D for Design Team, D for Development Team, A for Administration): ");
        char role = sc.nextLine().charAt(0);
        Employee emp = new Employee();
        //save employee to database by calling service method to do so
        employeeService.saveEmployee(emp);
        System.out.println("Employee account created successfully.");
    }


    private void delete_emp_account() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter (1) if you want to delete by User ID or (2) by name: ");
        int choice = scanner.nextInt();

        Employee employee = null;
        if (choice == 1) {
            System.out.println("Enter employee ID: ");
            int userId = scanner.nextInt();
            employee = employeeRepository.findById(userId).orElse(null);
        } else if (choice == 2) {
            System.out.println("Enter employee name: ");
            String name = scanner.nextLine();
            employee = employeeRepository.findByName(name);
        }

        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        employeeRepository.delete(employee);
        System.out.println("Employee account deleted successfully.");
    }

    // Not ready yet
    private void update_emp_account(Int userId, String name, Employee updatedEmployee) {
        Employee employee = null;
        if (userId != null) {
            employee = employeeRepository.findById(userId);
        } else if (name != null) {
            employee = employeeRepository.findByName(name);
        }
        if (employee != null) {
            employee.setName(updatedEmployee.getName());
            employee.setRole(updatedEmployee.getRole());
            employeeService.saveEmployee(emp);
        }

    }

    private void assign_emp_role() {

    }

    private void manage_emp_perm() {

    }


}
