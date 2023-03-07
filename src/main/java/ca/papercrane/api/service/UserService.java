package ca.papercrane.api.service;

import ca.papercrane.api.entity.User;

public interface UserService {

    User findById(Integer userId);

    void save(User user);

    Long totalCount();

}
