package ca.papercrane.api.service;

import ca.papercrane.api.entity.User;

public interface UserService {

    User getByUserId(Integer userId);

    User getByEmail(String email);

    Integer create(String type, String email, String password);

    Integer create(User user);

    void update(User user);

    void save(User user);

    void delete(User user);

    void deleteByUserId(Integer userId);

    Long totalCount();

}