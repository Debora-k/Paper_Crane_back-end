package ca.papercrane.api.repository;

import ca.papercrane.api.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findById(Integer userId);

    List<Employee> findByName(String employeeName);

}