package ca.papercrane.api.service.impl;

import ca.papercrane.api.entity.User;
import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.repository.UserRepository;
import ca.papercrane.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getByUserId(Integer userId) {
        return userRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }

    @Override
    public Integer create(String type, String email, String password) {
        final User createdUser = userRepository.save(new User(type, email, password));
        return createdUser.getUserId();
    }

    @Override
    public Integer create(User user) {
        final User createdUser = userRepository.save(user);
        return createdUser.getUserId();
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
        final User user = getByUserId(userId);
        userRepository.delete(user);
    }

    @Override
    public Long totalCount() {
        return userRepository.count();
    }

}