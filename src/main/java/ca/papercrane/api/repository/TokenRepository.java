package ca.papercrane.api.repository;

import ca.papercrane.api.security.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    List<Token> findAllByUserUserId(Integer userId);

    Optional<Token> findByToken(String token);

}