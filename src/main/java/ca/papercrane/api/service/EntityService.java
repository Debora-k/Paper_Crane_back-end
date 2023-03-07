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
    private final EmployeeRepository employeeRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ClientRepository clientRepository;

    /**
     * Creates a new EntityService.
     *
     * @param employeeRepository The employee repository.
     * @param userRepository     The user repository.
     * @param clientRepository   The client repository.
     */
    public EntityService(EmployeeRepository employeeRepository, UserRepository userRepository, ClientRepository clientRepository) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
    }

    /**
     * Gets a list of all employees within the database.
     *
     * @return the list of employees.
     */
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Gets a specific employee from the database by employeeId.
     *
     * @param employeeId
     * @return
     */
    public Optional<Employee> getEmployeeById(Integer employeeId) {
        return employeeRepository.findById(employeeId);
    }

    /**
     * Gets all the employees with the specified employee name.
     *
     * @param employeeName The name of the employee.
     * @return The found employee.
     */
    public List<Employee> getByEmployeeName(String employeeName) {
        return employeeRepository.findByName(employeeName);
    }


    /**
     * Gets a user by the specified user id.
     *
     * @param userId The id of the user.
     * @return The user.
     */
    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    /**
     * Gets a list of all users within the database.
     *
     * @return The list.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Gets a list of all users by a specific user type.
     *
     * @param type The type of the user.
     * @return The list of users.
     */
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


}