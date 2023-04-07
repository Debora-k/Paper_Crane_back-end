package ca.papercrane.api.security.auth;

import ca.papercrane.api.entity.User;
import ca.papercrane.api.security.token.Token;

public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void save(Token token);

    void revokeAllUserTokens(User user);

}