package ca.papercrane.api.service.impl;

import ca.papercrane.api.entity.Admin;
import ca.papercrane.api.entity.Employee;
import ca.papercrane.api.entity.role.UserRole;
import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.repository.AdminRepository;
import ca.papercrane.api.repository.EmployeeRepository;
import ca.papercrane.api.service.AdminService;
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
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    private final EmployeeRepository employeeRepository;

    @Override
    public List<Admin> getAll() throws ResourceNotFoundException {
        val adminList = adminRepository.findAll().stream().filter(e -> e.getRole().equals(UserRole.ADMIN)).collect(Collectors.toList());
        if (adminList.isEmpty()) {
            throw new ResourceNotFoundException("No admins found!");
        }
        return adminList;
    }

    @Override
    public List<Admin> getAllWithRole(UserRole role) {
        val adminList = adminRepository.findAll().stream().filter(e -> e.getRole().equals(role)).collect(Collectors.toList());
        if (adminList.isEmpty()) {
            throw new ResourceNotFoundException("No admins with role: " + role.toString() + " found!");
        }
        return adminList;
    }

    @Override
    public List<Admin> getAllWithType(String type) {
        return adminRepository.findAll().stream().filter(e -> e.getType().toString().equalsIgnoreCase(type)).collect(Collectors.toList());
    }

    @Override
    public Admin getByUserId(Integer userId) {
        return adminRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + userId));
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
    public void addNewAdmin(Admin admin) {
        val adminOptional = adminRepository.findByEmail(admin.getEmail());
        if (adminOptional.isPresent()) {
            throw new IllegalArgumentException("Email already taken.");
        }
        adminRepository.save(admin);
    }

    @Override
    @Transactional
    public void update(Integer userId, String email, String password, String firstName, String lastName) {
        val admin = getByUserId(userId);
        if (email != null && email.length() > 0 && !Objects.equals(admin.getEmail(), email)) {
            final Optional<Admin> adminOptional = adminRepository.findByEmail(email);
            if (adminOptional.isPresent()) {
                throw new IllegalArgumentException("Email is already taken.");
            }
            admin.setEmail(email);
        }
        if (password != null && password.length() > 0 && !Objects.equals(admin.getPassword(), password)) {
            admin.setPassword(password);
        }
        if (firstName != null && firstName.length() > 0 && !Objects.equals(admin.getFirstName(), firstName)) {
            admin.setFirstName(firstName);
        }
        if (lastName != null && lastName.length() > 0 && !Objects.equals(admin.getLastName(), lastName)) {
            admin.setLastName(lastName);
        }
        saveAdmin(admin);
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void saveAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public void deleteAdmin(Admin admin) {
        adminRepository.delete(admin);
    }

    @Override
    public void deleteByUserId(Integer userId) {
        adminRepository.deleteById(userId);
    }

}