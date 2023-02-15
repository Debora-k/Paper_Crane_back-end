package ca.papercrane.api.repository;

import ca.papercrane.api.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findById(Integer userId);
    List<Client> findByName(String clientName);
  }
