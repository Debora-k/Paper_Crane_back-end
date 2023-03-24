package ca.papercrane.api.project;

import jakarta.persistence.*;

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

    //TODO: Add to database if we want to use a description.
    @Column(name = "project_description")
    private String projectDescription;

    public Project() {

    }

    public Project(Integer clientId, Integer projectLeadId, String projectDescription) {
        this.clientId = clientId;
        this.projectLeadId = projectLeadId;
        this.projectDescription = projectDescription;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getProjectLeadId() {
        return projectLeadId;
    }

    public void setProjectLeadId(Integer projectLeadId) {
        this.projectLeadId = projectLeadId;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

}