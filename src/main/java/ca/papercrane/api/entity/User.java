package ca.papercrane.api.entity;

import jakarta.persistence.*;

/**
 * Represents a standard User account for general use of the application.
 */
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "type", length = 20, nullable = false)
    private String type;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    public User() {
        // default no-args constructor.
    }

    /**
     * Constructs a new User object.
     *
     * @param type     The type of user.
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

    /**
     * Sets the type of user.
     *
     * @param type The type to set for this user.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the email address.
     *
     * @param email The email address to set for this user.
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * Sets the password for this user.
     *
     * @param password The password to set for this user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

}