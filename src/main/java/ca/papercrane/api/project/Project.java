package ca.papercrane.api.project;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project")
public final class Project {

    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;

    @Column(name = "client_id", nullable = false)
    private Integer clientId;

    @Column(name = "project_lead_id", nullable = false)
    private Integer projectLeadId;

    @Column(name = "project_description")
    private String projectDescription;

    public Project(Integer clientId, Integer projectLeadId, String projectDescription) {
        this.clientId = clientId;
        this.projectLeadId = projectLeadId;
        this.projectDescription = projectDescription;
    }

}