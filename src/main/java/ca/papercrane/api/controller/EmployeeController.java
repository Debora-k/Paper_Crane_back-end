package ca.papercrane.api.controller;

import ca.papercrane.api.entity.Employee;
import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.service.impl.EmployeeServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees/")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostConstruct
    public void init() {
        createFakeEmployees();
        System.out.println("Fake employees created view at: http://localhost:8080/api/v1/employees/1");
    }

    @GetMapping("")
    public ResponseEntity<List<Employee>> getAll() {
        try {
            return new ResponseEntity<>(employeeService.getAllWithRole('A'), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getUser(@PathVariable Integer id) {
        try {
            final Employee employee = employeeService.getByUserId(id);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Just to test for now.
     */
    public void createFakeEmployees() {
        employeeService.addNewEmployee(new Employee("developer", "employee1@email.com", "123456", "Employee Name 1", 'A'));
        employeeService.addNewEmployee(new Employee("developer", "employee2@email.com", "123456", "Employee Name 2", 'A'));
        employeeService.addNewEmployee(new Employee("developer", "employee3@email.com", "123456", "Employee Name 3", 'A'));
        employeeService.addNewEmployee(new Employee("developer", "employee4@email.com", "123456", "Employee Name 4", 'S'));
        employeeService.addNewEmployee(new Employee("developer", "employee5@email.com", "123456", "Employee Name 5", 'C'));
        employeeService.addNewEmployee(new Employee("developer", "employee6@email.com", "123456", "Employee Name 6", 'C'));
        employeeService.addNewEmployee(new Employee("developer", "employee7@email.com", "123456", "Employee Name 7", 'C'));
        employeeService.addNewEmployee(new Employee("developer", "employee8@email.com", "123456", "Employee Name 8", 'D'));
    }

}