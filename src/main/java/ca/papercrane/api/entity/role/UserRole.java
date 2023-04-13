package ca.papercrane.api.entity.role;

import java.util.Arrays;
import java.util.Optional;

/**
 * Represents a users role within the system.
 */
public enum UserRole {

    USER,

    CLIENT,

    EMPLOYEE,

    ADMIN,

    SUPER_ADMIN;

    /**
     * Converts the parameter string into the corresponding UserRole type if any.
     *
     * @param str The string being checked for as an existing user role.
     * @return The result.
     */
    public static Optional<UserRole> fromString(String str) {
        return Arrays.stream(values()).filter(role -> role.name().equalsIgnoreCase(str)).findFirst();
    }

}