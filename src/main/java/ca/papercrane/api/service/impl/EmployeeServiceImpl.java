package ca.papercrane.api.service.impl;

import ca.papercrane.api.entity.Employee;
import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.repository.EmployeeRepository;
import ca.papercrane.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee getByUserId(Integer employeeId) {
        return employeeRepository.findByUserId(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
    }

    @Override
    public Employee getByEmail(String email) {
        return employeeRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Employee not found with email: " + email));
    }

    @Override
    public Integer create(String email, String password, String employeeName, String employeeRole) {
        final Employee createdEmployee = employeeRepository.save(new Employee(email, password, employeeName, employeeRole));
        return createdEmployee.getUserId();
    }

    @Override
    public Integer create(Employee employee) {
        final Employee createdEmployee = employeeRepository.save(employee);
        return createdEmployee.getUserId();
    }

    @Override
    public void update(Employee employee) {
        final Employee existingEmployee = getByUserId(employee.getUserId());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPassword(employee.getPassword());
        existingEmployee.setEmployeeName(employee.getName());
        existingEmployee.setRole(employee.getEmployeeRole());
        save(existingEmployee);
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public void deleteByUserId(Integer userId) {
        final Employee employee = getByUserId(userId);
        employeeRepository.delete(employee);
    }

    @Override
    public Long totalCount() {
        return employeeRepository.count();
    }

}