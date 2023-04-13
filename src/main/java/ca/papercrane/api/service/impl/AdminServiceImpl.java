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
    public Integer addNewEmployee(Employee employee) {
        val employeeOptional = employeeRepository.findByEmail(employee.getEmail());
        if (employeeOptional.isPresent()) {
            throw new IllegalArgumentException("Email already taken.");
        }
        val savedEmployee = employeeRepository.save(employee);
        return savedEmployee.getUserId();
    }

    @Override
    public Integer addNewAdmin(Admin admin) {
        val adminOptional = adminRepository.findByEmail(admin.getEmail());
        if (adminOptional.isPresent()) {
            throw new IllegalArgumentException("Email already taken.");
        }
        val savedAdmin = adminRepository.save(admin);
        return savedAdmin.getUserId();
    }

    @Override
    @Transactional
    public void update(Admin admin) {
        val existingAdmin = getByUserId(admin.getUserId());
        if (admin.getEmail() != null && admin.getEmail().length() > 0 && !Objects.equals(existingAdmin.getEmail(), admin.getEmail())) {
            final Optional<Admin> adminOptional = adminRepository.findByEmail(admin.getEmail());
            if (adminOptional.isPresent()) {
                throw new IllegalArgumentException("Email is already taken.");
            }
            existingAdmin.setEmail(admin.getEmail());
        }
        if (admin.getPassword() != null && admin.getPassword().length() > 0 && !Objects.equals(existingAdmin.getPassword(), admin.getPassword())) {
            existingAdmin.setPassword(admin.getPassword());
        }
        if (admin.getFirstName() != null && admin.getFirstName().length() > 0 && !Objects.equals(existingAdmin.getFirstName(), admin.getFirstName())) {
            existingAdmin.setFirstName(admin.getFirstName());
        }
        if (admin.getLastName() != null && admin.getLastName().length() > 0 && !Objects.equals(existingAdmin.getLastName(), admin.getLastName())) {
            existingAdmin.setLastName(admin.getLastName());
        }
    }

    @Override
    public void saveAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public void deleteByUserId(Integer userId) {
        adminRepository.findByUserId(userId).ifPresent(adminRepository::delete);
    }

}