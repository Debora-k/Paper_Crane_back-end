package ca.papercrane.api.controller;

import ca.papercrane.api.entity.Employee;
import ca.papercrane.api.entity.role.EmployeeRole;
import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.service.impl.EmployeeServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/employees")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    @PostConstruct
    public void init() {
        createFakeEmployees();
        System.out.println("Fake employees created view at: http://localhost:8080/api/v1/employees/1");
    }

    @GetMapping("")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            val employeeList = employeeService.getAll();
            return new ResponseEntity<>(employeeList, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Employee> getUser(@PathVariable Integer userId) {
        try {
            val employee = employeeService.getByUserId(userId);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Just to test for now.
     */
    public void createFakeEmployees() {
        employeeService.addNewEmployee(new Employee("employee1@email.com", "123456", "Employee", "#1", EmployeeRole.DESIGNER));
        employeeService.addNewEmployee(new Employee("employee2@email.com", "123456", "Employee", "#2", EmployeeRole.DEVELOPER));
        employeeService.addNewEmployee(new Employee("employee3@email.com", "123456", "Employee", "#3", EmployeeRole.DEVELOPER));
        employeeService.addNewEmployee(new Employee("employee4@email.com", "123456", "Employee", "#4", EmployeeRole.DEVELOPER));
        employeeService.addNewEmployee(new Employee("employee5@email.com", "123456", "Employee", "#5", EmployeeRole.DESIGNER));
        employeeService.addNewEmployee(new Employee("employee6@email.com", "123456", "Employee", "#6", EmployeeRole.DESIGNER));
        employeeService.addNewEmployee(new Employee("employee7@email.com", "123456", "Employee", "#7", EmployeeRole.DEVELOPER));
        employeeService.addNewEmployee(new Employee("employee8@email.com", "123456", "Employee", "#8", EmployeeRole.DESIGNER));
    }

}