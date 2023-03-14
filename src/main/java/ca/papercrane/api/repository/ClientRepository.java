package ca.papercrane.api.repository;

import ca.papercrane.api.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findByUserId(Integer userId);

    Optional<Client> findByEmail(String email);

}