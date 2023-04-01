package ca.papercrane.api.service.impl;

import ca.papercrane.api.entity.Admin;
import ca.papercrane.api.entity.Employee;
import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.repository.AdminRepository;
import ca.papercrane.api.repository.EmployeeRepository;
import ca.papercrane.api.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Admin> getAll() throws ResourceNotFoundException {
        final List<Admin> adminList = adminRepository.findAll();
        if (adminList.isEmpty()) {
            throw new ResourceNotFoundException("No admins found!");
        }
        return adminList;
    }

    @Override
    public Admin getByUserId(Integer userId) {
        return adminRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + userId));
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
    public void addNewAdmin(Admin admin) {
        final Optional<Admin> adminOptional = adminRepository.findByEmail(admin.getEmail());
        if (adminOptional.isPresent()) {
            throw new IllegalArgumentException("Email already taken.");
        }
        adminRepository.save(admin);
    }

    @Override
    @Transactional
    public void update(Integer userId, String email, String password, String name, char role) {
        final Admin admin = getByUserId(userId);
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
        if (name != null && name.length() > 0 && !Objects.equals(admin.getName(), name)) {
            admin.setName(name);
        }
        if (!Objects.equals(admin.getRole(), role)) {
            admin.setRole(role);
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