package ca.papercrane.api.service.impl;

import ca.papercrane.api.entity.Employee;
import ca.papercrane.api.entity.role.UserRole;
import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.repository.EmployeeRepository;
import ca.papercrane.api.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAll() throws ResourceNotFoundException {
        val employeeList = employeeRepository.findAll().stream().filter(e -> e.getRole().equals(UserRole.EMPLOYEE)).collect(Collectors.toList());
        if (employeeList.isEmpty()) {
            throw new ResourceNotFoundException("No employees found!");
        }
        return employeeList;
    }

    @Override
    public List<Employee> getAllWithRole(UserRole role) {
        val employeeList = employeeRepository.findAll().stream().filter(e -> e.getRole().equals(role)).collect(Collectors.toList());
        if (employeeList.isEmpty()) {
            throw new ResourceNotFoundException("No users with role: " + role.toString() + " found!");
        }
        return employeeList;
    }

    @Override
    public List<Employee> getAllWithType(String type) {
        return employeeRepository.findAll().stream().filter(e -> e.getType().toString().equalsIgnoreCase(type)).collect(Collectors.toList());
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
        val employeeOptional = employeeRepository.findByEmail(employee.getEmail());
        if (employeeOptional.isPresent()) {
            throw new IllegalArgumentException("Email already taken.");
        }
        employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void update(Integer userId, String email, String password, String firstName, String lastName) {
        val employee = getByUserId(userId);
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
        if (firstName != null && firstName.length() > 0 && !Objects.equals(employee.getFirstName(), firstName)) {
            employee.setFirstName(firstName);
        }
        if (lastName != null && lastName.length() > 0 && !Objects.equals(employee.getLastName(), lastName)) {
            employee.setLastName(lastName);
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
        employeeRepository.findByUserId(userId).ifPresent(employeeRepository::delete);
    }

    @Override
    public Long totalCount() {
        return employeeRepository.count();
    }

}