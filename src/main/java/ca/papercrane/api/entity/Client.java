package ca.papercrane.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "client")
@PrimaryKeyJoinColumn(name = "user_id")
public final class Client extends User {

    @Column(name = "client_name", length = 50, nullable = false)
    private String clientName;

    @Column(name = "website", length = 300)
    private String website;

    public Client() {

    }

    public Client(String email, String password, String clientName, String website) {
        super("Client", email, password);
        this.clientName = clientName;
        this.website = website;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

}