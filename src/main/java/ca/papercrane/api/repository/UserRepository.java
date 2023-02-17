package ca.papercrane.api.repository;

import ca.papercrane.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findById(Integer userId);

    List<User> findByType(String type);

}