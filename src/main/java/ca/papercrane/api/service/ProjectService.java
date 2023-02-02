package ca.papercrane.api.service;

import ca.papercrane.api.project.Project;
import ca.papercrane.api.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A Service class for retrieving data from the {@link ProjectRepository}
 */
@Service
public record ProjectService(ProjectRepository projectRepository) {

    @Autowired
    public ProjectService {
    }

    /**
     * Returns a project that exists with the specified projectId.
     *
     * @param projectId The projectId being searched for.
     * @return The project found.
     */
    public Project getProjectById(Integer projectId) {
        return projectRepository.findByProjectId(projectId);
    }

    /**
     * Returns a list of projects that belong to the same clientId.
     *
     * @param clientId The clientId that these projects are for.
     * @return The list of project objects.
     */
    public List<Project> findAllByClientId(Integer clientId) {
        return projectRepository.findByClientId(clientId);
    }

}