package ca.papercrane.api.project;

import jakarta.persistence.*;

@Entity
@Table(name = "project")
public class Project {

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

    //Gets the project id value.
    public Integer getProjectId() {
        return projectId;
    }

    //Gets the client id that this project is for.
    public Integer getClientId() {
        return clientId;
    }

    //Gets the project leaders user id.
    public Integer getProjectLeadId() {
        return projectLeadId;
    }
}