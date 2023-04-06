package ca.papercrane.api.service;

import ca.papercrane.api.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getByUserId(Integer userId);

    User getByEmail(String email);

    void addNewUser(User user);

    void update(User user);

    void save(User user);

    void saveById(Integer userId);

    void delete(User user);

    void deleteByUserId(Integer userId);

    Long totalCount();

}