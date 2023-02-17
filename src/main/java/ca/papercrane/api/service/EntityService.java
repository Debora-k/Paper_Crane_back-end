package ca.papercrane.api.service;

import ca.papercrane.api.entity.Client;
import ca.papercrane.api.entity.Employee;
import ca.papercrane.api.entity.User;
import ca.papercrane.api.repository.ClientRepository;
import ca.papercrane.api.repository.EmployeeRepository;
import ca.papercrane.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EntityService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    // Employees methods
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Integer userId) {
        return employeeRepository.findById(userId);
    }

    public List<Employee> getByEmployeeName(String employeeName) {
        return employeeRepository.findByName(employeeName);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }


    // User methods
    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUserByType(String type) {
        return userRepository.findByType(type);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(Integer userId) {
        userRepository.deleteById(userId);
    }

    public User updateUserById(Integer userId, User user) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setType(user.getType());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            return userRepository.save(existingUser);
        } else {
            return null; // needs to add Exception package to handle these staff
        }
    }

    // Client methods
    public Optional<Client> getClientById(Integer userId) {
        return clientRepository.findById(userId);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public List<Client> getByClientName(String clientName) {
        return clientRepository.findByName(clientName);
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(Client client) {
        clientRepository.delete(client);
    }

    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }
}


