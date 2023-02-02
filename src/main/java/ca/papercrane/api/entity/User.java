package ca.papercrane.api.entity;

import jakarta.persistence.*;

/**
 * Represents a standard User account for general use of the application.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    public User() {
        // default no-args constructor.
    }

    /**
     * Constructs a new User object.
     *
     * @param type     TODO: Find out what the string type represents.
     * @param email    The email tied into the user account.
     * @param password The password used for logging into the user account.
     */
    public User(String type, String email, String password) {
        this.type = type;
        this.email = email;
        this.password = password;
    }

    /**
     * Gets the users auto-generated id.
     *
     * @return The id for this user.
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Gets the type that this user is.
     *
     * @return the user type.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the email registered to the user account.
     *
     * @return the email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the password used to sign in to the user account.
     *
     * @return the user password.
     */
    public String getPassword() {
        return password;
    }

}