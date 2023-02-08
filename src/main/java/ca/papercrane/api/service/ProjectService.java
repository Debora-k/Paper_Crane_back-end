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

}