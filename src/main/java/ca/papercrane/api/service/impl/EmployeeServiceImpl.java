package ca.papercrane.api.service.impl;

import ca.papercrane.api.entity.Employee;
import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.repository.EmployeeRepository;
import ca.papercrane.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAll() throws ResourceNotFoundException {
        final List<Employee> employeeList = employeeRepository.findAll();
        if (employeeList.isEmpty()) {
            throw new ResourceNotFoundException("No employees found!");
        }
        return employeeList;
    }

    @Override
    public List<Employee> getAllWithRole(char role) {
        return employeeRepository.findAll().stream().filter(e -> e.getRole() == role).collect(Collectors.toList());
    }

    @Override
    public List<Employee> getAllWithType(String type) {
        return employeeRepository.findAll().stream().filter(e -> e.getType().equals(type)).collect(Collectors.toList());
    }

    @Override
    public Employee getByUserId(Integer userId) {
        return employeeRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + userId));
    }

    @Override
    public Employee getByEmail(String email) {
        return employeeRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Employee not found with email: " + email));
    }

    @Override
    public void addNewEmployee(Employee employee) {
        final Optional<Employee> employeeOptional = employeeRepository.findByEmail(employee.getEmail());
        if (employeeOptional.isPresent()) {
            throw new IllegalArgumentException("Email already taken.");
        }
        employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void update(Integer userId, String email, String password, String name, char role) {
        final Employee employee = getByUserId(userId);
        if (email != null && email.length() > 0 && !Objects.equals(employee.getEmail(), email)) {
            final Optional<Employee> employeeOptional = employeeRepository.findByEmail(email);
            if (employeeOptional.isPresent()) {
                throw new IllegalArgumentException("Email is already taken.");
            }
            employee.setEmail(email);
        }
        if (password != null && password.length() > 0 && !Objects.equals(employee.getPassword(), password)) {
            employee.setPassword(password);
        }
        if (name != null && name.length() > 0 && !Objects.equals(employee.getName(), name)) {
            employee.setName(name);
        }
        if (!Objects.equals(employee.getRole(), role)) {
            employee.setRole(role);
        }
        save(employee);
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
        employeeRepository.deleteById(userId);
    }

    @Override
    public Long totalCount() {
        return employeeRepository.count();
    }

}