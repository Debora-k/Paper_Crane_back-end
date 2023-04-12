package ca.papercrane.api.service;

import ca.papercrane.api.entity.Admin;
import ca.papercrane.api.entity.Employee;
import ca.papercrane.api.entity.role.UserRole;

import java.util.List;

public interface AdminService {

    List<Admin> getAll();

    List<Admin> getAllWithRole(UserRole role);

    List<Admin> getAllWithType(String type);

    Admin getByUserId(Integer userId);

    void addNewEmployee(Employee employee);

    void addNewAdmin(Admin admin);

    void update(Integer userId, String email, String password, String firstName, String lastName);

    void saveEmployee(Employee employee);

    void saveAdmin(Admin admin);

    void deleteEmployee(Employee employee);

    void deleteAdmin(Admin admin);

    void deleteByUserId(Integer userId);

}