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

    @Column(name = "company_name", length = 50, nullable = false)
    private String companyName;

    public Client() {

    }

    public Client(String email, String password, String clientName, String companyName) {
        super("Client", email, password);
        this.clientName = clientName;
        this.companyName = companyName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getCompany() {
        return companyName;
    }

    public void setCompany(String companyName) {
        this.companyName = companyName;
    }

}