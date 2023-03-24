package ca.papercrane.api.service;

import ca.papercrane.api.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();

    List<Employee> getAllWithRole(char role);

    List<Employee> getAllWithType(String type);

    Employee getByUserId(Integer userId);

    Employee getByEmail(String email);

    void addNewEmployee(Employee employee);

    void update(Integer userId, String email, String password, String name, char role);

    void save(Employee employee);

    void delete(Employee employee);

    void deleteByUserId(Integer userId);

    Long totalCount();


}