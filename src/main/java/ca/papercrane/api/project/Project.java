package ca.papercrane.api.project;

import jakarta.persistence.*;

@Entity
@Table(name = "project")
public final class Project {

    //TODO: From Hashem: we might need to add a column for a brief description for the project.

    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;

    @Column(name = "client_id", nullable = false)
    private Integer clientId;

    @Column(name = "project_lead_id", nullable = false)
    private Integer projectLeadId;

    public Project() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Creates a new Project.
     *
     * @param clientId      The id of the client this project is for.
     * @param projectLeadId The id of the employee user that will be the leader for the project.
     */
    public Project(Integer clientId, Integer projectLeadId) {
        this.clientId = clientId;
        this.projectLeadId = projectLeadId;
    }

    /**
     * Gets the project id.
     *
     * @return the id.
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * Gets the client id.
     *
     * @return The id of the client the project is for.
     */
    public Integer getClientId() {
        return clientId;
    }

    /**
     * Sets the client id to a new value.
     *
     * @param clientId the new client id value.
     */
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    /**
     * Gets the project leader employee id.
     *
     * @return The id of the project leader.
     */
    public Integer getProjectLeadId() {
        return projectLeadId;
    }

    /**
     * Sets the project leader id.
     *
     * @param projectLeadId The new project leader id.
     */
    public void setProjectLeadId(Integer projectLeadId) {
        this.projectLeadId = projectLeadId;
    }

}