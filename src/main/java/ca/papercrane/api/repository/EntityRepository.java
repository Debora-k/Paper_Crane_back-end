package ca.papercrane.api.repository;

import ca.papercrane.api.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EntityRepository {

	  public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	    Optional<Employee> findById(Integer userId);
	    List<Employee> findByName(String employeeName);
	  }

	  public interface UserRepository extends JpaRepository<User, Integer> {
	    Optional<User> findById(Integer userId);
	    List<User> findByType(String type);
	  }

	  public interface ClientRepository extends JpaRepository<Client, Integer> {
	    Optional<Client> findById(Integer userId);
	    List<Client> findByName(String clientName);
	  }
	  
	}

