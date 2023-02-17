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

    /**
     * Creates a new AdminService.
     *
     * @param employeeRepository The employee repository.
     * @param clientRepository   The client repository.
     */
    public AdminService(EmployeeRepository employeeRepository, ClientRepository clientRepository) {
        this.employeeRepository = employeeRepository;
        this.clientRepository = clientRepository;
    }

    /**
     * Creates a new Employee.
     *
     * @param email        The employee email.
     * @param password     The employee password
     * @param employeeName The employee name.
     * @param employeeRole The employee role.
     * @return The newly created employee.
     */
    public Employee createEmployee(String email, String password,
                                   String employeeName, String employeeRole) {
        return employeeRepository.save(new Employee(email, password, employeeName, employeeRole));
    }

    /**
     * Updates an existing employee record within the database.
     *
     * @param employee The employee object with the updated data.
     */
    public void updateEmployee(Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getUserId());
        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setEmployeeName(employee.getName());
            existingEmployee.setRole(employee.getEmployeeRole());
            employeeRepository.save(existingEmployee);
        } else {
            throw new EntityNotFoundException("Employee not found with user ID: " + employee.getUserId());
        }
    }

    /**
     * Deletes an employee record from the database.
     *
     * @param employeeId The employee id.
     */
    public void deleteEmployee(int employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        optionalEmployee.ifPresent(employeeRepository::delete);
    }

    /**
     * Creates a new Client.
     *
     * @param email      The client email address.
     * @param password   The client account password.
     * @param clientName The client name.
     * @param website    The client website.
     * @return The newly created client object.
     */
    public Client createClient(String email, String password, String clientName, String website) {
        return clientRepository.save(new Client(email, password, clientName, website));
    }

    /**
     * Updates an existing client record within the database.
     *
     * @param client The client object with the updated data.
     */
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

    /**
     * Deletes a client account record from the database.
     *
     * @param client The client account.
     */
    public void deleteClientAccount(Client client) {
        Optional<Client> existingClient = clientRepository.findById(client.getUserId());
        if (existingClient.isPresent()) {
            clientRepository.delete(existingClient.get());
        } else {
            throw new EntityNotFoundException("Client not found with user ID: " + client.getUserId());
        }
    }

}