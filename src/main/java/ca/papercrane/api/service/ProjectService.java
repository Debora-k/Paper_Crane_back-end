package ca.papercrane.api.service;

import ca.papercrane.api.project.Project;
import ca.papercrane.api.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * A Service class for retrieving data from the {@link ProjectRepository}
 */
@Service
public record ProjectService(ProjectRepository projectRepository) {

    @Autowired
    public ProjectService {
    }

    /**
     * Returns an Optional containing a Project that exists with the specified projectId.
     *
     * @param projectId The projectId being searched for.
     * @return An Optional containing the found Project, or an empty Optional if no Project is found with the specified projectId.
     */
    public Optional<Project> getProjectById(Integer projectId) {
        return projectRepository.findByProjectId(projectId);
    }

    /**
     * Returns a list of Project objects that belong to the same clientId.
     *
     * @param clientId The clientId that these projects are for.
     * @return The list of Project objects.
     */
    public List<Project> getAllByClientId(Integer clientId) {
        return projectRepository.findByClientId(clientId);
    }

    /**
     * Persists a Project into the database.
     *
     * @param project The project being inserted into the database.
     * @return the Project object.
     */
    public Project persistProject(Project project) {

        //TODO: Check for the following before adding a project to the database.

        //TODO: Validate the project was created correctly.

        //TODO: clientId must not be null, clientId must point to a valid client saved within the database.

        //TODO: projectLeadId must not be null, projectLeadId must point to a valid employee within the database.

        return projectRepository.save(project);
    }

}