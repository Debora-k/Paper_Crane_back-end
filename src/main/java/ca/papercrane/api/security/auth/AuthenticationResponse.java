package ca.papercrane.api.security.auth;

import ca.papercrane.api.entity.role.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class AuthenticationResponse {

    private Integer id;
    private String email;
    private UserRole role;
    private String token;

}