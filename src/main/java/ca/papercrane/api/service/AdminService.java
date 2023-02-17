package ca.papercrane.api.service;

import ca.papercrane.api.entity.Client;
import ca.papercrane.api.entity.Employee;
import ca.papercrane.api.repository.ClientRepository;
import ca.papercrane.api.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AdminService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    private final ClientRepository clientRepository;

    public AdminService(EmployeeRepository employeeRepository, ClientRepository clientRepository) {
        this.employeeRepository = employeeRepository;
        this.clientRepository = clientRepository;
    }

    // Method to create an employee account
    public Employee createEmployee(String email, String password,
                                   String employeeName, String employeeRole) {
        return employeeRepository.save(new Employee(email, password, employeeName, employeeRole));
    }

    // Method to update an employee account
    public void updateEmployee(int userId, String employeeName, String employeeRole) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(userId);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setEmployeeName(employeeName);
            employee.setRole(employeeRole);
            employeeRepository.save(employee);
        } else {
            throw new EntityNotFoundException("Employee not found with user ID: " + userId);
        }
    }

    // Method to delete an employee account
    public void deleteEmployee(int userId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(userId);
        optionalEmployee.ifPresent(employeeRepository::delete);
    }

    // Method to create a client account
    public void createClientAccount(Client client) {
        clientRepository.save(client);
    }

    // Method to update a client account
    public void updateClientAccount(Client client) {
        Optional<Client> existingClient = clientRepository.findById(client.getUserId());
        if (existingClient.isPresent()) {
            Client updatedClient = existingClient.get();
            updatedClient.setClientName(client.getClientName());
            updatedClient.setWebsite(client.getWebsite());
            clientRepository.save(updatedClient);
        } else {
            throw new EntityNotFoundException("Client not found with user ID: " + client.getUserId());
        }
    }

    // Method to delete a client account
    public void deleteClientAccount(int userId) {
        Optional<Client> existingClient = clientRepository.findById(userId);
        if (existingClient.isPresent()) {
            clientRepository.delete(existingClient.get());
        } else {
            throw new EntityNotFoundException("Client not found with user ID: " + userId);
        }
    }

}