package ca.papercrane.api.entity;

import ca.papercrane.api.entity.role.EmployeeRole;
import ca.papercrane.api.entity.role.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents an Employee user.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
@PrimaryKeyJoinColumn(name = "user_id")
public class Employee extends User {

    /**
     * The employees first name.
     */
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    /**
     * The employee's last name.
     */
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    /**
     * The role of the employee.
     */
    @Column(name = "type", length = 20, nullable = false)
    private EmployeeRole type;

    /**
     * Creates a new Employee.
     *
     * @param email     The email address for the user.
     * @param password  The password for the user.
     * @param firstName The employees first name.
     * @param lastName  The employees last name.
     * @param type      The type of employee.
     */
    public Employee(String email, String password, String firstName, String lastName, EmployeeRole type) {
        super(email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.setRole(UserRole.EMPLOYEE);
    }

}