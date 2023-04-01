package ca.papercrane.api.entity;

import jakarta.persistence.Entity;

@Entity
public class Admin extends Employee {

    public Admin() {
    }

    public Admin(String email, String password, String name) {
        super("developer", email, password, name, 'A');
    }

}