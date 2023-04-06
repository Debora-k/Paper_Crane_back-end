package ca.papercrane.api.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
public class Admin extends Employee {

    public Admin(String email, String password, String firstName, String lastName, EmployeeType type) {
        super(email, password, firstName, lastName, type);
        this.setRole(UserRole.ADMIN);
    }

}