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
    private String name;

    @Column(name = "employee_role", length = 1, nullable = false)
    private char role;

    public Employee() {

    }

    public Employee(String type, String email, String password, String name, char role) {
        super(type, email, password);
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String employeeName) {
        this.name = employeeName;
    }

    public char getRole() {
        return role;
    }

    public void setRole(char newRole) {
        this.role = newRole;
    }

}