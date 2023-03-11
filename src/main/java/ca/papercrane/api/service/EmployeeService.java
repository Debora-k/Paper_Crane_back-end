package ca.papercrane.api.service;

import ca.papercrane.api.entity.Employee;

public interface EmployeeService {

    Employee getByUserId(Integer userId);

    Employee getByEmail(String email);

    Integer create(String email, String password, String employeeName, String employeeRole);

    Integer create(Employee employee);

    void update(Employee employee);

    void save(Employee employee);

    void delete(Employee employee);

    void deleteByUserId(Integer userId);

    Long totalCount();


}