package ca.papercrane.api.security.auth;

import ca.papercrane.api.security.token.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    /**
     * The repository for storing user tokens.
     */
    private final TokenRepository tokenRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        //the request header.
        val authHeader = request.getHeader("Authorization");

        //validate the header.
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        //get the token string.
        val jwt = authHeader.substring(7);

        //check if the token exists, if it does then process the logout.
        tokenRepository.findByToken(jwt).ifPresent(token -> {

            //invalidate the token so it cannot be used anymore.
            token.invalidate();

            //save the invalidated token back to the database.
            tokenRepository.save(token);

            //complete the logout.
            SecurityContextHolder.clearContext();
            System.out.println("Logout accepted");

        });
    }

}