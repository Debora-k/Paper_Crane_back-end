package ca.papercrane.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
@PrimaryKeyJoinColumn(name = "user_id")
public class Employee extends User {

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "type", length = 20, nullable = false)
    private EmployeeType type;

    /**
     * Creates a new Employee.
     *
     * @param email     The email address for the user.
     * @param password  The password for the user.
     * @param firstName The employees first name.
     * @param lastName  The employees last name.
     * @param type      The type of employee.
     */
    public Employee(String email, String password, String firstName, String lastName, EmployeeType type) {
        super(email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.setRole(UserRole.EMPLOYEE);
    }

}