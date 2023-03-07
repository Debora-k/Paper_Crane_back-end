package ca.papercrane.api.service;

import ca.papercrane.api.entity.Employee;

public interface EntityService {

    Employee findById(Integer employeeId);

    void save(Employee employee);

    Long totalCount();

}