package ca.papercrane.api.service.impl;

import ca.papercrane.api.entity.User;
import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.repository.UserRepository;
import ca.papercrane.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() throws ResourceNotFoundException {
        final List<User> users = userRepository.findAll();
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
        final Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new IllegalArgumentException("Email already taken.");
        }
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        final User existingUser = getByUserId(user.getUserId());
        existingUser.setEmail(user.getEmail());
        existingUser.setType(user.getType());
        existingUser.setPassword(user.getPassword());
        save(existingUser);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteByUserId(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Long totalCount() {
        return userRepository.count();
    }

}