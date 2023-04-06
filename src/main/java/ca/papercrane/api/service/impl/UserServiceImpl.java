package ca.papercrane.api.service.impl;

import ca.papercrane.api.entity.User;
import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.repository.UserRepository;
import ca.papercrane.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAll() throws ResourceNotFoundException {
        val users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No users found!");
        }
        return users;
    }

    @Override
    public User getByUserId(Integer userId) {
        return userRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }

    @Override
    public void addNewUser(User user) {
        val userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new IllegalArgumentException("Email already taken.");
        }
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        val existingUser = getByUserId(user.getUserId());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());
        existingUser.setPassword(user.getPassword());
        save(existingUser);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void saveById(Integer userId) {
        val user = getByUserId(userId);
        save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteByUserId(Integer userId) {
        val user = getByUserId(userId);
        userRepository.delete(user);
    }

    @Override
    public Long totalCount() {
        return userRepository.count();
    }

}