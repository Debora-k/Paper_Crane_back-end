package ca.papercrane.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * The client is an extension of a general user. Clients may do the following:
 * <p>
 * 1) View current and past projects
 * 2) View scope of requested work per project
 * 3) View a list of links or view offboarding/training videos
 * 4) Submit tickets or requests for work and scope changes
 * 5) Access relevant files
 * 6) Access table of hours worked with associated tasks completed if they are on a retainer model.
 * <p>
 */
@Entity
@Table(name = "client")
public class Client extends User {

    @Column(name = "client_name", nullable = false)
    private String clientName;

    @Column(name = "website")
    private String website;

    public Client() {
        // default no-args constructor.
    }

    /**
     * Constructs a new Client object.
     *
     * @param type       TODO: Find out what the string type represents.
     * @param email      The email used for the client account.
     * @param password   The password used for logging into the client account.
     * @param clientName The name of the client user.
     * @param website    The client's website address.
     */
    public Client(String type, String email, String password, String clientName, String website) {
        super(type, email, password);
        this.clientName = clientName;
        this.website = website;
    }

    //Gets the client name.
    public String getClientName() {
        return clientName;
    }

    //Gets the client website address.
    public String getWebsite() {
        return website;
    }

}