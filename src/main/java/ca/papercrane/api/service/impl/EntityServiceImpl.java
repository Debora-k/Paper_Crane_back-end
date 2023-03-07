package ca.papercrane.api.service.impl;

import ca.papercrane.api.entity.Employee;
import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.repository.EmployeeRepository;
import ca.papercrane.api.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class EntityServiceImpl implements EntityService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee findById(Integer employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found!"));
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Long totalCount() {
        return employeeRepository.count();
    }

}