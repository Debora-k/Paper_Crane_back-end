package ca.papercrane.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
@PrimaryKeyJoinColumn(name = "user_id")
public final class Client extends User {

    @Column(name = "client_name", length = 50, nullable = false)
    private String clientName;

    @Column(name = "company_name", length = 50, nullable = false)
    private String companyName;

    public Client(String email, String password, String clientName, String companyName) {
        super(email, password);
        this.clientName = clientName;
        this.companyName = companyName;
        this.setRole(UserRole.CLIENT);
    }

}