package ca.papercrane.api.security.auth;

import ca.papercrane.api.entity.User;
import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.repository.UserRepository;
import ca.papercrane.api.security.jwt.JwtService;
import ca.papercrane.api.security.token.Token;
import ca.papercrane.api.security.token.TokenRepository;
import ca.papercrane.api.security.token.TokenType;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository repository;

    private final TokenRepository tokenRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        val user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        val token = Token.builder().
                user(user).
                token(jwtService.generateToken(user)).
                tokenType(TokenType.BEARER).
                expired(false).
                revoked(false).
                build();

        revokeAllUserTokens(user);

        save(token);

        return AuthenticationResponse.builder().
                id(user.getUserId()).
                email(user.getEmail()).
                role(user.getRole()).
                token(token.getToken()).
                build();
    }

    @Override
    public void save(Token token) {
        tokenRepository.save(token);
    }

    @Override
    public void revokeAllUserTokens(User user) {
        val validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUserId());
        if (validUserTokens.isEmpty()) {
            return;
        }
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

}