package ca.papercrane.api.entity;

import java.util.Arrays;
import java.util.Optional;

public enum UserRole {

    USER,

    CLIENT,

    EMPLOYEE,

    ADMIN,

    SUPER_ADMIN;

    public static Optional<UserRole> fromString(String str) {
        return Arrays.stream(values()).filter(role -> role.name().equalsIgnoreCase(str)).findFirst();
    }

}