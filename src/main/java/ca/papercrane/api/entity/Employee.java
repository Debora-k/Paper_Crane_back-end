package ca.papercrane.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Represents an employee in our database. An Employee may do the following:
 * <p>
 * 1) Access video training courses
 * 2) Request time off
 * 3) Log their working time per project through a form
 * 4) View past hours submissions in a calendar layout
 * 5) Add and modify client accounts
 */
@Entity
@Table(name = "employee")
public final class Employee extends Client {

    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @Column(name = "employee_role", nullable = false)
    private String employeeRole;

    public Employee() {
        // default no-args constructor.
    }

    /**
     * Constructs a new Employee object with the given employee name and role.
     *
     * @param type         TODO: Find out what the type represents.
     * @param email        The email tied to this Employee user account.
     * @param password     The password tied to this Employee user account.
     * @param clientName   The client name of the Client
     * @param website      The website of the Client
     * @param employeeName The employee name of the Employee
     * @param employeeRole The employee role of the Employee
     */
    public Employee(String type, String email, String password, String clientName, String website,
                    String employeeName, String employeeRole) {
        super(type, email, password, clientName, website);
        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
    }

    //Get the name of the employee.
    public String getName() {
        return employeeName;
    }

    //Set the new role for this employee.
    public void setRole(String newEmployeeRole) {
        this.employeeRole = newEmployeeRole;
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