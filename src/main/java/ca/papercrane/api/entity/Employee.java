package ca.papercrane.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
@PrimaryKeyJoinColumn(name = "user_id")
public final class Employee extends User {

    @Column(name = "employee_name", length = 50, nullable = false)
    private String employeeName;

    @Column(name = "employee_role", length = 1, nullable = false)
    private String employeeRole;

    public Employee() {

    }

    public Employee(String email, String password,
                    String employeeName, String employeeRole) {
        super("Employee", email, password);
        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
    }

    public String getName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setRole(String newEmployeeRole) {
        this.employeeRole = newEmployeeRole;
    }

}