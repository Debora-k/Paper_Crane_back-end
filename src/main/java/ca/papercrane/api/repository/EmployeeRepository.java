package ca.papercrane.api.repository;

import ca.papercrane.api.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByUserId(Integer userId);

    Optional<Employee> findByEmail(String email);

}