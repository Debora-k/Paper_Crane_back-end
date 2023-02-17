package ca.papercrane.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
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
@PrimaryKeyJoinColumn(name = "user_id")
public final class Client extends User {

    @Column(name = "client_name", length = 50, nullable = false)
    private String clientName;

    @Column(name = "website", length = 300)
    private String website;

    public Client() {
        // default no-args constructor.
    }

    /**
     * Constructs a new Client object.
     *
     * @param email      The email used for the client account.
     * @param password   The password used for logging into the client account.
     * @param clientName The name of the client user.
     * @param website    The client's website address.
     */
    public Client(String email, String password, String clientName, String website) {
        super("Client", email, password);
        this.clientName = clientName;
        this.website = website;
    }

    //Gets the client name.
    public String getClientName() {
        return clientName;
    }

    //Sets the client name.
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    //Gets the client website address.
    public String getWebsite() {
        return website;
    }

    //Sets the client website.
    public void setWebsite(String website) {
        this.website = website;
    }

}