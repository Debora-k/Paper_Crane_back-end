package ca.papercrane.api.controller;

import ca.papercrane.api.entity.Employee;
import ca.papercrane.api.entity.role.EmployeeType;
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

    /**
     * Gets a list of all normal Employee users.
     *
     * @return The list of admins.
     */
    @GetMapping("")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            val employeeList = employeeService.getAll();
            return new ResponseEntity<>(employeeList, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates a new Employee.
     *
     * @param employee The new employee being created.
     * @return The new employee generated user id.
     */
    @PostMapping("/new")
    public ResponseEntity<Integer> createEmployee(@RequestBody Employee employee) {
        try {
            val createdEmployeeId = employeeService.addNewEmployee(employee);
            return new ResponseEntity<>(createdEmployeeId, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes an Employee by their userId value.
     *
     * @param userId The employees user id.
     * @return The response status of the request.
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Integer userId) {
        try {
            employeeService.deleteByUserId(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates an existing Employee.
     *
     * @param employee The new employee details.
     * @return The response status of the request.
     */
    @PutMapping("/{userId}")
    public ResponseEntity<HttpStatus> updateEmployee(@RequestBody Employee employee) {
        try {
            employeeService.update(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets an Employee by their userId.
     *
     * @param userId The id of the employee being searched for.
     * @return The found employee data.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Integer userId) {
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
        employeeService.addNewEmployee(new Employee("employee1@email.com", "123456", "Employee", "#1", EmployeeType.DESIGNER));
        employeeService.addNewEmployee(new Employee("employee2@email.com", "123456", "Employee", "#2", EmployeeType.DEVELOPER));
        employeeService.addNewEmployee(new Employee("employee3@email.com", "123456", "Employee", "#3", EmployeeType.DEVELOPER));
        employeeService.addNewEmployee(new Employee("employee4@email.com", "123456", "Employee", "#4", EmployeeType.DEVELOPER));
        employeeService.addNewEmployee(new Employee("employee5@email.com", "123456", "Employee", "#5", EmployeeType.DESIGNER));
        employeeService.addNewEmployee(new Employee("employee6@email.com", "123456", "Employee", "#6", EmployeeType.DESIGNER));
        employeeService.addNewEmployee(new Employee("employee7@email.com", "123456", "Employee", "#7", EmployeeType.DEVELOPER));
        employeeService.addNewEmployee(new Employee("employee8@email.com", "123456", "Employee", "#8", EmployeeType.DESIGNER));
    }

}